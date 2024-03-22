package com.creavispace.project.domain.feedback.entity;

import java.util.List;

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
public class ChoiceItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = FeedbackQuestion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "feedback_question_id", nullable = false)
    private FeedbackQuestion feedbackQuestion;

    @Column(nullable = false)
    private String item;

    @OneToMany(mappedBy = "choiceItem", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SelectedItem> selectedItems;
}
