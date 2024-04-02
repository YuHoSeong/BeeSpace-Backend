package com.creavispace.project.domain.member;

import com.creavispace.project.domain.bookmark.dto.response.BookmarkContentsResponseDto;
import com.creavispace.project.domain.bookmark.service.BookmarkService;
import com.creavispace.project.domain.common.dto.response.SuccessResponseDto;
import com.creavispace.project.domain.member.controller.MemberController;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.service.MemberService;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberControllerTest {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private MemberService memberService;

    @Autowired
    private BookmarkService bookmarkService;
    @Test
    void projectServiceTest() throws JsonProcessingException {
        MemberResponseDto member = memberService.findById(4L);
        System.out.println("member = " + member);
        SuccessResponseDto<List<ProjectListReadResponseDto>> listSuccessResponseDto = projectService.readMyProjectList(
                4L, 2, 1, "asc");
        listSuccessResponseDto.getData().forEach(a -> System.out.println("a = " + a));
    }

}
