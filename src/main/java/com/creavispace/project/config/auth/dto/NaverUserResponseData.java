package com.creavispace.project.config.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NaverUserResponseData {
    @JsonProperty("id")
    String loginId;
    @JsonProperty("nickname")
    String nickname;
    @JsonProperty("email")
    String email;
    @JsonProperty("name")
    String name;
}

