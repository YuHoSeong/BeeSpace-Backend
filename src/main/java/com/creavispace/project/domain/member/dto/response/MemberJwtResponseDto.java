package com.creavispace.project.domain.member.dto.response;

import lombok.Getter;

public record MemberJwtResponseDto(String memberEmail, String loginType) {
}
