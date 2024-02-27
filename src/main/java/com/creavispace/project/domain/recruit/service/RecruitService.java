package com.creavispace.project.domain.recruit.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitRequestDto;
import com.creavispace.project.domain.recruit.dto.response.DeadLineRecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitDeleteResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitResponseDto;

public interface RecruitService {
    public SuccessResponseDto<RecruitResponseDto> createRecruit(RecruitRequestDto dto);
    public SuccessResponseDto<RecruitResponseDto> modifyRecruit(Long recruitId, RecruitRequestDto dto);
    public SuccessResponseDto<RecruitDeleteResponseDto> deleteRecruit(Long recruitId);
    public SuccessResponseDto<List<RecruitListReadResponseDto>> readRecruitList(Integer size, Integer page);
    public SuccessResponseDto<RecruitResponseDto> readRecruit(Long recruitId);
    public SuccessResponseDto<List<DeadLineRecruitListReadResponseDto>> readDeadlineRecruitList();
}
