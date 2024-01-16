package com.creavispace.project.domain.comment.dto.request;

import lombok.Getter;

@Getter
public class ProjectCommentCreateRequestDto {
    private Long projectId;
    private String content;
}
