package com.creavispace.project.domain.hashTag.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.hashTag.dto.response.PopularHashTagReadResponseDto;

@Service
public class HashTagServiceImpl implements HashTagService {

    @Override
    public SuccessResponseDto<List<PopularHashTagReadResponseDto>> readPopularHashTagList() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readPopularHashTagList'");
    }
    
}
