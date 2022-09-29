package com.codestates.flyaway.domain.boardimage.service;

import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import com.codestates.flyaway.domain.boardimage.repository.BoardImageRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.global.exception.ExceptionCode;
import com.codestates.flyaway.web.board.dto.BoardImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.codestates.flyaway.global.exception.ExceptionCode.FILE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardImageService {

    private final BoardImageRepository boardImageRepository;

//    @Value("${file.dir}")
    @Value("/Users/ricepocket/Desktop/test/")
    private String fileDir;

    public String getFullPath(String fileName) {

        return fileDir + fileName;
    }

    public BoardImage saveFile(MultipartFile multipartFile, Board board) throws IOException {

        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(fileName)));
        BoardImage boardImage = new BoardImage(originalFileName, fileDir, fileName);
        boardImageRepository.save(boardImage);
        boardImage.setBoard(board);

        return boardImage;
    }

    public List<BoardImage> saveFiles(List<MultipartFile> multipartFiles, Board board) {

        List<BoardImage> saveFileResult = new ArrayList<>();
        //todo 급하게 작성해준거라 리팩토링필요, 디폴트 이미지 추가
        if(multipartFiles == null) {
            BoardImage boardImage = new BoardImage("default.png", "default", "default");
            boardImageRepository.save(boardImage);
            boardImage.setBoard(board);
        }else {
            for(MultipartFile multipartFile : multipartFiles) {
                try {
                    BoardImage storedFile = saveFile(multipartFile, board);
                    saveFileResult.add(storedFile);
                }catch(IOException e) {
                    throw new BusinessLogicException(FILE_NOT_FOUND);
                }
            }
        }
        return saveFileResult;
    }

    public void delete(Long imageId) {

        final BoardImage boardImage = boardImageRepository.getReferenceById(imageId);
        String fullPath = getFullPath(boardImage.getFileName());
        File file = new File(fullPath);

        if(!file.exists()) {
            throw new BusinessLogicException(ExceptionCode.FILE_NOT_FOUND);
        }
        if(!file.delete()) {
            throw new BusinessLogicException(ExceptionCode.FILE_DELETE_FAILED);
        }

        boardImageRepository.delete(boardImage);
    }

    public List<Long> findByBoard(Long boardId) {

        List<BoardImage> imageList = boardImageRepository.findAllByBoardId(boardId);

        return imageList.stream()
                .map(BoardImage::getId)
                .collect(Collectors.toList());
    }

    public BoardImageDto findByImageId(Long imageId) {

        BoardImage boardImage = boardImageRepository.findById(imageId).orElseThrow(() ->
                new BusinessLogicException(FILE_NOT_FOUND));

        return BoardImageDto.toResponseDto(boardImage);
    }

    public String createStoreFileName(String originalFileName) {

        String uuid = UUID.randomUUID().toString();
        String ext = extractedExt(originalFileName);

        return uuid + "." + ext;
    }

    public String extractedExt(String originalFileName) {

        int pos = originalFileName.lastIndexOf(".");

        return originalFileName.substring(pos + 1);
    }
}
