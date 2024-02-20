package com.creavispace.project.domain.recruit.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitCreateRequestDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitModifyRequestDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitCreateResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitDeleteResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitModifyResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitReadResponseDto;

public interface RecruitService {
    public SuccessResponseDto<RecruitCreateResponseDto> createRecruit(RecruitCreateRequestDto dto);
    public SuccessResponseDto<RecruitModifyResponseDto> modifyRecruit(Long recruitId, RecruitModifyRequestDto dto);
    public SuccessResponseDto<RecruitDeleteResponseDto> deleteRecruit(Long recruitId);
    public SuccessResponseDto<List<RecruitListReadResponseDto>> readRecruitList(Integer size, Integer page);
    public SuccessResponseDto<RecruitReadResponseDto> readRecruit(Long recruitId);
}
