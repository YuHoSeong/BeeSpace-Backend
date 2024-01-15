package com.creavispace.project.domain.recruit.service;

import com.creavispace.project.domain.recruit.dto.request.RecruitCreateRequestDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitCreateReponseDto;
import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.recruit.entity.RecruitImage;
import com.creavispace.project.domain.recruit.entity.RecruitPosition;
import com.creavispace.project.domain.recruit.repository.RecruitImageRepository;
import com.creavispace.project.domain.recruit.repository.RecruitPositionRepository;
import com.creavispace.project.domain.recruit.repository.RecruitRepository;
import com.creavispace.project.global.util.TimeUtil;
import creavispace.project.domain.common.dto.FailResponseDto;
import creavispace.project.domain.common.dto.SuccessResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RcruitCreateService {

    private final RecruitRepository recruitRepository;
    private final RecruitPositionRepository recruitPositionRepository;
    private final RecruitImageRepository recruitImageRepository;
    private final TimeUtil timeUtil;

    @Transactional
    public ResponseEntity createRecruit(RecruitCreateRequestDto recruitCreateRequestDto) {
        //JWT토큰을 통해 유저를 파악할 수 있는 방식으로 변경되어야함
        Member member = memberRepository.findById(recruitCreateRequestDto.getMemberId()).orElse(null);

        LocalDateTime startDay = recruitCreateRequestDto.getStartDay();

        LocalDateTime endDay = recruitCreateRequestDto.getEndDay();

        LocalDateTime end = recruitCreateRequestDto.getEnd();

        LocalDateTime currentTime = timeUtil.getCurrentLocalDate();

        if(startDay.isBefore(currentTime)) {
            return ResponseEntity.status(400)
                    .body(new FailResponseDto(false, "시작 날짜가 현재 날짜보다 이전입니다.", 400);
        }

        if(endDay.isBefore(currentTime)) {
            return ResponseEntity.status(400)
                    .body(new FailResponseDto(false, "종료 날짜가 현재 날짜보다 이전입니다.", 400));
        }

        if(endDay.isBefore(startDay)) {
            return ResponseEntity.status(400)
                    .body(new FailResponseDto(false, "종료 날짜가 시작 날짜보다 이전입니다.", 400));
        }

        if(end.isBefore(currentTime)) {
            return ResponseEntity.status(400)
                    .body(new FailResponseDto(false, "모집 마감일이 현재 날짜보다 이전입니다.", 400));
        }

        //recruit 생성
        Recruit recruit = Recruit.builder()
                .memberId(member.getId())
                .kind(recruitCreateRequestDto.getKind())
                .amount(recruitCreateRequestDto.getAmount())
                .proceedWay(recruitCreateRequestDto.getProceedWay())
                .startDay(startDay)
                .endDay(endDay)
                .workDay(recruitCreateRequestDto.getWorkDay())
                .end(end)
                .contect(recruitCreateRequestDto.getContect())
                .title(recruitCreateRequestDto.getTitle())
                .content(recruitCreateRequestDto.getContent())
                .thumbnail(recruitCreateRequestDto.getThumbnail())
                .status(true)
                .viewCount(0)
                .build();

        //recruit 저장
        recruitRepository.save(recruit);

        Long recruitId = recruit.getId();

        // RecruitPosition 저장
        if (recruitCreateRequestDto.getPositionList() != null) {
            List<RecruitPosition> recruitPositions = recruitCreateRequestDto.getPositionList().stream()
                    .map(positionDto -> RecruitPosition.builder()
                            .position(positionDto.getPosition())
                            .amount(positionDto.getAmount())
                            .now(positionDto.getNow())
                            .status(positionDto.isStatus())
                            .recruit(recruit)
                            .build())
                    .collect(Collectors.toList());

            recruitPositionRepository.saveAll(recruitPositions);
        }
        
        // RecruitImage 저장
        if (recruitCreateRequestDto.getImageList() != null) {
            List<RecruitImage> recruitImages = recruitCreateRequestDto.getImageList().stream()
                    .map(imageDto -> RecruitImage.builder()
                            .url(imageDto.getUrl())
                            .recruit(recruit)
                            .build())
                    .collect(Collectors.toList());

            recruitImageRepository.saveAll(recruitImages);
        }


        RecruitCreateReponseDto create = RecruitCreateReponseDto.builder()
                .id(recruit.getId())
                .memberNickname(member.getNickname())
                .kind(recruit.getKind())
                .amount(recruit.getAmount())
                .proceedWay(recruit.getProceedWay())
                .startDay(recruit.getStartDay())
                .endDay(recruit.getEndDay())
                .workDay(recruit.getWorkDay())
                .end(recruit.getEnd())
                .contect(recruit.getContect())
                .title(recruit.getTitle())
                .content(recruit.getContent())
                .thumbnail(recruit.getThumbnail())
                .status(recruit.isStatus())
                .viewCount(recruit.getViewCount())
                .imageList(recruit.getImageList())
                .positionList(recruit.getPositionList())
                .build();

        return ResponseEntity.ok()
                .body(new SuccessResponseDto(true, "모집 게시글 생성이 완료되었습니다.", create));
    }
}
