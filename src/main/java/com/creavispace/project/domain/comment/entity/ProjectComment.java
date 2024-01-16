package com.creavispace.project.domain.comment.entity;

import com.creavispace.project.domain.comment.dto.request.ProjectCommentCreateRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProjectComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    // @ManyToOne(targetEntity = Member.class)
    // @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
    // private Member member;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    public ProjectComment(ProjectCommentCreateRequestDto dto, Long memberId){
        this.projectId = dto.getProjectId();
        this.memberId = memberId;
        this.content = dto.getContent();
    }

}
