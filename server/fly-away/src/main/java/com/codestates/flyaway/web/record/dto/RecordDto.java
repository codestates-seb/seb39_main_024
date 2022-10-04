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
    public static class InsertRequest {
        @Positive
        @NotNull
        private long record;
    }

    @Getter
    @NoArgsConstructor @AllArgsConstructor
    public static class InsertResponse {
        private long memberId;
        private LocalDate date;
        private long record;

        public static InsertResponse recordToInsertResponse(long memberId, Record record) {
            return new InsertResponse(
                    memberId,
                    record.getDate(),
                    record.getRecord());
        }
    }

    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    public static class RecordProfileResponse {
        private LocalDate date;
        private long record;
    }
}
