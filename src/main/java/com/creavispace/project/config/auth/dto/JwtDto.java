package com.creavispace.project.config.auth.dto;

import lombok.Getter;

@Getter
public class JwtDto {
    String jwt;
    String memberId;
    boolean oldUser;

    public JwtDto(String jwt, String memberId, boolean memberEnabled) {
        this.jwt = jwt;
        this.memberId = memberId;
        this.oldUser = memberEnabled;
    }


}
