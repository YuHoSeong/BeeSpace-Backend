package com.creavispace.project.domain.project.service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.common.entity.TechStack;
import com.creavispace.project.domain.common.repository.TechStackRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.dto.request.ProjectLinkRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectMemberRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackRequestDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ProjectServiceImplTest {

    @Autowired
    ProjectService projectService;

    @Autowired
    TechStackRepository techStackRepository;

    @Autowired
    MemberRepository memberRepository;
    @Test
    @Commit
    void createProject() {
        ProjectLinkRequestDto linkRequestDto = ProjectLinkRequestDto.builder()
                .url("")
                .type("")
                .build();

        ProjectMemberRequestDto projectMemberRequestDto = ProjectMemberRequestDto.
                builder().
                memberId(4L).
                position("")
                .build();

        List<ProjectLinkRequestDto> projectLinkRequestDtos = List.of(linkRequestDto);

        ProjectTechStackRequestDto techStackRequestDto = new ProjectTechStackRequestDto(1L);

        ProjectRequestDto projectRequestDto = ProjectRequestDto.builder().category("팀 프로젝트")
                .linkDtos(projectLinkRequestDtos)
                .memberDtos(List.of(projectMemberRequestDto))
                .title("타이틀 1")
                .field("")
                .techStackDtos(List.of(techStackRequestDto))
                .bannerContent("banner")
                .thumbnail("thumnail")
                .content("테스트 내용")
                .build();

        projectService.createProject(4L, projectRequestDto);
    }

    @Test
    @Commit
    void techStack() {
        TechStack techStack =TechStack.builder().techStack("백엔드").id(2L).iconUrl("").build();
        techStackRepository.save(techStack);
    }

    @Test
    @Commit
    void readMyProject() {
        Member member = memberRepository.findById(4L).orElseThrow();

        SuccessResponseDto<List<ProjectListReadResponseDto>> listSuccessResponseDto = projectService.readMyProjectList(
                member, 6, 1, "asc");

        listSuccessResponseDto.getData().forEach(System.out::println);
    }
}