package com.creavispace.project.domain.member.dto.response;

import java.util.List;
import lombok.Getter;

@Getter
public class DataResponseDto {
    private final List<MemberResponseDto> data;
    private final String message;

    public DataResponseDto(List<MemberResponseDto> data) {
        this.data = data;
        this.message = message();
    }

    private String message() {
        return "조회된 데이터 : " + data.size() + "개";
    }
}