package com.creavispace.project.domain.report.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.report.dto.request.ReportCreateRequestDto;
import com.creavispace.project.domain.report.dto.response.ReportCreateResponseDto;
import com.creavispace.project.domain.report.service.ReportService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportController {
    
    private final ReportService reportService;

    private final static String CREATE_REPORT = "";

    @PostMapping(CREATE_REPORT)
    @Operation(summary = "신고하기")
    public ResponseEntity<SuccessResponseDto<ReportCreateResponseDto>> createReport(@RequestBody ReportCreateRequestDto requestBody){
        return ResponseEntity.ok().body(reportService.createReport(requestBody));
    }
}
