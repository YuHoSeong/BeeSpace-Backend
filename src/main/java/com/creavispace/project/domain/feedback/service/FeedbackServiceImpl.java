package com.creavispace.project.domain.feedback.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackAnswerCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackQuestionCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackAnswerCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionReadResponseDto;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Override
    public SuccessResponseDto<List<FeedbackQuestionCreateResponseDto>> createFeedbackQuestion(Long projectId,
            List<FeedbackQuestionCreateRequestDto> dtos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFeedbackQuestion'");
    }

    @Override
    public SuccessResponseDto<List<FeedbackQuestionReadResponseDto>> readFeedbackQuestion(Long projectId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readFeedbackQuestion'");
    }

    @Override
    public SuccessResponseDto<List<FeedbackAnswerCreateResponseDto>> createFeedbackAnswer(
            List<FeedbackAnswerCreateRequestDto> dtos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createFeedbackAnswer'");
    }
    
}
