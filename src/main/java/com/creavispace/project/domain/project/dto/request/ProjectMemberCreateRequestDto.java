package com.creavispace.project.domain.project.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberCreateRequestDto {
    Long memberId;
    String position;
}
