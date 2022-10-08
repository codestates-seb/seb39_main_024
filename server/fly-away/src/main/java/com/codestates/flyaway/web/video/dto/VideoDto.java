package com.codestates.flyaway.web.video.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class VideoDto {

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class AddRequest{
        private long memberId;
        private long videoId;
        private String title;
        private String url;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class VideoList{
        private long videoId;
        private String title;
        private String url;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class ListResponse<T> {
        private List<T> data;
    }
}
