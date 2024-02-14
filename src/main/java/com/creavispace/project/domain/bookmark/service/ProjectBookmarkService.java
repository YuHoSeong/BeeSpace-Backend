package com.creavispace.project.domain.bookmark.service;

import org.springframework.http.ResponseEntity;

public interface ProjectBookmarkService {
    public ResponseEntity<?> projectBookmark(long projectId);
}
