package com.creavispace.project.domain.feedback.entity;

import java.util.List;

import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.project.entity.Project;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackQuestion extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Project.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private String questionType;

    @OneToMany(mappedBy = "feedbackQuestion", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ChoiceItem> choiceItems;

}
