package com.creavispace.project.domain.project.service;

import org.springframework.http.ResponseEntity;

import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;

public interface ProjectService {
    public ResponseEntity<?> createProject(ProjectCreateRequestDto dto);
    public ResponseEntity<?> modifyProject(ProjectModifyRequestDto dto);
    public ResponseEntity<?> deleteProject(Long projectId);
    public ResponseEntity<?> readPopularProjectList();
    public ResponseEntity<?> readProjectList(Integer size, Integer page);
    public ResponseEntity<?> readProject(Long projectId);
}
