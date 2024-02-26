package com.creavispace.project.domain.project.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.project.dto.request.ProjectRequestDto;
import com.creavispace.project.domain.project.dto.response.PopularProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectDeleteResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectResponseDto;

public interface ProjectService {
    public SuccessResponseDto<ProjectResponseDto> createProject(ProjectRequestDto dto);
    public SuccessResponseDto<ProjectResponseDto> modifyProject(Long projectId, ProjectRequestDto dto);
    public SuccessResponseDto<ProjectDeleteResponseDto> deleteProject(Long projectId);
    public SuccessResponseDto<List<PopularProjectReadResponseDto>> readPopularProjectList();
    public SuccessResponseDto<List<ProjectListReadResponseDto>> readProjectList(Integer size, Integer page, String kind);
    public SuccessResponseDto<ProjectReadResponseDto> readProject(Long projectId);
}
