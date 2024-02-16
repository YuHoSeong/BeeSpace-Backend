package com.creavispace.project.config.auth.dto;

import com.creavispace.project.domain.member.entity.Member;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String loginType;

    public SessionMember(Member member) {
        this.name = member.getMemberName();
        this.email = member.getMemberEmail();
        this.loginType = member.getLoginType();
    }
}