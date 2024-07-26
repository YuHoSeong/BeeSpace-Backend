package com.creavispace.project.domain.recruit.controller;

import com.creavispace.project.common.dto.response.SuccessResponseDto;
import com.creavispace.project.domain.recruit.dto.request.RecruitRequestDto;
import com.creavispace.project.domain.recruit.dto.response.DeadLineRecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.dto.response.RecruitReadResponseDto;
import com.creavispace.project.domain.recruit.entity.Recruit;
import com.creavispace.project.domain.recruit.service.RecruitService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {
    
    private final RecruitService recruitService;

    private static final String CREATE_RECRUIT = "";
    private static final String MODIFY_RECRUIT = "/{recruitId}";
    private static final String DELETE_RECRUIT = "/{recruitId}";
    private static final String READ_RECRUIT_LIST = "";
    private static final String READ_RECRUIT = "/{recruitId}";
    private static final String READ_DEADLINE_RECRUIT_LIST = "/deadline";

    @PostMapping(CREATE_RECRUIT)
    @Operation(summary = "모집 게시글 생성")
    public ResponseEntity<SuccessResponseDto<Long>> createRecruit(
        @AuthenticationPrincipal String memberId,
        @RequestBody RecruitRequestDto requestBody
    ){
        log.info("/recruit/controller : 모집 게시글 생성");
        return ResponseEntity.ok().body(recruitService.createRecruit(memberId, requestBody));
    }

    @PutMapping(MODIFY_RECRUIT)
    @Operation(summary = "모집 게시글 수정")
    public ResponseEntity<SuccessResponseDto<Long>> modifyRecruit(
        @AuthenticationPrincipal String memberId,
        @PathVariable("recruitId") Long recruitId, 
        @RequestBody RecruitRequestDto requestBody
    ){
        log.info("/recruit/controller : 모집 게시글 수정");
        return ResponseEntity.ok().body(recruitService.modifyRecruit(memberId, recruitId, requestBody));
    }

    @DeleteMapping(DELETE_RECRUIT)
    @Operation(summary = "모집 게시글 삭제")
    public ResponseEntity<SuccessResponseDto<Long>> deleteRecruit(
        @AuthenticationPrincipal String memberId,
        @PathVariable("recruitId") Long recruitId
    ){
        log.info("/recruit/controller : 모집 게시글 삭제");
        return ResponseEntity.ok().body(recruitService.deleteRecruit(memberId, recruitId));
    }

    @GetMapping(READ_RECRUIT_LIST)
    @Operation(summary = "모집 게시글 리스트")
    public ResponseEntity<SuccessResponseDto<List<RecruitListReadResponseDto>>> readRecruitList(
            @ParameterObject @SortDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageRequest,
            @RequestParam(value = "category", required = false) Recruit.Category category
    ){
        log.info("/recruit/controller : 모집 게시글 리스트");
        return ResponseEntity.ok().body(recruitService.readRecruitList(pageRequest, category));
    }

    @GetMapping(READ_RECRUIT)
    @Operation(summary = "모집 게시글 디테일")
    public ResponseEntity<SuccessResponseDto<RecruitReadResponseDto>> readRecruit(
        @AuthenticationPrincipal String memberId,
        @PathVariable("recruitId") Long recruitId,
        HttpServletRequest request
    ){
        log.info("/recruit/controller : 모집 게시글 디테일");
        return ResponseEntity.ok().body(recruitService.readRecruit(memberId, recruitId));
    }

    @GetMapping(READ_DEADLINE_RECRUIT_LIST)
    @Operation(summary = "모집 마감 리스트 / 모집 베너 조회")
    public ResponseEntity<SuccessResponseDto<List<DeadLineRecruitListReadResponseDto>>> readDeadlineRecruitList(){
        log.info("/recruit/controller : 모집 마감 리스트 / 모집 베너 조회");
        return ResponseEntity.ok().body(recruitService.readDeadlineRecruitList());
    }

}
