package com.codestates.flyaway.domain.memberimage.service;

import com.codestates.flyaway.domain.memberimage.MemberImage;
import com.codestates.flyaway.domain.memberimage.repository.MemberImageRepository;
import com.codestates.flyaway.global.exception.BusinessLogicException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static com.codestates.flyaway.global.exception.ExceptionCode.*;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberImageService {

    private final MemberImageRepository memberImageRepository;

    @Value("${file.dir}")
    private String fileUrl;

    public String getFullPath(String filename) {
        return fileUrl + filename;
    }


    /**
     * 파일 저장 (DB 저장 + 로컬 pc 저장)
     */
    public MemberImage saveImage(MultipartFile multipartFile) throws IOException {

        String fileOriName = multipartFile.getOriginalFilename();
        String fileName = createFileName(fileOriName);

        multipartFile.transferTo(new File(getFullPath(fileName)));
        log.info("파일 저장 성공 = {}", fileName);

        return new MemberImage(fileOriName, fileUrl, fileName);
    }

    /**
     * 파일 삭제 (DB 삭제, pc 파일 삭제)
     */
    public void deleteImage(MemberImage image) {
        String fullPath = getFullPath(image.getFileName());
        File file = new File(fullPath);

        //파일 없을 시
        if (!file.exists()) {
            throw new BusinessLogicException(FILE_NOT_FOUND);
        }
        //파일 삭제 실패 시
        if (!file.delete()) {
            throw new BusinessLogicException(FILE_DELETE_FAILED);
        }

        log.info("파일 삭제 성공 ={}", file.getName());

        memberImageRepository.delete(image);
    }

    private String createFileName(String originalFilename) {

        String ext = extractExt(originalFilename);
        String uuid = UUID.randomUUID().toString();     //pk로 관리되는데 uuid가 필요할까?
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {

        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }
}
