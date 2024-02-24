package com.creavispace.project.config.auth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

public class JwtUtil {

    public static String createJwt(String memberEmail, String loginType, String secretKey, Long expiredTimeStampMs) {
        Claims claims = Jwts.claims();
        claims.put("memberEmail", memberEmail);
        claims.put("loginType", loginType);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiredTimeStampMs))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
