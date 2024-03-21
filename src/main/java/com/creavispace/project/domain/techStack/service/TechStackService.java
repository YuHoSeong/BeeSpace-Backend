package com.creavispace.project.domain.techStack.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.techStack.dto.response.TechStackListReadResponseDto;

public interface TechStackService {
    public SuccessResponseDto<List<TechStackListReadResponseDto>> readTechStackList(String text);
}
