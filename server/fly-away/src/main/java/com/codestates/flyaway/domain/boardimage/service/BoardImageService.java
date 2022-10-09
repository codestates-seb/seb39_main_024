package com.codestates.flyaway.domain.boardimage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.codestates.flyaway.domain.board.entity.Board;
import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import com.codestates.flyaway.domain.boardimage.repository.BoardImageRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.web.board.dto.BoardImageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.codestates.flyaway.global.exception.ExceptionCode.FILE_NOT_FOUND;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BoardImageService {

    private final BoardImageRepository boardImageRepository;
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}/board")
    private String bucket;
    @Value("${cloud.aws.s3.bucket}/default")
    private String defaultUrl;

    BoardImage defaultImage = new BoardImage("default", defaultUrl, "defaultboard.jpg");

    public BoardImage saveFile(MultipartFile multipartFile, Board board) throws IOException {

        String originalFileName = multipartFile.getOriginalFilename();
        String s3FileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());
        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        BoardImage boardImage = new BoardImage(originalFileName, bucket, s3FileName);
        boardImageRepository.save(boardImage);
        boardImage.setBoard(board);

        return boardImage;
    }

    public List<BoardImage> saveFiles(List<MultipartFile> multipartFiles, Board board) {

        List<BoardImage> saveFileResult = new ArrayList<>();
        if(multipartFiles == null) {
            BoardImage boardImage = defaultImage;
            saveFileResult.add(boardImage);
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

    public List<BoardImage> updateFiles(List<MultipartFile> multipartFiles, Board board) {

        if(multipartFiles != null) {
            List<BoardImage> imageList = boardImageRepository.findAllByBoardId(board.getId());
            for (BoardImage boardImage : imageList) {
                delete(boardImage);
            }
            return saveFiles(multipartFiles, board);
        } else return boardImageRepository.findAllByBoardId(board.getId());
    }

    public String getImage(Long imageId) {

        BoardImageDto boardImageDto = findByImageId(imageId);

        return amazonS3.getUrl(boardImageDto.getFileUrl(), boardImageDto.getFileName()).toString();
    }


    public void delete(BoardImage boardImage) {

//    File file = new File(boardImage.getFileUrl());
//    if(!file.exists()) {
//        throw new BusinessLogicException(ExceptionCode.FILE_NOT_FOUND);
//    }
//    if(!file.delete()) {
//        throw new BusinessLogicException(ExceptionCode.FILE_DELETE_FAILED);
//    }
        boardImageRepository.deleteById(boardImage.getId());
    }

    public BoardImageDto findByImageId(Long imageId) {

        BoardImage boardImage = boardImageRepository.findById(imageId).orElseThrow(() ->
                new BusinessLogicException(FILE_NOT_FOUND));

        return BoardImageDto.toResponseDto(boardImage);
    }
}
