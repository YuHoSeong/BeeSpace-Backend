package com.creavispace.project.domain.member.entity;

import com.creavispace.project.domain.member.Role;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
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
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //회원 관련 정보와 로그인 정보는 분리
    private Long id;
    @Column(nullable = false, unique = true)
    private String memberEmail;
    @Column(nullable = false)
    private String memberName;
    @Column(nullable = false, unique = true)
    private String memberNickname;
    private String profileUrl;
    @Column(nullable = false)
    private String loginType;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private String memberPassword;
    @Column(nullable = false, unique = true)
    private String memberNickname;
    private String profileUrl;
    private String role;
    private String memberIntroduce;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private boolean expired;
    private boolean enabled;

    public Member(MemberSaveRequestDto memberSaveRequestDto) {
        this.memberEmail = memberSaveRequestDto.getMemberEmail();
        this.memberName = memberSaveRequestDto.getMemberName();
        this.loginType = memberSaveRequestDto.getLoginType();
        this.memberNickname = memberSaveRequestDto.getMemberNickname();
        this.role = memberSaveRequestDto.getRole();
        this.memberPassword = memberSaveRequestDto.getMemberPassword();
        this.memberNickname = memberSaveRequestDto.getMemberNickname();
        role = "default";
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
        expired = false;
        enabled = true;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
