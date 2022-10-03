package com.codestates.flyaway.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class S3Controller {

    private final S3Upload s3Upload;

    @PostMapping("/image")
    public String postImage(@RequestPart MultipartFile multipartFile) throws IOException {

        return s3Upload.upload(multipartFile);
    }
}
