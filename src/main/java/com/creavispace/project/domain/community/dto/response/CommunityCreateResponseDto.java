package com.creavispace.project.domain.community.dto.response;

import java.util.List;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCreateResponseDto {
    private Long id;
    private String postType;
    // enum으로 교체
    private String kind;
    private Long memberId;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String title;
    private String content;
    private List<String> hashTagList;
}
