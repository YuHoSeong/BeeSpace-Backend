package com.creavispace.project.member;

import com.creavispace.project.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    public Member saveMember(@ModelAttribute Member member) {
        if (service.emailExsists(member.getMemberEmail())) {
            return member;
        }
        service.save(member);
        return member;
    }

    @PostMapping("/{memberId}/edit")
    public void editMember(@PathVariable Long memberId, @ModelAttribute MemberUpdateDto updateParam) {
        Member member = service.findById(memberId).orElseThrow();
        service.update(memberId, updateParam);
    }

    @PostMapping("/read/members")
    public List<Member> findAllMembers() {
        return service.findAllMembers();
    }
}
