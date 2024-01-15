package com.creavispace.project.domain.project.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.creavispace.project.domain.project.entity.ProjectMember;

import lombok.Data;

@Data
public class ProjectMemberResponseDto {
    private Long id;
    private Long memberId;
    private String memberEmail;
    private String memberImage;
    private String position;

    public ProjectMemberResponseDto(ProjectMember projectMember){
        // Member member = projectMember.getMember();
        this.id = projectMember.getId();
        // this.memberId = member.getId();
        // this.memberEmail = member.getMemberEmail();
        // this.memberImage = member.getMemberImage();
        this.position = projectMember.getPosition();
    }

    public static List<ProjectMemberResponseDto> copyList(List<ProjectMember> projectMemberList){
        List<ProjectMemberResponseDto> list = new ArrayList<>();
        for(ProjectMember projectMember : projectMemberList){
            list.add(new ProjectMemberResponseDto(projectMember));
        }
        return list;
    }
}
