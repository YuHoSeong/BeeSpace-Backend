package com.creavispace.project.domain.mypage.dto.request;

/**
 * @param authorizationToken jwt 인증
 */
public record MyPageContentsRequestDto(String authorizationToken, Integer size, Integer page, String category) {
}

