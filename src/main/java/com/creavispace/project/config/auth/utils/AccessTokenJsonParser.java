package com.creavispace.project.config.auth.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;


@ToString
@Data
public class AccessTokenJsonParser {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private String expiresIn;
    private String message;

//    public AccessTokenJsonParser(){
//        this.message = "토큰 획득 성공";
//    }
//    public AccessTokenJsonParser(Exception e) {
//        this.message = "토큰 획득 실패";
//    }
//
//    public AccessTokenJsonParser(String accessToken, String refreshToken, String tokenType, String expiresIn) {
//        this.accessToken = accessToken;
//        this.refreshToken = refreshToken;
//        this.tokenType = tokenType;
//        this.expiresIn = expiresIn;
//        this.message = "토큰 획득 성공";
//    }
}

