package com.creavispace.project.domain.project.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;

@Service
public interface ProjectService {
    public ResponseEntity createProject(ProjectCreateRequestDto dto);
    // public ResponseEntity modifyProject(ProjectModifyRequestDto dto);
    // public ResponseEntity deleteProject(Long projectId);
    // public ResponseEntity readProjectList(int size, int page);
    // public ResponseEntity readProject(Long projectId);
}
