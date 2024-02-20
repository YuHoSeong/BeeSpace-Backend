package com.creavispace.project.domain.feedback.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackAnswerCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackQuestionCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackAnswerCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionReadResponseDto;
import com.creavispace.project.domain.feedback.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedback")
public class FeedbackController {
    
    private final FeedbackService feedbackService;

    private final static String CREATE_FEEDBACK_QUESTION = "/question";
    private final static String READ_FEEDBACK_QUESTION = "/question";
    private final static String CREATE_FEEDBACK_ANSWER = "/answer";

    @PostMapping(CREATE_FEEDBACK_QUESTION)
    @Operation(summary = "피드백 질문 생성")
    public ResponseEntity<SuccessResponseDto<List<FeedbackQuestionCreateResponseDto>>> createFeedbackQuestion(
        @RequestParam("projectId") Long projectId, 
        @RequestBody List<FeedbackQuestionCreateRequestDto> requestBody){
        return ResponseEntity.ok().body(feedbackService.createFeedbackQuestion(projectId, requestBody));
    }

    @GetMapping(READ_FEEDBACK_QUESTION)
    @Operation(summary = "피드백 질문 리스트")
    public ResponseEntity<SuccessResponseDto<List<FeedbackQuestionReadResponseDto>>> readFeedbackQuestion(
        @RequestParam("projectId") Long projectId
    ){
        return ResponseEntity.ok().body(feedbackService.readFeedbackQuestion(projectId));
    }

    @PostMapping(CREATE_FEEDBACK_ANSWER)
    @Operation(summary = "피드백 답변 생성")
    public ResponseEntity<SuccessResponseDto<List<FeedbackAnswerCreateResponseDto>>> createFeedbackAnswer(
        @RequestBody List<FeedbackAnswerCreateRequestDto> requestBody
    ){
        return ResponseEntity.ok().body(feedbackService.createFeedbackAnswer(requestBody));
    }
}
