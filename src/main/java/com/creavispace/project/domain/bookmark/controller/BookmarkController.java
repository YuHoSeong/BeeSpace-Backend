package com.creavispace.project.domain.bookmark.controller;

import com.creavispace.project.common.dto.response.SuccessResponseDto;
import com.creavispace.project.domain.bookmark.dto.response.BookmarkResponseDto;
import com.creavispace.project.domain.bookmark.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {
    
    private final BookmarkService bookmarkService;

    private static final String TOGGLE_BOOKMARK = "";
    private static final String READ_BOOKMARK = "";

    @PostMapping(TOGGLE_BOOKMARK)
    @Operation(summary = "북마크 토글 기능")
    public ResponseEntity<SuccessResponseDto<BookmarkResponseDto>> bookmark(
        @AuthenticationPrincipal String memberId,
        @RequestParam("postId") Long postId,
        @RequestParam("postType") String postType
    ) {
        log.info("/bookmark/controller : 북마크 토글 기능");
        return ResponseEntity.ok().body(bookmarkService.bookmarkToggle(memberId, postId, postType));
    }
    
    @GetMapping(READ_BOOKMARK)
    @Operation(summary = "북마크 상태 조회 기능")
    public ResponseEntity<SuccessResponseDto<BookmarkResponseDto>> readBookmark(
        @AuthenticationPrincipal String memberId,
        @RequestParam("postId") Long postId,
        @RequestParam("postType") String postType
    ){
        log.info("/bookmark/controller : 북마크 상태 조회 기능");
        return ResponseEntity.ok().body(bookmarkService.readBookmark(memberId, postId, postType));
    }
    
}
