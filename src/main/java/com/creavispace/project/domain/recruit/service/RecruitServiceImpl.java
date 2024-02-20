package com.creavispace.project.domain.recruit.service;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitCreateRequestDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitModifyRequestDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitCreateResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitDeleteResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitModifyResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitReadResponseDto;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Override
    public SuccessResponseDto<RecruitCreateResponseDto> createRecruit(RecruitCreateRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRecruit'");
    }

    @Override
    public SuccessResponseDto<RecruitModifyResponseDto> modifyRecruit(Long recruitId, RecruitModifyRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyRecruit'");
    }

    @Override
    public SuccessResponseDto<RecruitDeleteResponseDto> deleteRecruit(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecruit'");
    }

    @Override
    public SuccessResponseDto<RecruitListReadResponseDto> readRecruitList(Integer size, Integer page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecruitList'");
    }

    @Override
    public SuccessResponseDto<RecruitReadResponseDto> readRecruit(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecruit'");
    }
    
}
