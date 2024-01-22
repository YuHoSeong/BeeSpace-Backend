package com.creavispace.project.domain.member.dto.request;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * 사용자가 수정할 수 있는 데이터
 * 비밀번호
 * 닉네임
 * 직무
 * 관심 기술
 * 자기소개
 * 프로필*/
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MemberUpdateRequestDto {
    private String memberPassword;
    private String memberNickname;
    private String introduce;
    private String profile;
}
