package com.creavispace.project.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GlobalErrorCode {
    MEMBER_NOT_FOUND(400,"해당 회원이 존재하지 않습니다."),
    TECHSTACK_NOT_FOUND(400,"해당 기술스택이 존재하지 않습니다."),
    PROJECT_NOT_FOUND(400,"해당 프로젝트가 존재하지 않습니다."),
    NOT_PERMISSMISSION(401,"권한이 없습니다."),
    NOT_PROJECT_CONTENT(400, "프로젝트 게시글이 없습니다."),
    PROJECT_COMMENT_NOT_FOUND(400, "해당 댓글이 존재하지 않습니다."),
    S3_SERVER_NOT_FOUND(500, "이미지 저장에 실패했습니다."),
    RECRUIT_NOT_FOUND(400,"해당 모집 게시글이 존재하지 않습니다."),
    NOT_RECRUIT_CONTENT(400,"모집 게시글이 없습니다."),
    COMMUNITY_NOT_FOUND(400, "해당 커뮤니티 게시글이 존재하지 않습니다."),
    NOT_COMMUNITY_CONTENT(400, "커뮤니티 게시글이 없습니다.");

    private Integer code;
    private String message;
}
