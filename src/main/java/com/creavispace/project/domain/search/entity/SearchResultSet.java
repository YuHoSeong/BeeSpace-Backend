package com.creavispace.project.domain.search.entity;

import java.time.LocalDateTime;

public interface SearchResultSet {
    String getPostType();
    Long getPostId();
    LocalDateTime getCreatedDate();
}
