package com.creavispace.project.domain.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostType {
    PROJECT("project"),
    COMMUNITY("community"),
    RECRUIT("recruit");

    private final String name;

}
