package com.creavispace.project.domain.feedback.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackAnswerCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackQuestionCreateRequestDto;
import com.creavispace.project.domain.feedback.dto.request.FeedbackQuestionModifyRequestDto;
import com.creavispace.project.domain.feedback.dto.response.ChoiceItemResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackAnswerCreateResponseDto;
import com.creavispace.project.domain.feedback.dto.response.FeedbackQuestionResponseDto;
import com.creavispace.project.domain.feedback.entity.ChoiceItem;
import com.creavispace.project.domain.feedback.entity.FeedbackAnswer;
import com.creavispace.project.domain.feedback.entity.FeedbackQuestion;
import com.creavispace.project.domain.feedback.entity.SelectedItem;
import com.creavispace.project.domain.feedback.repository.ChoiceItemRepository;
import com.creavispace.project.domain.feedback.repository.FeedbackAnswerRepository;
import com.creavispace.project.domain.feedback.repository.FeedbackQuestionRepository;
import com.creavispace.project.domain.feedback.repository.SelectedItemRepository;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.member.repository.MemberRepository;
import com.creavispace.project.domain.project.entity.Project;
import com.creavispace.project.domain.project.repository.ProjectRepository;
import com.creavispace.project.global.exception.CreaviCodeException;
import com.creavispace.project.global.exception.GlobalErrorCode;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    private final FeedbackQuestionRepository feedbackQuestionRepository;
    private final ChoiceItemRepository choiceItemRepository;
    private final FeedbackAnswerRepository feedbackAnswerRepository;
    private final SelectedItemRepository selectedItemRepository;

    @Override
    @Transactional
    public SuccessResponseDto<List<FeedbackQuestionResponseDto>> createFeedbackQuestion(Long memberId, Long projectId,
            List<FeedbackQuestionCreateRequestDto> dtos) {
        // JWT에 저장된 회원이 존재하는지
        if(!memberRepository.existsById(memberId)) throw new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND);

        Project project = projectRepository.findById(projectId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));
        
        List<FeedbackQuestion> feedbackQuestions = dtos.stream()
            .map(dto -> {
                FeedbackQuestion feedbackQuestion = FeedbackQuestion.builder()
                .project(project)
                .question(dto.getQuestion())
                .type(dto.getType())
                .build();

                feedbackQuestionRepository.save(feedbackQuestion);

                if(dto.getType().equals("객관식") || dto.getType().equals("체크박스")){
                    List<ChoiceItem> choiceItems = dto.getChoiceItems().stream()
                        .map(choiceItem -> ChoiceItem.builder()
                            .feedbackQuestion(feedbackQuestion)
                            .item(choiceItem)
                            .build())
                        .collect(Collectors.toList());
    
                    choiceItemRepository.saveAll(choiceItems);
                }

                return feedbackQuestion;
            })
            .collect(Collectors.toList());

        List<FeedbackQuestionResponseDto> create = feedbackQuestions.stream()
            .map(feedbackQuestion -> {
                List<ChoiceItem> choiceItems = choiceItemRepository.findByFeedbackQuestionId(feedbackQuestion.getId());
                
                return FeedbackQuestionResponseDto.builder()
                    .id(feedbackQuestion.getId())
                    .question(feedbackQuestion.getQuestion())
                    .type(feedbackQuestion.getType())
                    .choiceItems(choiceItems.stream()
                        .map(choiceItem -> ChoiceItemResponseDto.builder()
                            .id(choiceItem.getId())
                            .item(choiceItem.getItem())
                            .build())
                        .collect(Collectors.toList()))
                    .build();
                })
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "피드백 질문 저장이 완료되었습니다.", create);

    }

    @Override
    @Transactional
    public SuccessResponseDto<List<FeedbackQuestionResponseDto>> modifyFeedbackQuestion(Long memberId, Long projectId,
            List<FeedbackQuestionModifyRequestDto> dtos) {

        // JWT에 저장된 회원이 존재하는지
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        // 프로젝트가 존재하는지
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND));

        // 수정 권한이 있는지
        if(project.getMember().getId() != memberId && !member.getRole().equals("Administrator")){
            throw new CreaviCodeException(GlobalErrorCode.NOT_PERMISSMISSION);
        }

        if(dtos.isEmpty()) return new SuccessResponseDto<>(true, "피드백 질문 추가/삭제 저장이 완료되었습니다.", new ArrayList<FeedbackQuestionResponseDto>());

        // 수정할 피드백 질문 ID 그룹화
        List<Long> modifyFeedbackQuestionIds = dtos.stream()
            .filter(dto -> dto.getQuestionId() != null)
            .map(dto -> dto.getQuestionId())
            .collect(Collectors.toList());

        // 삭제된 피드백 질문 삭제
        if(modifyFeedbackQuestionIds != null && !modifyFeedbackQuestionIds.isEmpty())
            feedbackQuestionRepository.deleteByQuestionIds(modifyFeedbackQuestionIds);

        // modify 피드백
        List<FeedbackQuestion> feedbackQuestions = dtos.stream()
            .map(dto-> {
                if(dto.getQuestionId() != null){
                    return feedbackQuestionRepository.findById(dto.getQuestionId()).orElseThrow(() -> new CreaviCodeException(GlobalErrorCode.FEEDBACK_QUESTION_NOT_FOUND));
                }
                // 신규 피드백은 저장 후 리턴
                else{
                    // 신규 피드백 저장
                    FeedbackQuestion feedbackQuestion = FeedbackQuestion.builder()
                    .project(project)
                    .question(dto.getQuestion())
                    .type(dto.getType())
                    .build();
    
                    feedbackQuestionRepository.save(feedbackQuestion);
    
                    // 객관식 or 체크박스일 경우 선택지 저장
                    if(dto.getType().equals("객관식") || dto.getType().equals("체크박스")){
                        List<ChoiceItem> choiceItems = dto.getChoiceItems().stream()
                            .map(choiceTiem -> ChoiceItem.builder()
                                .feedbackQuestion(feedbackQuestion)
                                .item(choiceTiem)
                                .build())
                            .collect(Collectors.toList());
        
                        choiceItemRepository.saveAll(choiceItems);
                    }
                    return feedbackQuestion;
                }
            })
            .collect(Collectors.toList());

        // 피드백 질문 DTO
        List<FeedbackQuestionResponseDto> modify = feedbackQuestions.stream()
            .map(feedbackQuestion -> {
                List<ChoiceItem> choiceItems = choiceItemRepository.findByFeedbackQuestionId(feedbackQuestion.getId());

                return FeedbackQuestionResponseDto.builder()
                    .id(feedbackQuestion.getId())
                    .question(feedbackQuestion.getQuestion())
                    .type(feedbackQuestion.getType())
                    .choiceItems(choiceItems.stream()
                        .map(choiceItem -> ChoiceItemResponseDto.builder()
                            .id(choiceItem.getId())
                            .item(choiceItem.getItem())
                            .build())
                        .collect(Collectors.toList()))
                    .build();
                })
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "피드백 질문 추가/삭제 저장이 완료되었습니다.", modify);
    }

    @Override
    public SuccessResponseDto<List<FeedbackQuestionResponseDto>> readFeedbackQuestion(Long projectId) {
        
        List<FeedbackQuestion> feedbackQuestions = feedbackQuestionRepository.findByProjectId(projectId);

        List<FeedbackQuestionResponseDto> reads = feedbackQuestions.stream()
            .map(feedbackQuestion -> {
                List<ChoiceItem> choiceItems = choiceItemRepository.findByFeedbackQuestionId(feedbackQuestion.getId());
                return FeedbackQuestionResponseDto.builder()
                .id(feedbackQuestion.getId())
                .question(feedbackQuestion.getQuestion())
                .type(feedbackQuestion.getType())
                .choiceItems(choiceItems.stream()
                    .map(choiceItem -> ChoiceItemResponseDto.builder()
                        .id(choiceItem.getId())
                        .item(choiceItem.getItem())
                        .build())
                    .collect(Collectors.toList()))
                .build();
            })
            .collect(Collectors.toList());

        return new SuccessResponseDto<>(true, "피드백 질문 리스트 조회가 완료되었습니다.", reads);
    }

    @Override
    @Transactional
    public SuccessResponseDto<FeedbackAnswerCreateResponseDto> createFeedbackAnswer(
            Long memberId, Long projectId, List<FeedbackAnswerCreateRequestDto> dtos) {
        // JWT에 저장된 회원이 존재하는지
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.MEMBER_NOT_FOUND));

        // 프로젝트가 존재하는지
        if(!projectRepository.existsById(projectId)) throw new CreaviCodeException(GlobalErrorCode.PROJECT_NOT_FOUND);

        // TODO 모든 질문에 답변을 했는지 체크하는 로직 추가 필요
        
        // 피드백 답변 저장
        dtos.stream()
                .map(dto -> {
                    // 존재하는 질문인지
                    FeedbackQuestion feedbackQuestion = feedbackQuestionRepository.findById(dto.getQuestionId()).orElseThrow(() -> new CreaviCodeException(GlobalErrorCode.FEEDBACK_QUESTION_NOT_FOUND));
                    
                    // 답변했던 질문인지
                    if(feedbackAnswerRepository.existsByFeedbackQuestionIdAndMemberId(dto.getQuestionId(), memberId)) throw new CreaviCodeException(GlobalErrorCode.ALREADY_FEEDBACK_ANSWER);

                    // 질문에 대한 답변 저장
                    FeedbackAnswer feedbackAnswer = FeedbackAnswer.builder()
                        .feedbackQuestion(feedbackQuestion)
                        .member(member)
                        .answer(dto.getAnswer())
                        .build();

                    feedbackAnswerRepository.save(feedbackAnswer);

                    // 객관식이나 체크박스일 경우 선택지 답변 저장
                    if(feedbackQuestion.getType().equals("객관식") || feedbackQuestion.getType().equals("체크박스")){
                        List<SelectedItem> selectedItems = dto.getSelectedItems().stream()
                            .map(selectedItemDto -> {
                                ChoiceItem choiceItem = choiceItemRepository.findById(selectedItemDto.getId()).orElseThrow(()-> new CreaviCodeException(GlobalErrorCode.CHOICE_ITEM_NOT_FOUND));
    
                                return SelectedItem.builder()
                                    .feedbackAnswer(feedbackAnswer)
                                    .choiceItem(choiceItem)
                                    .build();
                                })
                            .collect(Collectors.toList());
    
                        selectedItemRepository.saveAll(selectedItems);
                    }

                    return feedbackAnswer;
                })
                .collect(Collectors.toList());

        FeedbackAnswerCreateResponseDto create = FeedbackAnswerCreateResponseDto.builder().projectId(projectId).build();

        return new SuccessResponseDto<>(true, "해당 프로젝트의 피드백 답변을 완료했습니다.", create);
    }
    
}
