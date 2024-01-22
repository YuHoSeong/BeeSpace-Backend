package com.creavispace.project.domain.member.dto.request;

import java.util.List;
import lombok.Builder;
import lombok.Data;

/**
 * 사용자가 입력 할 수 있는 데이터
 * 이메일
 * 비밀번호
 * 닉네임
 * 직무
 * 관심 기술
 */
@Data
@Builder
public class MemberSaveRequestDto {
    String memberEmail;
    String memberPassword;
    String memberNickname;
}
