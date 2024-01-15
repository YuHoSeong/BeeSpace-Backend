package com.creavispace.project.domain.recruit.service;

import com.creavispace.project.domain.common.dto.FailResponseDto;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitModifyRequestDto;
import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecruitModifyService {

    private final RecruitRepository recruitRepository;

    @Transactional
    public ResponseEntity modifyRecruit(RecruitModifyRequestDto recruitModifyRequestDto) {
        // JWT 토큰을 통해 유저를 파악하고, 해당 유저가 수정할 권한이 있는지 판단
        Member member = "jwt토큰을 통해 가져온 멤버";

        Optional<Recruit> recruitOptional = recruitRepository.findById(recruitModifyRequestDto.getId());

        // findById의 반환값이 Optional이므로 ifPresent를 사용하여 처리
        return recruitOptional.map(recruit -> {
            // 관리자도 아니고 글 작성자도 아니면
            if (member.getId() != recruit.getMemberId() && !member.getRole().equals("Administrator")) {
                return ResponseEntity.status(401)
                        .body(new FailResponseDto(false, "글을 수정할 수 있는 권한이 없습니다.", 401));
            }

            // recruit 수정
            recruit = recruit.builder()
                    .id(recruitModifyRequestDto.getId())
                    .kind(recruitModifyRequestDto.getKind())
                    .amount(recruitModifyRequestDto.getAmount())
                    .proceedWay(recruitModifyRequestDto.getProceedWay())
                    .startDay(recruitModifyRequestDto.getStartDay())
                    .endDay(recruitModifyRequestDto.getEndDay())
                    .workDay(recruitModifyRequestDto.getWorkDay())
                    .contect(recruitModifyRequestDto.getContect())
                    .title(recruitModifyRequestDto.getTitle())
                    .content(recruitModifyRequestDto.getContent())
                    .thumbnail(recruitModifyRequestDto.getThumbnail())
                    .status(recruitModifyRequestDto.isStatus())
                    .build(recruit);

            // 수정된 recruit 저장
            recruitRepository.save(recruit);

            return ResponseEntity.ok()
                    .body(new SuccessResponseDto(true, "모집 게시글을 수정하였습니다.", recruitModifyRequestDto.getId()));
        }).orElseGet(() -> ResponseEntity.status(404)
                .body(new FailResponseDto(false, "해당 ID의 모집 게시글이 존재하지 않습니다.", 404)));
    }
}
