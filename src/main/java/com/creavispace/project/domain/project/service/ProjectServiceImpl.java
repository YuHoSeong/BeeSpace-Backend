package com.creavispace.project.domain.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.entity.ProjectMember;
import com.creavispace.project.domain.project.entity.ProjectLink;
import com.creavispace.project.domain.project.entity.ProjectTechStack;
import com.creavispace.project.domain.project.repository.ProjectMemberRepository;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.domain.project.repository.ProjectTechStackRepository;
import com.creavispace.project.domain.project.repository.ProjectLinkRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private ProjectRepository projectRepository;
    private ProjectMemberRepository projectMemberRepository;
    private ProjectTechStackRepository projectTechStackRepository;
    private ProjectLinkRepository projectUrlRepository;
    
    @Override
    @Transactional
    public ResponseEntity createProject(ProjectCreateRequestDto dto) {
        Project projectEntity = new Project(dto);

        projectRepository.save(projectEntity);

        Long projectId = projectEntity.getId();
        List<ProjectMember> memberList = ProjectMember.copyList(dto.getMemberList(), projectId);
        List<ProjectTechStack> test = ProjectTechStack.copyList(dto.getTechStackList(), projectId);
        List<ProjectLink> linkList = ProjectLink.copyList(dto.getLinkList(), projectId);

        if(memberList != null)
            projectMemberRepository.saveAll(memberList);
        if(test != null)
            projectTechStackRepository.saveAll(test);
        if(linkList != null)
            projectUrlRepository.saveAll(linkList);

        // ProjectCreateResponseDto create = new ProjectCreateResponseDto(projectEntity);

        return ResponseEntity.ok().body("프로젝트 게시글 생성이 완료되었습니다.");
    }
    
}
