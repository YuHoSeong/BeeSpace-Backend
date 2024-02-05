package com.creavispace.project.domain.project.service;

import java.util.ArrayList;
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
import com.creavispace.project.domain.common.entity.TechStack;
import com.creavispace.project.domain.common.repository.TechStackRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberModifyRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackModifyRequestDto;
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
    private final TechStackRepository techStackRepository;
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

        // 프로젝트 맴버 정보가 있다면
        if(dto.getMemberList() != null){
            // 저장할 맴버리스트 객체 생성
            List<ProjectMember> projectMembers = new ArrayList<>();

            for(ProjectMemberCreateRequestDto projectMemberDto : dto.getMemberList()){
                // 리스트의 맴버 ID로 회원을 찾음
                Optional<Member> optionalProjectMember = memberRepository.findById(projectMemberDto.getMemberId());

                // 해당 ID에 대한 회원이 존재하지 않을 경우 실패 응답 반환
                if(optionalProjectMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "맴버 회원이 존재하지 않습니다.", 400));

                // 프로젝트 맴버의 맴버 객체 생성
                Member projectMember = optionalProjectMember.get();
                
                // 저장할 맴버를 맴버 리스트 객체에 추가
                projectMembers.add(ProjectMember.builder()
                    .member(projectMember)
                    .project(project)
                    .build());
            }
            
            // 프로젝트 맴버 저장
            projectMemberRepository.saveAll(projectMembers);
        }
        
        // 프로젝트 기술스택 정보가 있을 경우 저장
        if(dto.getTechStackList() != null){
            // 저장할 기술스택 리스트 객체 생성
            List<ProjectTechStack> projectTechStacks = new ArrayList<>();
            
            for(ProjectTechStackCreateRequestDto projectTechStackDto : dto.getTechStackList()){
                // 리스트의 기술스택 ID로 기술스택을 찾음
                Optional<TechStack> optionalProjectTechStack = techStackRepository.findById(projectTechStackDto.getTechStackId());

                // 해당 ID에 대한 기술스택이 존재하지 않을 경우 실패 응답 반환
                if(optionalProjectTechStack.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "기술스택이 존재하지 않습니다.", 400));

                // 프로젝트 기술스택의 기술스택 객체 생성
                TechStack projectTechStack = optionalProjectTechStack.get();

                // 저장할 기술스택을 기술스택 리스트 객체에 추가
                projectTechStacks.add(ProjectTechStack.builder()
                    .techStack(projectTechStack)
                    .project(project)
                    .build());
            }

            // 프로젝트 기술스택 저장
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
            

        // 생성된 프로젝트 게시글의 정보를 DTO로 변환하여 반환
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
        // todo 현재 토큰 미구현
        // jwt 토큰
        long memberId = 1;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        // 해당 ID에 대한 회원이 존재하지 않을 경우 실패 응답 반환
        if(optionalMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 회원이 존재하지 않습니다.", 400));

        // DTO의 프로젝트 ID로 프로젝트 게시글 찾아옴
        Optional<Project> optionalProject = projectRepository.findById(dto.getId());

        // 해당 ID에 대한 프로젝트 게시글이 존재하지 않을 경우 실패 응답 반환
        if(optionalProject.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "해당 프로젝트 게시글이 존재하지 않습니다.", 400));

        // Optional에서 프로젝트 게시글 객체 찾아옴
        Project project = optionalProject.get();

        // 작성자도 아니고 관리자도 아니면 실패 응답 반환
        if(project.getMember().getId() != memberId && !optionalMember.get().getRole().equals("Administrator")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new FailResponseDto(false,"글을 수정할 수 있는 권한이 없습니다.",401));
        }

        // 프로젝트 수정
        project.toBuilder()
            .kind(dto.getKind())
            .field(dto.getField())
            .title(dto.getTitle())
            .content(dto.getContent())
            .link(dto.getLink())
            .thumbnail(dto.getThumbnail())
            .bannerContent(dto.getBannerContent())
            .build();

        // 수정된 프로젝트 저장
        projectRepository.save(project);

        // MemberList가 있다면
        if(dto.getMemberList() !=null){
            // 저장할 맴버리스트 객체 생성
            List<ProjectMember> projectMembers = new ArrayList<>();

            for(ProjectMemberModifyRequestDto projectMemberDto : dto.getMemberList()){
                Optional<Member> optionalProjectMember = memberRepository.findById(projectMemberDto.getMemberId());

                // 해당 ID에 대한 회원이 존재하지 않을 경우 실패 응답 반환
                if(optionalProjectMember.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "맴버 회원이 존재하지 않습니다.", 400));

                // 프로젝트 맴버의 맴버 객체 생성
                Member projectMember = optionalProjectMember.get();
                
                // 저장할 맴버리스트 객체에 추가
                projectMembers.add(ProjectMember.builder()
                    .id(projectMemberDto.getId())
                    .member(projectMember)
                    .project(project)
                    .build());
                    
            }
            
            // 삭제된 맴버를 찾기위한 수정 프로젝트 맴버 ID 리스트 생성
            List<Long> modifiedMemberIdList = dto.getMemberList().stream()
                .map(projectMember -> projectMember.getId())
                .filter(id -> id != null)
                .collect(Collectors.toList());
    
            // 기존 프로젝트 맴버에서 삭제된 맴버만 삭제
            projectMemberRepository.deleteByNotModifyMemberIdList(modifiedMemberIdList);

            // 수정 및 추가된 프로젝트 맴버 저장
            projectMemberRepository.saveAll(projectMembers);
        }else{
            // MemberList가 없다면 프로젝트의 맴버를 모두 삭제
            projectMemberRepository.deleteByProjectId(project.getId());
        }

        // TechStackList가 있다면
        if(dto.getTechStackList() != null){
            // 저장할 기술스택 리스트 객체 생성
            List<ProjectTechStack> projectTechStacks = new ArrayList<>();
            
            for(ProjectTechStackModifyRequestDto projectTechStackDto : dto.getTechStackList()){
                // 리스트의 기술스택 ID로 기술스택을 찾음
                Optional<TechStack> optionalProjectTechStack = techStackRepository.findById(projectTechStackDto.getTechStackId());

                // 해당 ID에 대한 기술스택이 존재하지 않을 경우 실패 응답 반환
                if(optionalProjectTechStack.isEmpty()) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new FailResponseDto(false, "기술스택이 존재하지 않습니다.", 400));

                // 프로젝트 기술스택의 기술스택 객체 생성
                TechStack projectTechStack = optionalProjectTechStack.get();

                // 저장할 기술스택을 기술스택 리스트 객체에 추가
                projectTechStacks.add(ProjectTechStack.builder()
                    .id(projectTechStackDto.getId())
                    .techStack(projectTechStack)
                    .project(project)
                    .build());
            }

            // 삭제된 기술스택을 찾기위한 수정 프로젝트 기술스택 ID 리스트 생성
            List<Long> modifiedTechStackIdList = dto.getTechStackList().stream()
                .map(projectTechStack -> projectTechStack.getId())
                .filter(id -> id != null)
                .collect(Collectors.toList());

            // 기존 프로젝트 기술스택에서 삭제된 기술스택만 삭제
            projectTechStackRepository.deleteByNotModifyTechStackIdList(modifiedTechStackIdList);

            // 수정 및 추가된 프로젝트 기술스택 저장
            projectTechStackRepository.saveAll(projectTechStacks);
        }else{
            // TechStackList가 없다면 프로젝트의 기술스택을 모두 삭제
            projectTechStackRepository.deleteByProjectId(project.getId());
        }
        // 수정된 프로젝트 게시글의 프로젝트 맴버 조회
        List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(project.getId());

        // 수정된 프로젝트 게시글의 프로젝트 기술스택 조회
        List<ProjectTechStack> projectTechStacks = projectTechStackRepository.findByProjectId(project.getId());

        // 프로젝트 맴버 리스트 DTO변환
        List<ProjectMemberResponseDto> ProjectMemberDtos = projectMembers.stream()
            .map(projectMember -> new ProjectMemberResponseDto(projectMember))
            .collect(Collectors.toList());

        // 프로젝트 기술스택 리스트 DTO변환
        List<ProjectTechStackResponseDto> ProjectTechStackDtos = projectTechStacks.stream()
            .map(projectTechStack -> new ProjectTechStackResponseDto(projectTechStack))
            .collect(Collectors.toList());

        // 수정된 프로젝트 게시글의 정보를 DTO로 변환하여 반환
        ProjectModifyResponseDto modify = ProjectModifyResponseDto.builder()
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

        return ResponseEntity.ok().body(new SuccessResponseDto(true, "프로젝트 게시글의 수정이 완료되었습니다.", modify));
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
