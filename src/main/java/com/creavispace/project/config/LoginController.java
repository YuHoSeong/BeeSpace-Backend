package com.creavispace.project.config;

import com.creavispace.project.config.auth.dto.SessionMember;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    @GetMapping("/")
    public void index(Model model, HttpServletResponse response, HttpServletRequest request) {
        SessionMember member = (SessionMember) httpSession.getAttribute("member");

        if (member != null) {
            model.addAttribute("memberName", member.getName());
        }
        System.out.println("-----------------------세션----------------------");
        System.out.println(member);

        String sessionId = UUID.randomUUID().toString();
        HttpSession session = request.getSession(true);
        session.setAttribute("memberSessionId", member);
        SessionMember sessionMember = (SessionMember) httpSession.getAttribute("member");
        sessionStore.put(sessionId, sessionMember);
        Cookie cookie = new Cookie("memberSession", sessionId);
        response.addCookie(cookie);
        System.out.println("sessionMember = " + sessionMember);
        httpSession.getAttributeNames().asIterator().forEachRemaining(System.out::println);
        System.out.println("----------------------index------------------");
    }
}
