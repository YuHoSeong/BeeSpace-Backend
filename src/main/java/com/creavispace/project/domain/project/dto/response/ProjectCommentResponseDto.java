package com.creavispace.project.domain.project.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.member.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectCommentResponseDto {
    private Long id;
    private Long memberId;
    private String memberNickName;
    private String memberProfileUrl;
    private String content;
    private LocalDateTime modifiedDate;

    public ProjectCommentResponseDto(ProjectComment projectComment){
        Member member = projectComment.getMember();
        this.id = projectComment.getId();
        this.memberId = member.getId();
        this.memberNickName = member.getMemberNickname();
        this.memberProfileUrl = member.getProfileUrl();
        this.content = projectComment.getContent();
        this.modifiedDate = projectComment.getModifiedDate();
    }

    public static List<ProjectCommentResponseDto> copyList(List<ProjectComment> comments){
        return comments.stream()
            .map(comment -> new ProjectCommentResponseDto(comment))
            .collect(Collectors.toList());
    }
}
