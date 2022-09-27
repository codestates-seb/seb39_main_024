package com.codestates.flyaway.web.boardimage;

import com.codestates.flyaway.domain.boardimage.entity.BoardImage;
import com.codestates.flyaway.domain.boardimage.service.BoardImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardImageController {

    private final BoardImageService boardImageService;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/image")
    public List<BoardImage> uploadImage(@RequestParam("image") List<MultipartFile> multipartFile) {

        return boardImageService.saveFiles(multipartFile);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/image/{image-id}")
    public void deleteImage(@PathVariable("image-id") Long imageId) {
        boardImageService.delete(imageId);
    }
}
