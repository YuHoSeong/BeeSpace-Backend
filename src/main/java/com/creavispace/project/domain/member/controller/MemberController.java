package com.creavispace.project.domain.member.controller;

import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @GetMapping("/read/members{memberId}")
    public List<Member> readMember(@PathVariable("memberId") Long memberId) {

        return service.findAllMembers();
    }
}
