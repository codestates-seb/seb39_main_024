package com.codestates.flyaway.web.record.dto;

import com.codestates.flyaway.domain.record.entity.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
public class RecordDto {

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class InsertRequestDto {
        @Positive
        @NotNull
        private long record;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class InsertResponseDto {
        private long memberId;
        private LocalDate date;
        private long record;

        public static InsertResponseDto recordToInsertResponse(long memberId, Record record) {
            return new InsertResponseDto(
                    memberId,
                    record.getDate(),
                    record.getRecord());
        }
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class RecordProfileResponseDto {
        private LocalDate date;
        private long record;
    }
}
