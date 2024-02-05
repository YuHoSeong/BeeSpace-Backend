package com.creavispace.project.domain.project.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.FailResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackDto;
import com.creavispace.project.domain.project.dto.response.PopularProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectCreateResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectMemberResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectModifyResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectTechStackResponseDto;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.entity.ProjectKind;
import com.creavispace.project.domain.project.entity.ProjectMember;
import com.creavispace.project.domain.project.entity.ProjectTechStack;
import com.creavispace.project.domain.project.repository.ProjectMemberRepository;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.domain.project.repository.ProjectTechStackRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{

    private final ProjectRepository projectRepository;
    private final MemberRepository memberRepository;
    private final ProjectMemberRepository projectMemberRepository;
    private final ProjectTechStackRepository projectTechStackRepository;

    @Override
    @Transactional
    public ResponseEntity<?> createProject(ProjectCreateRequestDto dto) {
        // todo 현재 토큰 미구현
        // jwt 토큰
        long memberId = 1;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // 해당 ID에 대한 회원이 존재하지 않을 경우 실패 응답 반환
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));

        // Optional에서 회원 객체를 가져옴
        Member member = optionalMember.get();

        // 새로운 프로젝트 게시글 생성
        Project project = Project.builder()
            .member(member)
            .kind(ProjectKind.valueOf(dto.getKind()))
            .field(dto.getField())
            .title(dto.getTitle())
            .content(dto.getContent())
            .link(dto.getLink())
            .thumbnail(dto.getThumbnail())
            .bannerContent(dto.getBannerContent())
            .viewCount(0)
            .weekViewCount(0)
            .status(true)
            .build();
        
        // 프로젝트 게시글 저장
        projectRepository.save(project);

        // 프로젝트 맴버 정보가 있을 경우 저장
        if(dto.getMemberList() != null){
            List<ProjectMember> projectMembers = dto.getMemberList().stream()
                .map(memberDto -> new ProjectMember(memberDto, project.getId()))
                .collect(Collectors.toList());
            
            projectMemberRepository.saveAll(projectMembers);
        }
        
        // 프로젝트 기술스택 정보가 있을 경우 저장
        if(dto.getTechStackList() != null){
            List<ProjectTechStack> projectTechStacks = dto.getTechStackList().stream()
                .map(techStackDto -> new ProjectTechStack(techStackDto, project.getId()))
                .collect(Collectors.toList());

            projectTechStackRepository.saveAll(projectTechStacks);
        }

        // 생성된 프로젝트 게시글의 프로젝트 맴버 리스트 정보 조회
        List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(project.getId());
        
        // 생성된 프로젝트 게시글의 프로젝트 기술스택 리스트 정보 조회
        List<ProjectTechStack> projectTechStacks = projectTechStackRepository.findByProjectId(project.getId());
        
        // 프로젝트 맴버 리스트 DTO변환
        List<ProjectMemberResponseDto> ProjectMemberDtos = projectMembers.stream()
            .map(projectMember -> new ProjectMemberResponseDto(projectMember))
            .collect(Collectors.toList());
            
        // 프로젝트 기술스택 리스트 DTO변환
        List<ProjectTechStackResponseDto> ProjectTechStackDtos = projectTechStacks.stream()
            .map(projectTechStack -> new ProjectTechStackResponseDto(projectTechStack))
            .collect(Collectors.toList());
            

        // 생성된 모집 게시글의 정보를 DTO로 변환하여 반환
        ProjectCreateResponseDto create = ProjectCreateResponseDto.builder()
            .id(project.getId())
            .kind(project.getKind())
            .field(project.getField())
            .title(project.getTitle())
            .content(project.getContent())
            .link(project.getLink())
            .thumbnail(project.getThumbnail())
            .bannerContent(project.getBannerContent())
            .viewCount(project.getViewCount())
            .createdDate(project.getCreatedDate())
            .modifiedDate(project.getModifiedDate())
            .memberList(ProjectMemberDtos)
            .techStackList(ProjectTechStackDtos)
            .build();

        // 성공적인 응답 반환
        return ResponseEntity.ok().body(new SuccessResponseDto(true, "프로젝트 게시글 생성이 완료 되었습니다.", create));
    }

    @Override
    @Transactional
    public ResponseEntity<?> modifyProject(ProjectModifyRequestDto dto) {
        // long memberId = "토큰정보";
        long projectId = dto.getId();
        List<ProjectMemberDto> memberDtoList = dto.getMemberList();
        List<ProjectTechStackDto> techStackDtoList = dto.getTechStackList();

        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null)
            return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
            // return ResponseEntity.status(404).body(new FailResponseDto(false,"게시글이 존재하지 않습니다.", 404));
        
        // if(memberId != project.getMemberId() && !member.getRole().equals("Administrator")){
        //     return ResponseEntity.status(401).body(new FailResponseDto(false,"프로젝트 게시글을 삭제할 수 있는 권한이 없습니다.", 401));
        // }
        
        project.modify(dto);
        projectRepository.save(project);

        // 맴버 삭제
        projectMemberRepository.deleteByProjectId(projectId);
        // 맴버 수정 저장
        List<ProjectMember> memberList = ProjectMember.copyList(memberDtoList, projectId);
        if(memberList != null)
            projectMemberRepository.saveAll(memberList);
            
        // 기술스택 삭제
        projectTechStackRepository.deleteByProjectId(projectId);
        // 기술스택 수정 저장
        List<ProjectTechStack> techStackList = ProjectTechStack.copyList(techStackDtoList, projectId);
        if(techStackList != null)
            projectTechStackRepository.saveAll(techStackList);

        ProjectModifyResponseDto modify = new ProjectModifyResponseDto(project);

        return ResponseEntity.ok().body(modify);
    }

    /** 
     * 프로젝트 게시글을 비활성화 하는 기능
     */
    @Override
    @Transactional
    public ResponseEntity<?> deleteProject(long projectId) {
        // todo : JWT의 정보로 project작성자와 관리자권한에 대한 확인 로직 필요
        // long memberId = "토큰정보";

        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null)
            return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
            // return ResponseEntity.status(404).body(new FailResponseDto(false,"게시글이 존재하지 않습니다.", 404));
        
        // if(memberId != project.getMemberId() && !member.getRole().equals("Administrator")){
        //     return ResponseEntity.status(401).body(new FailResponseDto(false,"프로젝트 게시글을 삭제할 수 있는 권한이 없습니다.", 401));
        // }

        project.disable();
        projectRepository.save(project);

        return ResponseEntity.ok().body("프로젝트 게시글 삭제가 완료되었습니다.");
    }

    @Override
    public ResponseEntity<?> readPopularProjectList() {

        List<Project> projectList = projectRepository.findTop5ByStatusTrueOrderByWeekViewCountDesc();

        List<PopularProjectReadResponseDto> readPopularList = PopularProjectReadResponseDto.copyList(projectList);

        return ResponseEntity.ok().body(readPopularList);
    }

    @Override
    public ResponseEntity<?> readProjectList(int size, int page) {
        Pageable pageRequest = PageRequest.of(page-1, size);
        Page<Project> pageable = projectRepository.findAllByStatusTrue(pageRequest);
        List<Project> projectList = pageable.getContent();

        List<ProjectListReadResponseDto> readList = ProjectListReadResponseDto.copyList(projectList);

        // todo : JWT 토큰이 있다면 
        // if(isJwt){
        //     for(ProjectListReadResponseDto read : readList){
        //         Long projectId = read.getId();
        //         read.setLike(projectLikeRepository.existsByProjectIdAndMemberId(projectId, memberId));
        //         read.setBookmark(projectBookmarkRepository.existsByProjectIdAndMemberId(projectId, memberId));
        //     }
        // }

        return ResponseEntity.ok().body(readList);
    }

    @Override
    public ResponseEntity readProject(long projectId) {
        Project project = projectRepository.findByIdAndStatusTrue(projectId);
        if(project == null)
            return ResponseEntity.status(404).body("게시글이 존재하지 않습니다.");
            // return ResponseEntity.status(404).body(new FailResponseDto(false,"게시글이 존재하지 않습니다.", 404));
        
        ProjectReadResponseDto read = new ProjectReadResponseDto(project);
        // todo : JWT 토큰이 있다면
        // if(isJwt){
        //     read.setLike(projectLikeRepository.existsByProjectIdAndMemberId(projectId, memberId));
        //     read.setBookmark(projectBookmarkRepository.existsByProjectIdAndMemberId(projectId, memberId));
        // }

        return ResponseEntity.ok().body(read);
    }


}
