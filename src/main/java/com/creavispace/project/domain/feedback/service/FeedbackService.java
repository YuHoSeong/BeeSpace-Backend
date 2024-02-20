package com.creavispace.project.domain.feedback.service;

import java.util.List;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackAnswerCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackQuestionCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackAnswerCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionReadResponseDto;

public interface FeedbackService {
    public SuccessResponseDto<List<FeedbackQuestionCreateResponseDto>> createFeedbackQuestion(Long projectId, List<FeedbackQuestionCreateRequestDto> dtos);
    public SuccessResponseDto<List<FeedbackQuestionReadResponseDto>> readFeedbackQuestion(Long projectId);
    public SuccessResponseDto<List<FeedbackAnswerCreateResponseDto>> createFeedbackAnswer(List<FeedbackAnswerCreateRequestDto> dtos);
}
