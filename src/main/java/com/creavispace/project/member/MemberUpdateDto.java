package com.creavispace.project.member;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class MemberUpdateDto {
    private String memberPassword;
    private String memberNickname;
    private String introduce;

    public MemberUpdateDto(String memberPassword, String memberNickname, String introduce) {
        this.memberPassword = memberPassword;
        this.memberNickname = memberNickname;
        this.introduce = introduce;
    }
}
