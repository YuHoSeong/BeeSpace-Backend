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
public class FeedbackQuestionCreateRequestDto {
    private String question;
    // enum으로 관리 변경
    private String type;
    private List<String> choiceItems;
    
}
