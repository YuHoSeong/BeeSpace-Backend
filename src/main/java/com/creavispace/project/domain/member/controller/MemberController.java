package com.creavispace.project.domain.member.controller;

import com.creavispace.project.config.auth.dto.SessionMember;
import com.creavispace.project.domain.member.dto.request.MemberInfoRequestDto;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.service.MemberService;
import com.creavispace.project.domain.member.dto.request.MemberUpdateRequestDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;

    @PostMapping("/save")
    public ResponseEntity<MemberSaveRequestDto> saveMember(@ModelAttribute MemberSaveRequestDto memberSaveRequestDto) {
        MemberSaveRequestDto save = service.save(memberSaveRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(save);
    }

    @PostMapping("/{memberId}/edit")
    public void editMember(@PathVariable Long memberId, @ModelAttribute MemberUpdateRequestDto updateParam) {
        MemberResponseDto member = service.findById(memberId);
        service.update(memberId, updateParam);
    }

    @PostMapping("/read/members")
    public List<Member> findAllMembers() {
        return service.findAllMembers();
    }
}
