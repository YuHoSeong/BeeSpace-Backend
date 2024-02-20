package com.creavispace.project.domain.mypage.dto.response;

import java.util.HashMap;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class MyPageResponseDto {
    private HashMap<String, String> myProfile;
    private HashMap<String, List<Object>> myContents;
    private String status;
}
