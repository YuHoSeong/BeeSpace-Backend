package com.creavispace.project.domain.project.entity;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.dto.request.ProjectMemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "project_member")
public class ProjectMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @ManyToOne(targetEntity = Member.class)
    // @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
    // private Member member;
    
    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    private String position;

    public ProjectMember(ProjectMemberDto dto, Long projectId){
        this.id = dto.getId();
        this.memberId = dto.getMemberId();
        this.projectId = projectId;
        this.position = dto.getPosition();
    }

    public static List<ProjectMember> copyList(List<ProjectMemberDto> memberList, Long projectId){
        List<ProjectMember> list = new ArrayList<>();
        for(ProjectMemberDto dto : memberList){
            list.add(new ProjectMember(dto, projectId));
        }
        return list;
    }
}