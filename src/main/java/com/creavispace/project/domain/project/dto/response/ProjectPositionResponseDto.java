package com.creavispace.project.domain.project.dto.response;

import java.util.List;
import java.util.stream.Collectors;

import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.project.entity.ProjectMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectPositionResponseDto {
    private Long id;
    // enum 변경
    private String position;
    private List<ProjectMemberResponseDto> memberList;

    public ProjectPositionResponseDto(ProjectMember projectMember){
        Member member = projectMember.getMember();
        this.id = projectMember.getId();
        // this.memberId = member.getId();
        // this.memberEmail = member.getMemberEmail();
        // this.profileUrl = member.getProfileUrl();
        // this.positionName = projectMember.getPosition().getName();
    }

    public static List<ProjectPositionResponseDto> copyList(List<ProjectMember> projectMembers){
        return projectMembers.stream()
            .map(member -> new ProjectPositionResponseDto(member))
            .collect(Collectors.toList());
    }
}
