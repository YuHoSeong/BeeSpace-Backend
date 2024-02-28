package com.creavispace.project.config;

import com.creavispace.project.config.auth.dto.JsonToken;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LoginController {

    private final HttpSession httpSession;
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    @GetMapping("/config/login")
    public ResponseEntity<JsonToken> getJwt(HttpServletResponse response) {
        Object jwt = httpSession.getAttribute("jwt");
        JsonToken jsonToken = new JsonToken(jwt);
        return ResponseEntity.status(HttpStatus.OK).body(jsonToken);
    }

}
