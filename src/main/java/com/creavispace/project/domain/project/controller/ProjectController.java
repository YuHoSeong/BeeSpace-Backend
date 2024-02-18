package com.creavispace.project.domain.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.response.PopularProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectCreateResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectModifyResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectReadResponseDto;
import com.creavispace.project.domain.project.service.ProjectService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    
    private static final String CREATE_PROJECT = "";
    private static final String MODIFY_PROJECT = "";
    private static final String DELETE_PROJECT = "/{projectId}";
    private static final String READ_POPULAR_PROJECT = "/popular";
    private static final String READ_PROJECT_LIST = "";
    private static final String READ_PROJECT = "/{projectId}";

    @PostMapping(CREATE_PROJECT)
    public ResponseEntity<SuccessResponseDto<ProjectCreateResponseDto>> createProject(@RequestBody ProjectCreateRequestDto dto) {
        return ResponseEntity.ok().body(projectService.createProject(dto));
    }

    @PutMapping(MODIFY_PROJECT)
    public ResponseEntity<SuccessResponseDto<ProjectModifyResponseDto>> modifyProject(@RequestBody ProjectModifyRequestDto dto) {
        return ResponseEntity.ok().body(projectService.modifyProject(dto));
    }
    
    @DeleteMapping(DELETE_PROJECT)
    public ResponseEntity<SuccessResponseDto<Long>> deleteProject(@PathVariable("projectId") Long projectId){
        return ResponseEntity.ok().body(projectService.deleteProject(projectId));
    }

    @GetMapping(READ_POPULAR_PROJECT)
    public ResponseEntity<SuccessResponseDto<List<PopularProjectReadResponseDto>>> readPopularProjectList() {
        return ResponseEntity.ok().body(projectService.readPopularProjectList());
    }

    @GetMapping(READ_PROJECT_LIST)
    public ResponseEntity<SuccessResponseDto<List<ProjectListReadResponseDto>>> readProjectList(
        @RequestParam(value = "size", required = false, defaultValue = "6") Integer size,
        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page
        ) {
        return ResponseEntity.ok().body(projectService.readProjectList(size, page));
    }

    @GetMapping(READ_PROJECT)
    public ResponseEntity<SuccessResponseDto<ProjectReadResponseDto>> readProject(@PathVariable("projectId") Long projectId) {
        return ResponseEntity.ok().body(projectService.readProject(projectId));
    }
    
}
