package com.creavispace.project.domain.project.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectLinkDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackDto;
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
    private ProjectLinkRepository projectLinkRepository;
    
    @Override
    @Transactional
    public ResponseEntity createProject(ProjectCreateRequestDto dto) {
        Project projectEntity = new Project(dto);

        projectRepository.save(projectEntity);

        Long projectId = projectEntity.getId();
        List<ProjectMember> memberList = ProjectMember.copyList(dto.getMemberList(), projectId);
        List<ProjectTechStack> techStackList = ProjectTechStack.copyList(dto.getTechStackList(), projectId);
        List<ProjectLink> linkList = ProjectLink.copyList(dto.getLinkList(), projectId);

        if(memberList != null)
            projectMemberRepository.saveAll(memberList);
        if(techStackList != null)
            projectTechStackRepository.saveAll(techStackList);
        if(linkList != null)
            projectLinkRepository.saveAll(linkList);

        // ProjectCreateResponseDto create = new ProjectCreateResponseDto(projectEntity);

        return ResponseEntity.ok().body("프로젝트 게시글 생성이 완료되었습니다.");
    }

    @Override
    @Transactional
    public ResponseEntity modifyProject(ProjectModifyRequestDto dto) {
        long projectId = dto.getId();
        List<ProjectMemberDto> memberDtoList = dto.getMemberList();
        List<ProjectTechStackDto> techStackDtoList = dto.getTechStackList();
        List<ProjectLinkDto> linkDtoList = dto.getLinkList();

        Project project = projectRepository.findById(projectId).orElse(null);
        project.modify(dto);

        projectRepository.save(project);

        // 삭제된 맴버
        List<Long> modifyMemberIdList = ProjectMemberDto.modifyIdList(memberDtoList);
        projectMemberRepository.deleteByNotModifyMemberIdList(projectId, modifyMemberIdList);
        // 맴버 수정 저장
        List<ProjectMember> memberList = ProjectMember.copyList(memberDtoList, projectId);
        if(memberList != null)
            projectMemberRepository.saveAll(memberList);
            
        // 삭제된 기술스택
        List<Long> modifyTechStackIdList = ProjectTechStackDto.modifyIdList(techStackDtoList);
        projectTechStackRepository.deleteByNotModifyTechStackIdList(projectId, modifyTechStackIdList);
        // 기술스택 수정 저장
        List<ProjectTechStack> techStackList = ProjectTechStack.copyList(techStackDtoList, projectId);
        if(techStackList != null)
            projectTechStackRepository.saveAll(techStackList);

        // 삭제된 링크
        List<Long> modifyLinkIdList = ProjectLinkDto.modifyIdList(linkDtoList);
        projectLinkRepository.deleteByNotModifyLinkIdList(projectId, modifyLinkIdList);
        // 링크 수정 저장
        List<ProjectLink> linkList = ProjectLink.copyList(linkDtoList, projectId);
        if(linkList != null)
            projectLinkRepository.saveAll(linkList);

        // ProjectModifyResponseDto modify = new ProjectModifyResponseDto(project);

        return ResponseEntity.ok().body("프로젝트 게시글 수정이 완료되었습니다.");
    }
}
