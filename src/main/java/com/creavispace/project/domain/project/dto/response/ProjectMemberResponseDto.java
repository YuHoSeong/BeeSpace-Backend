package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.creavispace.project.domain.project.entity.ProjectMember;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberResponseDto {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private String memberProfile;
    private String position;

    public ProjectMemberResponseDto(ProjectMember projectMember){
        // Member member = projectMember.getMember();
        this.id = projectMember.getId();
        // this.memberId = member.getId();
        // this.memberEmail = member.getMemberEmail();
        // this.memberProfile = member.getMemberProfile();
        this.position = projectMember.getPosition();
    }

    public static List<ProjectMemberResponseDto> copyList(List<ProjectMember> projectMembers){
        return projectMembers.stream()
            .map(member -> new ProjectMemberResponseDto(member))
            .collect(Collectors.toList());
    }
}
