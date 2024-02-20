package com.creavispace.project.domain.project.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.response.PopularProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectCreateResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectDeleteResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectModifyResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectReadResponseDto;

public interface ProjectService {
    public SuccessResponseDto<ProjectCreateResponseDto> createProject(ProjectCreateRequestDto dto);
    public SuccessResponseDto<ProjectModifyResponseDto> modifyProject(Long projectId, ProjectModifyRequestDto dto);
    public SuccessResponseDto<ProjectDeleteResponseDto> deleteProject(Long projectId);
    public SuccessResponseDto<List<PopularProjectReadResponseDto>> readPopularProjectList();
    public SuccessResponseDto<List<ProjectListReadResponseDto>> readProjectList(Integer size, Integer page);
    public SuccessResponseDto<ProjectReadResponseDto> readProject(Long projectId);
}
