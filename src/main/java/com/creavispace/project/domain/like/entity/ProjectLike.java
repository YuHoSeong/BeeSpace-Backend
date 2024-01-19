package com.creavispace.project.domain.like.entity;

import com.creavispace.project.domain.project.entity.Project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProjectLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "projcet_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Column(name = "projcet_id", nullable = false)
    private Long projectId;

    public ProjectLike(Long projectId, Long memberId){
        this.projectId = projectId;
        this.memberId = memberId;
    }
}
