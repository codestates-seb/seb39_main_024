package com.codestates.flyaway.web.record.dto;

import com.codestates.flyaway.domain.record.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Getter
public class RecordDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertRequestDto {
        @Positive
        @NotEmpty
        private long record;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsertResponseDto {
        private long memberId;
        private String date;
        private long record;

        public static InsertResponseDto recordToInsertResponse(long memberId, Record record) {
            return new InsertResponseDto(
                    memberId,
                    record.getDate(),
                    record.getRecord());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordProfileResponseDto {
        private String date;
        private long record;
    }
}
