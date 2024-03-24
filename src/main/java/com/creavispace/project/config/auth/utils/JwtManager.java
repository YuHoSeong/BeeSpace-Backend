package com.creavispace.project.config.auth.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JwtManager {
    private static final Map<String, String> jsonToken = new ConcurrentHashMap<String, String>();

    public static void storeToken(String tokenName, String token) {
        jsonToken.put(tokenName, token);
    }

    public static String findToken(String tokenName) {
        String token = null;
        if (jsonToken.containsKey(tokenName)) {
            token = jsonToken.remove(tokenName);
            System.out.println("token = " + token);
        }
        return token;
    }
}

