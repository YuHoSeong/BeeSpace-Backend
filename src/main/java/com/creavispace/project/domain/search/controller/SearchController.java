package com.creavispace.project.domain.search.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.creavispace.project.domain.common.dto.SuccessResponseDto;
import com.creavispace.project.domain.project.dto.response.ProjectListReadResponseDto;
import com.creavispace.project.domain.search.dto.response.SearchListReadResponseDto;
import com.creavispace.project.domain.search.service.SearchService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {
    
    private final SearchService searchService;

    private final static String READ_SEARCH_LIST = "";
    private final static String READ_SEARCH_PROJECT = "/project/{projectId}";

    @GetMapping(READ_SEARCH_LIST)
    @Operation(summary = "통합 검색 리스트 조회")
    public ResponseEntity<SuccessResponseDto<List<SearchListReadResponseDto>>> readSearchList(
        @RequestParam(value = "size", required = false, defaultValue = "6") Integer size,
        @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
        @RequestParam(value = "text", required = true) String text,
        @RequestParam(value = "type", required = false) String type
    ){
        return ResponseEntity.ok().body(searchService.readSearchList(size, page, text, type));
    }

    @GetMapping(READ_SEARCH_PROJECT)
    @Operation(summary = "통합 검색 프로젝트 게시글 카드 정보 조회")
    public ResponseEntity<SuccessResponseDto<ProjectListReadResponseDto>> readSearchProject(
        @PathVariable("projectId") Long projectId
    ){
        return ResponseEntity.ok().body(searchService.readSearchProject(projectId));
    }
}
