package com.creavispace.project.domain.report.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.report.dto.request.ReportCreateRequestDto;
import com.creavispace.project.domain.report.dto.response.ReportCreateResponseDto;

public interface ReportService {
    public SuccessResponseDto<ReportCreateResponseDto> createReport(ReportCreateRequestDto dto);
}
