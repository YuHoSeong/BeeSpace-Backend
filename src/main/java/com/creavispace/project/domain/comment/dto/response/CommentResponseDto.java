package com.creavispace.project.domain.comment.dto.response;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long memberId;
    private String memberNickName;
    private String memberProfileUrl;
    private LocalDateTime modifiedDate;
    private String content;
}
