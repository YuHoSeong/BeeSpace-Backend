package com.creavispace.project.domain.report.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.report.dto.request.ReportCreateRequestDto;
import com.creavispace.project.domain.report.dto.response.ReportCreateResponseDto;

@Service
public class ReportServiceImpl implements ReportService{

    @Override
    public SuccessResponseDto<ReportCreateResponseDto> createReport(ReportCreateRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createReport'");
    }
    
}
