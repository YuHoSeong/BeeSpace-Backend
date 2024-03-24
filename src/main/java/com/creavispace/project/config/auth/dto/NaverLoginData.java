package com.creavispace.project.config.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NaverLoginData {
    @JsonProperty("resultcode")
    private Long resultCode;
    @JsonProperty("message")
    private String message;
    @JsonProperty("response")
    private NaverUserResponseData naverUserResponseData;
}

