package com.creavispace.project.domain.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.repository.ProjectBookmarkRepository;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.common.entity.TechStack;
import com.creavispace.project.domain.common.repository.TechStackRepository;
import com.creavispace.project.domain.like.repository.ProjectLikeRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackRequestDto;
import com.creavispace.project.domain.project.dto.response.PopularProjectReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectCreateResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectDeleteResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectPositionResponseDto;
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
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

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
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectBookmarkRepository projectBookmarkRepository;

    @Override
    @Transactional
    public SuccessResponseDto<ProjectCreateResponseDto> createProject(ProjectCreateRequestDto dto) {
        // todo 현재 토큰 미구현
        // jwt 토큰
        long memberId = 1;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        // 새로운 프로젝트 게시글 생성
        Project project = Project.builder()
            .member(member)
            .kind(ProjectKind.valueOf(dto.getKind()))
            .field(dto.getField())
            .title(dto.getTitle())
            .content(dto.getContent())
            // .link(dto.getLink())
            .thumbnail(dto.getThumbnail())
            .bannerContent(dto.getBannerContent())
            .viewCount(0)
            .weekViewCount(0)
            .status(true)
            .build();
        
        // 프로젝트 게시글 저장
        projectRepository.save(project);

        // 프로젝트 맴버 정보가 있다면
        if(dto.getProjectMemberList() != null){
            // 저장할 맴버리스트 객체 생성
            List<ProjectMember> projectMembers = new ArrayList<>();

            for(ProjectMemberRequestDto projectMemberDto : dto.getProjectMemberList()){
                // 리스트의 맴버 ID로 회원을 찾음
                Optional<Member> optionalProjectMember = memberRepository.findById(projectMemberDto.getMemberId());

                Member projectMember = optionalProjectMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
                
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
        if(dto.getProjectTechStackList() != null){
            // 저장할 기술스택 리스트 객체 생성
            List<ProjectTechStack> projectTechStacks = new ArrayList<>();
            
            for(ProjectTechStackRequestDto projectTechStackDto : dto.getProjectTechStackList()){
                // 리스트의 기술스택 ID로 기술스택을 찾음
                Optional<TechStack> optionalProjectTechStack = techStackRepository.findById(projectTechStackDto.getTechStackId());

                TechStack projectTechStack = optionalProjectTechStack.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.TECHSTACK_NOT_FOUND));

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
        List<ProjectPositionResponseDto> ProjectMemberDtos = projectMembers.stream()
            .map(projectMember -> new ProjectPositionResponseDto(projectMember))
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
            // .link(project.getLink())
            .thumbnail(project.getThumbnail())
            .bannerContent(project.getBannerContent())
            .viewCount(project.getViewCount())
            .createdDate(project.getCreatedDate())
            .modifiedDate(project.getModifiedDate())
            .projectPositionList(ProjectMemberDtos)
            .projectTechStackList(ProjectTechStackDtos)
            .build();

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "프로젝트 게시글 생성이 완료 되었습니다.", create);
    }

    @Override
    @Transactional
    public SuccessResponseDto<ProjectModifyResponseDto> modifyProject(Long projectId, ProjectModifyRequestDto dto) {
        // todo 현재 토큰 미구현
        // jwt 토큰
        long memberId = 1;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        // DTO의 프로젝트 ID로 프로젝트 게시글 찾아옴
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        // 작성자도 아니고 관리자도 아니면 실패 응답 반환
        if(project.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        // 프로젝트 수정
        project.toBuilder()
            // .kind(dto.getKind())
            .field(dto.getField())
            .title(dto.getTitle())
            .content(dto.getContent())
            // .link(dto.getLink())
            .thumbnail(dto.getThumbnail())
            .bannerContent(dto.getBannerContent())
            .build();

        // 수정된 프로젝트 저장
        projectRepository.save(project);

        // // MemberList가 있다면
        // if(dto.getProjectMemberList() !=null){
        //     // 저장할 맴버리스트 객체 생성
        //     List<ProjectMember> projectMembers = new ArrayList<>();

        //     for(ProjectMemberModifyRequestDto projectMemberDto : dto.getProjectMemberList()){
        //         Optional<Member> optionalProjectMember = memberRepository.findById(projectMemberDto.getMemberId());

        //         Member projectMember = optionalProjectMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        //         // 저장할 맴버리스트 객체에 추가
        //         projectMembers.add(ProjectMember.builder()
        //             .id(projectMemberDto.getId())
        //             .member(projectMember)
        //             .project(project)
        //             .build());
                    
        //     }
            
        //     // 삭제된 맴버를 찾기위한 수정 프로젝트 맴버 ID 리스트 생성
        //     List<Long> modifiedProjectMemberIdList = dto.getProjectMemberList().stream()
        //         .map(projectMember -> projectMember.getId())
        //         .filter(id -> id != null)
        //         .collect(Collectors.toList());
    
        //     // 기존 프로젝트 맴버에서 삭제된 맴버만 삭제
        //     projectMemberRepository.deleteByNotModifyMemberIdList(modifiedProjectMemberIdList);

        //     // 수정 및 추가된 프로젝트 맴버 저장
        //     projectMemberRepository.saveAll(projectMembers);
        // }else{
        //     // MemberList가 없다면 프로젝트의 맴버를 모두 삭제
        //     projectMemberRepository.deleteByProjectId(project.getId());
        // }

        // // TechStackList가 있다면
        // if(dto.getProjectTechStackList() != null){
        //     // 저장할 기술스택 리스트 객체 생성
        //     List<ProjectTechStack> projectTechStacks = new ArrayList<>();
            
        //     for(ProjectTechStackModifyRequestDto projectTechStackDto : dto.getProjectTechStackList()){
        //         // 리스트의 기술스택 ID로 기술스택을 찾음
        //         Optional<TechStack> optionalProjectTechStack = techStackRepository.findById(projectTechStackDto.getTechStackId());

        //         TechStack projectTechStack = optionalProjectTechStack.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.TECHSTACK_NOT_FOUND));

        //         // 저장할 기술스택을 기술스택 리스트 객체에 추가
        //         projectTechStacks.add(ProjectTechStack.builder()
        //             .id(projectTechStackDto.getId())
        //             .techStack(projectTechStack)
        //             .project(project)
        //             .build());
        //     }

        //     // 삭제된 기술스택을 찾기위한 수정 프로젝트 기술스택 ID 리스트 생성
        //     List<Long> modifiedProjectTechStackIdList = dto.getProjectTechStackList().stream()
        //         .map(projectTechStack -> projectTechStack.getId())
        //         .filter(id -> id != null)
        //         .collect(Collectors.toList());

        //     // 기존 프로젝트 기술스택에서 삭제된 기술스택만 삭제
        //     projectTechStackRepository.deleteByNotModifyTechStackIdList(modifiedProjectTechStackIdList);

        //     // 수정 및 추가된 프로젝트 기술스택 저장
        //     projectTechStackRepository.saveAll(projectTechStacks);
        // }else{
        //     // TechStackList가 없다면 프로젝트의 기술스택을 모두 삭제
        //     projectTechStackRepository.deleteByProjectId(project.getId());
        // }

        // 수정된 프로젝트 게시글의 프로젝트 맴버 조회
        List<ProjectMember> projectMembers = projectMemberRepository.findByProjectId(project.getId());

        // 수정된 프로젝트 게시글의 프로젝트 기술스택 조회
        List<ProjectTechStack> projectTechStacks = projectTechStackRepository.findByProjectId(project.getId());

        // 프로젝트 맴버 리스트 DTO변환
        List<ProjectPositionResponseDto> ProjectMemberDtos = projectMembers.stream()
            .map(projectMember -> new ProjectPositionResponseDto(projectMember))
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
            // .link(project.getLink())
            .thumbnail(project.getThumbnail())
            .bannerContent(project.getBannerContent())
            .viewCount(project.getViewCount())
            .createdDate(project.getCreatedDate())
            .modifiedDate(project.getModifiedDate())
            .projectPositionList(ProjectMemberDtos)
            .projectTechStackList(ProjectTechStackDtos)
            .build();

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "프로젝트 게시글의 수정이 완료되었습니다.", modify);
    }

    @Override
    @Transactional
    public SuccessResponseDto<ProjectDeleteResponseDto> deleteProject(Long projectId) {
        // todo 현재 토큰 미구현
        // jwt 토큰
        long memberId = 1;

        // 회원 ID로 회원을 찾음
        Optional<Member> optionalMember = memberRepository.findById(memberId);

        Member member = optionalMember.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));
        
        // projectId 의 프로젝트 ID로 프로젝트 게시글 찾아옴
        Optional<Project> optionalProject = projectRepository.findByIdAndStatusTrue(projectId);

        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));
        
        // 작성자도 아니고 관리자도 아니면 실패 응답 반환
        if(project.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        // 프로젝트 비활성화 Status = false
        project.disable();

        // 프로젝트 상태 저장
        projectRepository.save(project);

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "프로젝트 게시글 삭제가 완료되었습니다.", new ProjectDeleteResponseDto(projectId));
    }

    @Override
    public SuccessResponseDto<List<PopularProjectReadResponseDto>> readPopularProjectList() {
        // 인기 프로젝트 5개 조회(주간조회수 기준)
        List<Project> projectList = projectRepository.findTop5ByStatusTrueOrderByWeekViewCountDesc();

        // 인기 프로젝트 정보를 DTO로 변환
        List<PopularProjectReadResponseDto> readPopularList = projectList.stream()
            .map(project -> new PopularProjectReadResponseDto(project))
            .collect(Collectors.toList());

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "인기 프로젝트 조회가 완료 되었습니다.", readPopularList);
    }

    @Override
    public SuccessResponseDto<List<ProjectListReadResponseDto>> readProjectList(Integer size, Integer page) {
        // 프로젝트 페이지네이션 조회
        Pageable pageRequest = PageRequest.of(page-1, size);
        Page<Project> pageable = projectRepository.findAllByStatusTrue(pageRequest);

        // 해당 페이지에 게시글이 없을 경우 실패 응답 반환
        if(!pageable.hasContent()) new CreaviCodeException(GlobalErrorCode.NOT_PROJECT_CONTENT);
        
        // 해당 페이지의 게시글 찾아옴
        List<Project> projectList = pageable.getContent();

        // 프로젝트 게시글의 정보를 DTO로 변환
        List<ProjectListReadResponseDto> readList = projectList.stream()
            .map(project -> new ProjectListReadResponseDto(project))
            .collect(Collectors.toList());

        // todo 현재 토큰 미구현
        // 토큰이 있다면
        Boolean isJwt = true;
        if(isJwt){
            // jwt 토큰
            long memberId = 1;
            // 프로젝트 게시글 정보에 좋아요, 북마크 정보 추가
            readList.stream()
                .map(project -> project.toBuilder()
                        .like(projectLikeRepository.existsByProjectIdAndMemberId(project.getId(), memberId))
                        .bookmark(projectBookmarkRepository.existsByProjectIdAndMemberId(project.getId(), memberId))
                        .build())
                .collect(Collectors.toList());
        }

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "프로젝트 게시글 리스트 조회가 완료되었습니다.", readList);
    }

    @Override
    public SuccessResponseDto<ProjectReadResponseDto> readProject(Long projectId) {
        // 해당 ID로 활성화된 프로젝트 게시글 찾아옴
        Optional<Project> optionalProject = projectRepository.findByIdAndStatusTrue(projectId);
        
        // todo 현재 토큰 미구현
        // 토큰이 있다면
        Boolean isJwt = true;
        if(isJwt){
            // jwt 토큰
            long memberId = 1;
            // 해당 프로젝트 ID의 회원정보가 토큰 회원 ID와 일치하면
            if(projectRepository.existsByIdAndMemberId(projectId,memberId)){
                // 해당 ID로 프로젝트 게시글 찾아옴
                optionalProject = projectRepository.findById(projectId);
            }
        }

        Project project = optionalProject.orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));
        
        // 프로젝트 게시글 정보를 DTO로 변환
        ProjectReadResponseDto read = new ProjectReadResponseDto(project);

        // // todo 현재 토큰 미구현
        // // 토큰이 있다면
        // if(isJwt){
        //     // jwt 토큰
        //     long memberId = 1;
        //     // 프로젝트 게시글 정보에 좋아요, 북마크 정보 추가
        //     read.toBuilder()
        //         .like(projectLikeRepository.existsByProjectIdAndMemberId(project.getId(), memberId))
        //         .bookmark(projectBookmarkRepository.existsByProjectIdAndMemberId(project.getId(), memberId))
        //         .build();
        // }

        // 성공적인 응답 반환
        return new SuccessResponseDto<>(true, "프로젝트 게시글 상세조회가 완료되었습니다.", read);
    }


}
