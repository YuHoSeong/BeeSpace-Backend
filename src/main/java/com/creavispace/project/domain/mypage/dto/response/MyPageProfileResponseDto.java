package com.creavispace.project.domain.mypage.dto.response;

import com.creavispace.project.domain.member.entity.Member;
import java.util.List;
import lombok.Getter;

@Getter
public class MyPageProfileResponseDto {
    /**
     * idTag
     * 닉네임
     * 직무
     * 자기소개
     * 경력
     *
     */
    private final String memberIdTag;
    private final String memberNickname;
    private final String introduce;
    private final Integer career;
    private final String position;
    private final String profileUrl;
    private final String message;
    private final List<String> interestedStack;

    public MyPageProfileResponseDto(Member member) {
        this.memberIdTag = member.getIdTag();
        this.memberNickname = member.getMemberNickname();
        this.introduce = member.getMemberIntroduce();
        this.career = member.getMemberCareer();
        this.position = member.getMemberPosition();
        this.interestedStack = member.getInterestedStack();
        this.message = "인증 성공";
        this.profileUrl = member.getProfileUrl();
    }

    public MyPageProfileResponseDto() {
        this.memberIdTag = null;
        this.memberNickname = null;
        this.position = null;
        this.introduce = null;
        this.career = null;
        this.profileUrl = null;
        this.interestedStack = null;
        this.message = "인증 실패";
    }
}
