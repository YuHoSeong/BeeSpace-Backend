package com.creavispace.project.domain.bookmark.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.creavispace.project.domain.bookmark.dto.response.ProjectBookmarkResponseDto;
import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.bookmark.repository.ProjectBookmarkRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectBookmarkServiceImpl implements ProjectBookmarkService{
    
    private final ProjectBookmarkRepository projectBookmarkRepository;

    @Override
    @Transactional
    public ResponseEntity projectBookmark(long projectId) {
        // todo : JWT 토큰의 member정보 사용 예정
        Long memberId =1L;
        ProjectBookmarkResponseDto projectBookmarkResponseDto;

        ProjectBookmark projectBookmark = projectBookmarkRepository.findByProjectIdAndMemberId(projectId, memberId);

        if(projectBookmark == null){
            projectBookmark = new ProjectBookmark(projectId, memberId);
            projectBookmarkRepository.save(projectBookmark);
            projectBookmarkResponseDto = new ProjectBookmarkResponseDto(true);
        }else{
            long projectBookmarkId = projectBookmark.getId();
            projectBookmarkRepository.deleteById(projectBookmarkId);
            projectBookmarkResponseDto = new ProjectBookmarkResponseDto(false);
        }
        
        return ResponseEntity.ok().body(projectBookmarkResponseDto);
    }
    
}
