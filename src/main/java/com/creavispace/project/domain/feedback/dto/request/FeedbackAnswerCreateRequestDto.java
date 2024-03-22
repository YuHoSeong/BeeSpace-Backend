package com.creavispace.project.domain.feedback.dto.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackAnswerCreateRequestDto {
    private Long questionId;
    private String answer;
    private List<ChoiceItemRequestDto> selectedItems;
}
