package com.creavispace.project.domain.bookmark.dto.response;

import com.creavispace.project.domain.bookmark.entity.Bookmark;
import java.util.List;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BookmarkContentsResponseDto {
    List<Bookmark> bookmark;

    public BookmarkContentsResponseDto(List<Bookmark> bookmark) {
        this.bookmark = bookmark;
    }
}
