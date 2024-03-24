package com.creavispace.project.config.auth.utils;

import com.creavispace.project.config.auth.dto.JwtDto;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JwtManager {
    private static final Map<String, JwtDto> jsonToken = new ConcurrentHashMap<>();

    public static void storeToken(String tokenName, JwtDto jwtDto) {
        jsonToken.put(tokenName, jwtDto);
    }

    public static JwtDto findToken(String tokenName) {
        JwtDto token = null;
        if (jsonToken.containsKey(tokenName)) {
            token = jsonToken.remove(tokenName);
            System.out.println("token = " + token);
        }
        return token;
    }
}

