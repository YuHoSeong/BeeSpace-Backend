package com.creavispace.project.config;

import com.creavispace.project.config.auth.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final HttpSession httpSession;
    @GetMapping("/google/login")
    public ResponseEntity<SessionMember> index(Model model) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            model.addAttribute("memberName", member.getName());
        }
        System.out.println("-----------------------세션----------------------");
        System.out.println(member);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping("/")
    public void index() {
        System.out.println("----------------------index------------------");
    }


}
