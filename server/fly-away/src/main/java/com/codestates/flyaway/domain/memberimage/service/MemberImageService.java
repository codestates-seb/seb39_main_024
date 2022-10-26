package com.codestates.flyaway.domain.memberimage.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.codestates.flyaway.domain.member.entity.Member;
import com.codestates.flyaway.domain.memberimage.MemberImage;
import com.codestates.flyaway.domain.memberimage.repository.MemberImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class MemberImageService {

    @Value("${cloud.aws.s3.bucket}/member")
    private String bucket;
    @Value("${cloud.aws.s3.default}")
    private String defaultUrl;

    private final AmazonS3 amazonS3;
    private final MemberImageRepository imageRepository;

    /**
     * S3 이미지 업로드
     * @param multipartFile
     * @return 생성된 image 객체
     */
    public MemberImage upload(MultipartFile multipartFile) throws IOException {

        String fileOriName = multipartFile.getOriginalFilename();
        String s3FileName = UUID.randomUUID().toString() + "-" + fileOriName;

        ObjectMetadata objMeta = new ObjectMetadata();
        objMeta.setContentLength(multipartFile.getInputStream().available());

        amazonS3.putObject(bucket, s3FileName, multipartFile.getInputStream(), objMeta);
        log.info("파일 업로드 성공 = {}", s3FileName);

        String s3Url = amazonS3.getUrl(bucket, s3FileName).toString();
        return new MemberImage(fileOriName, s3Url, s3FileName);
    }

    /**
     * 파일 반환
     * @return 파일 url
     */
    public String getImageUrl(Member member) {

        return Optional.ofNullable(member.getMemberImage())
                .map(MemberImage::getFileUrl)
                .orElseGet(() -> defaultUrl);
    }

    /**
     * 파일 삭제
     */
    public void delete(long id) {
        imageRepository.deleteById(id);
    }
}
