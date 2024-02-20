package com.creavispace.project.domain.search.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.search.dto.response.SearchListReadResponseDto;

@Service
public class SearchServiceImpl implements SearchService{

    @Override
    public SuccessResponseDto<List<SearchListReadResponseDto>> readSearchList(Integer size, Integer page, String text,
            String type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readSearchList'");
    }
    
}
