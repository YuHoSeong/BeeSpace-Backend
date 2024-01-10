package com.creavispace.project.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

//이메일
//비밀번호
//사용자 이름
//사용자 닉네임
//생성일
//수정일
//계정 탈퇴
//계정 활성화
//권한
//이메일 인증
//자기소개
@Entity
@Data
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberEmail;

    @Column(nullable = false)
    private String memberPassword;

    @Column(nullable = false)
    private String memberName;

    @Column(nullable = false, unique = true)
    private String memberNickname;

    private String role;

    private String memberIntroduce;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private boolean expired;
    private boolean enabled;
    private boolean emailConfirmed;

    public Member(String memberEmail, String memberPassword, String memberName, String memberNickname) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
        expired = false;
        enabled = true;
        emailConfirmed = false;
    }

}
