package com.creavispace.project.domain.project.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.TechStack;
import com.creavispace.project.domain.project.dto.request.ProjectTechStackDto;

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
@Table(name = "project_tech_stack")
public class ProjectTechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = TechStack.class)
    @JoinColumn(name = "tech_stack_id", nullable = false, insertable = false, updatable = false)
    private TechStack techStack;

    @Column(name = "tech_stack_id", nullable = false)
    private Long techStackId;

    @ManyToOne(targetEntity = Project.class)
    @JoinColumn(name = "project_id", nullable = false, insertable = false, updatable = false)
    private Project project;

    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "modified_date", nullable = false)
    private LocalDateTime modifiedDate;

    public ProjectTechStack(ProjectTechStackDto dto, Long projectId){
        this.id = dto.getId();
        this.techStackId = dto.getTechStackId();
        this.projectId = projectId;
    }

    public static List<ProjectTechStack> copyList(List<ProjectTechStackDto> techStackList, Long projectId){
        List<ProjectTechStack> list = new ArrayList<>();
        for(ProjectTechStackDto dto : techStackList){
            list.add(new ProjectTechStack(dto, projectId));
        }
        return list;
    }
}