package com.creavispace.project.domain.member.entity;

import com.creavispace.project.domain.member.dto.request.MemberSaveRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
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
    private Long id;
    @Column(nullable = false, unique = true)
    private String memberEmail;
    @Column(nullable = false)
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
        this.memberPassword = memberSaveRequestDto.getMemberPassword();
        this.memberNickname = memberSaveRequestDto.getMemberNickname();
        role = "default";
        createdDate = LocalDateTime.now();
        modifiedDate = LocalDateTime.now();
        expired = false;
        enabled = true;
    }

}
