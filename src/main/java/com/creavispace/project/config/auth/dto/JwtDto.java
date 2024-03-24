package com.creavispace.project.config.auth.dto;

import lombok.Getter;

@Getter
public class JwtDto {
    String jwt;
    Long memberId;

    public JwtDto(String jwt, Long memberId) {
        this.jwt = jwt;
        this.memberId = memberId;
    }


}
