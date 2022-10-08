package com.codestates.flyaway.web.video.controller;

import com.codestates.flyaway.domain.video.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.codestates.flyaway.web.video.dto.VideoDto.*;

@RestController
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping("/video")
    public String addRecent(@RequestBody AddRequest request) {
        videoService.addRecent(request);
        return "시청 기록 저장";
    }

    @GetMapping("/members/{memberId}/video")
    public ListResponse getRecent(@PathVariable long memberId) {

        List<VideoList> videoList = videoService.getRecent(memberId);
        return new ListResponse(videoList);
    }
}
