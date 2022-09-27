package com.codestates.flyaway.domain.boardimage.service;

import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import com.codestates.flyaway.domain.boardimage.repository.BoardImageRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import com.codestates.flyaway.global.exception.ExceptionCode;
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

    public BoardImage saveFile(MultipartFile multipartFile) throws IOException {
        //Todo 파일이 비어있을 때 defaultImage 추가
        if(multipartFile.isEmpty()) {
            return null;
        }
        String originalFileName = multipartFile.getOriginalFilename();
        String fileName = createStoreFileName(originalFileName);
        multipartFile.transferTo(new File(getFullPath(fileName)));
        BoardImage boardImage = new BoardImage(originalFileName, fileDir, fileName);
        boardImageRepository.save(boardImage);

        return boardImage;
    }

    public List<BoardImage> saveFiles(List<MultipartFile> multipartFiles) {

        List<BoardImage> saveFileResult = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles) {
            try {
                BoardImage storedFile = saveFile(multipartFile);
                saveFileResult.add(storedFile);
            }catch(IOException e) {
                throw new BusinessLogicException(FILE_NOT_FOUND);
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
