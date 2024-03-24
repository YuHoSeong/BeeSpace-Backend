package com.creavispace.project.domain.member.controller;

import com.creavispace.project.domain.member.dto.response.DataResponseDto;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/read/{memberId}")
    @Operation(summary = "사용자 아이디로 사용자 프로필 조회")
    public MemberResponseDto readMember(@PathVariable("memberId") Long memberId) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글
        return service.findById(memberId);
    }

    @GetMapping("/search")
    @Operation(summary = "닉네임 또는 아이디 태그를 포함하는 사용자 검색")
    public DataResponseDto findMember(@RequestParam String search) {
        List<MemberResponseDto> userData = service.findByMemberNicknameOrIdTagContaining(search);

        return new DataResponseDto(userData);
    }
}
