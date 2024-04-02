package com.creavispace.project.config.auth.dto;

import lombok.Getter;

@Getter
public class JwtDto {
    String jwt;
    Long memberId;
    boolean memberEnabled;

    public JwtDto(String jwt, Long memberId, boolean memberEnabled) {
        this.jwt = jwt;
        this.memberId = memberId;
        this.memberEnabled = memberEnabled;
    }


}
