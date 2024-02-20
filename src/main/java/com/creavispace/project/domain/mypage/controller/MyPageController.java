package com.creavispace.project.domain.mypage.controller;

import com.creavispace.project.domain.mypage.dto.request.MyPageModifyRequestDto;
import com.creavispace.project.domain.mypage.dto.request.MyPageRequestDto;
import com.creavispace.project.domain.mypage.dto.response.MyPageResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPageController {

    @GetMapping("/member/myPage")
    public ResponseEntity<MyPageResponseDto> readMyProfile(@ModelAttribute MyPageRequestDto requestDto) {
        //jwt인증
        return ResponseEntity.status(HttpStatus.OK).body(new MyPageResponseDto());
    }

    @PostMapping("/member/myPage/modify")
    public ResponseEntity<MyPageResponseDto> modifyProfile(@ModelAttribute MyPageModifyRequestDto myPageModifyRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(new MyPageResponseDto());
    }

}
