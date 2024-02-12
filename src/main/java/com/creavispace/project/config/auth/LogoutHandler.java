package com.creavispace.project.config.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class LogoutHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        System.out.println("------------------로그아웃------------------");
        request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
        System.out.println("request.getHeader(\"user-agent\") = " + request.getHeader("user-agent"));;
        System.out.println(request.getSession());
        System.out.println(request.getRemoteAddr());
        response.sendRedirect("/");
    }
}
