package com.codestates.flyaway.web.record.controller;

import com.codestates.flyaway.domain.record.service.RecordService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.codestates.flyaway.web.record.dto.RecordDto.*;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/record")
public class RecordController {

    private final RecordService recordService;

    @ApiOperation(value = "운동 시간 기록", notes = "운동영상 시청 시간을 회원의 운동 기록에 반영")
    @ResponseStatus(value = CREATED)
    @PostMapping("/{memberId}")
    public InsertResponse insertRecord(@PathVariable long memberId,
                                       @Validated @RequestBody InsertRequest recordDto) {
        return recordService.insertRecord(memberId, recordDto);
    }
}
