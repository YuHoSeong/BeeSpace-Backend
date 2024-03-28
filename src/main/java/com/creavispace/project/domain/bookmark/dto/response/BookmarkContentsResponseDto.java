package com.creavispace.project.domain.bookmark.dto.response;

import com.creavispace.project.global.util.JsonMapper;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookmarkContentsResponseDto {
    String bookmark;

    public BookmarkContentsResponseDto(Object bookmark) {
        this.bookmark = JsonMapper.mapping(bookmark);
    }
}
