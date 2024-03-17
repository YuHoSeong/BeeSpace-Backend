package com.creavispace.project.domain.member.entity;

import com.creavispace.project.config.auth.dto.OAuthAttributes;
import com.creavispace.project.domain.member.Role;
import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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
    @Column(nullable = false)
    private String loginId;
    @Column(nullable = false, unique = false)
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
    private String memberIntroduce;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private boolean expired;
    private boolean enabled;

    private String idTag;
    private String memberPosition;
    private Integer memberCareer;
    private List<String> interestedStack;

    public Member(MemberSaveRequestDto memberSaveRequestDto) {
        this.memberEmail = memberSaveRequestDto.getMemberEmail();
        this.memberName = memberSaveRequestDto.getMemberName();
        this.loginId = memberSaveRequestDto.getLoginId();
        this.loginType = memberSaveRequestDto.getLoginType();
        this.role = memberSaveRequestDto.getRole();
        this.memberNickname = memberSaveRequestDto.getMemberNickname();
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
        expired = false;
        enabled = true;
    }

    public Member(MemberSaveRequestDto dto, OAuthAttributes oAuthAttributes) {
        this.idTag = UUID.randomUUID().toString().substring(0,7);
        this.memberEmail = dto.getMemberEmail();
        this.memberName = dto.getMemberName();
        this.loginId = dto.getLoginId();
        this.loginType = dto.getLoginType();
        this.role = dto.getRole();
        this.memberNickname = dto.getMemberNickname();
        this.interestedStack = new ArrayList<>();
        this.memberCareer = 0;
        this.memberPosition = "";
        this.profileUrl = "";
        this.memberIntroduce= "";
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
        expired = false;
        enabled = true;

    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
