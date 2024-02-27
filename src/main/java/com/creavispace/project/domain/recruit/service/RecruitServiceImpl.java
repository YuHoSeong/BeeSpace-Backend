package com.creavispace.project.domain.recruit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitRequestDto;
import com.creavispace.project.domain.recruit.dto.response.DeadLineRecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitDeleteResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitResponseDto;

@Service
public class RecruitServiceImpl implements RecruitService {

    @Override
    public SuccessResponseDto<RecruitResponseDto> createRecruit(RecruitRequestDto requestBody) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createRecruit'");
    }

    @Override
    public SuccessResponseDto<RecruitResponseDto> modifyRecruit(Long recruitId, RecruitRequestDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyRecruit'");
    }

    @Override
    public SuccessResponseDto<RecruitDeleteResponseDto> deleteRecruit(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteRecruit'");
    }

    @Override
    public SuccessResponseDto<List<RecruitListReadResponseDto>> readRecruitList(Integer size, Integer page) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecruitList'");
    }

    @Override
    public SuccessResponseDto<RecruitResponseDto> readRecruit(Long recruitId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readRecruit'");
    }

    @Override
    public SuccessResponseDto<List<DeadLineRecruitListReadResponseDto>> readDeadlineRecruitList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readDeadlineRecruitList'");
    }
    
}
