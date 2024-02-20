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
public class CommunityReadListResponseDto {
    private Long id;
    // enum으로 교체
    private Long memberId;
    private Integer viewCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private String title;
    private String content;
    private List<String> hashTagList;
    private Integer commentCount;
}
