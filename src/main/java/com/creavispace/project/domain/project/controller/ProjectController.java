package com.creavispace.project.domain.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
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
    
    private static final String CREATE_PROJECT = "/";
    private static final String MODIFY_PROJECT = "/";
    private static final String DELETE_PROJECT = "/{projectId}";
    private static final String READ_POPULAR_PROJECT = "/popular";
    private static final String READ_PROJECT_LIST = "/";
    private static final String READ_PROJECT = "/{projectId}";

    @PostMapping(CREATE_PROJECT)
    public ResponseEntity<?> createProject(@RequestBody ProjectCreateRequestDto dto) {
        return projectService.createProject(dto);
    }

    @PutMapping(MODIFY_PROJECT)
    public ResponseEntity<?> modifyProject(@RequestBody ProjectModifyRequestDto dto) {
        return projectService.modifyProject(dto);
    }
    
    @DeleteMapping(DELETE_PROJECT)
    public ResponseEntity<?> deleteProject(@PathVariable("projectId") long projectId){
        return projectService.deleteProject(projectId);
    }

    @GetMapping(READ_POPULAR_PROJECT)
    public ResponseEntity<?> readPopularProjectList() {
        return projectService.readPopularProjectList();
    }

    @GetMapping(READ_PROJECT_LIST)
    public ResponseEntity<?> readProjectList(
        @RequestParam(value = "size", required = false, defaultValue = "6") int size,
        @RequestParam(value = "page", required = false, defaultValue = "1") int page
        ) {
        return projectService.readProjectList(size, page);
    }

    @GetMapping(READ_PROJECT)
    public ResponseEntity<?> readProject(@PathVariable("projectId") long projectId) {
        return projectService.readProject(projectId);
    }
    


    
    
}
