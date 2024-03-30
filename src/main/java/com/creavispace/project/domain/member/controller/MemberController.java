package com.creavispace.project.domain.member.controller;

import com.creavispace.project.domain.bookmark.dto.response.BookmarkContentsResponseDto;
import com.creavispace.project.domain.bookmark.service.BookmarkService;
import com.creavispace.project.domain.comment.dto.response.CommentResponseDto;
import com.creavispace.project.domain.comment.service.CommentService;
import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.community.dto.response.CommunityResponseDto;
import com.creavispace.project.domain.community.service.CommunityService;
import com.creavispace.project.domain.feedback.service.FeedbackService;
import com.creavispace.project.domain.member.dto.response.DataResponseDto;
import com.creavispace.project.domain.member.dto.response.MemberResponseDto;
import com.creavispace.project.domain.member.service.MemberService;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.project.service.ProjectService;
import com.creavispace.project.domain.recruit.dto.response.RecruitListReadResponseDto;
import com.creavispace.project.domain.recruit.service.RecruitService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ProjectService projectService;
    private final RecruitService recruitService;
    private final CommunityService communityService;
    private final BookmarkService bookmarkService;
    private final CommentService commentService;
    private final FeedbackService feedbackService;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final JavaTimeModule javaTimeModule = new JavaTimeModule();

    @GetMapping("/read/profile")
    @Operation(summary = "사용자 아이디로 사용자 프로필 조회")
    public MemberResponseDto readMember(@RequestParam("id") Long memberId) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글
        System.out.println("MemberController.readMember");
        MemberResponseDto member = memberService.findById(memberId);
        return member;
    }

    @GetMapping("/read/contents/project")
    @Operation(summary = "사용자 아이디와 일치하는 프로젝트 게시물 조회")
    public ResponseEntity<SuccessResponseDto<List<ProjectListReadResponseDto>>> readMemberProjectContents(
            @RequestParam("id") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글
        SuccessResponseDto<List<ProjectListReadResponseDto>> memberProjectContents = projectService.readMyProjectList(
                memberId, 6, page, sortType);
        return ResponseEntity.ok().body(memberProjectContents);
    }

    @GetMapping("/read/contents/recruit")
    @Operation(summary = "사용자 아이디와 일치하는 모집 게시물 조회")
    public ResponseEntity<SuccessResponseDto<List<RecruitListReadResponseDto>>> readMemberRecruitContents(
            @RequestParam("memberId") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글

        SuccessResponseDto<List<RecruitListReadResponseDto>> memberRecruitContents = recruitService.readMyRecruitList(
                memberId, 6, page, sortType);

        return ResponseEntity.ok().body(memberRecruitContents);
    }

    @GetMapping("/read/contents/community")
    @Operation(summary = "사용자 아이디와 일치하는 커뮤니티 게시물 조회")
    public ResponseEntity<SuccessResponseDto<List<CommunityResponseDto>>> readMemberCommunityContents(
            @RequestParam("memberId") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글

        SuccessResponseDto<List<CommunityResponseDto>> memberCommunityContents = communityService.readMyCommunityList(
                memberId, 6, page, sortType);
        return ResponseEntity.ok().body(memberCommunityContents);
    }

    @GetMapping("/read/contents/bookmark")
    @Operation(summary = " 사용자 아이디로 사용자가 북마크한 게시물 검색")
    public ResponseEntity<SuccessResponseDto<BookmarkContentsResponseDto>> readMemberBookmarkContents(
            @RequestParam("memberId") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType, @RequestParam String category) throws JsonProcessingException {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글
        SuccessResponseDto<BookmarkContentsResponseDto> bookmark = bookmarkService.readMyBookmark(memberId, category);

        return ResponseEntity.ok().body(bookmark);
    }


    @GetMapping("/read/contents/feedback")
    @Operation(summary = "사용자 아이디로 사용자 프로필 조회")
    public ResponseEntity<SuccessResponseDto<List<ProjectListReadResponseDto>>> readMemberFeedbackContents(
            @RequestParam("id") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType) {
        //사용자 게시글 조회
        //프로젝트, 커뮤니티, 모집, 댓글
        SuccessResponseDto<List<ProjectListReadResponseDto>> memberProjectContents = projectService.readMyProjectList(
                memberId, 6, page, sortType);
        return ResponseEntity.ok().body(memberProjectContents);
    }

    @GetMapping("/read/contents/comment")
    public ResponseEntity<SuccessResponseDto<List<CommentResponseDto>>> readMemberComment(
            @RequestParam("memberId") Long memberId, @RequestParam Integer page,
            @RequestParam String sortType, @RequestParam String category) {
        SuccessResponseDto<List<CommentResponseDto>> listSuccessResponseDto = commentService.readMyCommentList(memberId,
                category);

        return ResponseEntity.ok().body(listSuccessResponseDto);
    }


    @GetMapping("/search")
    @Operation(summary = "닉네임 또는 아이디 태그를 포함하는 사용자 검색")
    public DataResponseDto findMember(@RequestParam String search) {
        List<MemberResponseDto> userData = memberService.findByMemberNicknameOrIdTagContaining(search);

        return new DataResponseDto(userData);
    }

    @GetMapping("/read/contents/test")
    public ResponseEntity<SuccessResponseDto<BookmarkContentsResponseDto>> test() throws JsonProcessingException {
        System.out.println("MemberController.test");
        SuccessResponseDto<BookmarkContentsResponseDto> bookmark = bookmarkService.readMyBookmark(4L, "project");
        return ResponseEntity.ok().body(bookmark);
    }
}
