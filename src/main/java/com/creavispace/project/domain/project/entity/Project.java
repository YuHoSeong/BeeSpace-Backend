package com.creavispace.project.domain.project.entity;

import java.util.List;

import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.comment.entity.ProjectComment;
import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.like.entity.ProjectLike;
import com.creavispace.project.domain.member.entity.Member;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder(toBuilder = true)
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Project extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    private String field;

    // enum으로 관리 변경
    @Column(nullable = false)
    private String category;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String thumbnail;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String bannerContent;

    private int viewCount;

    private int weekViewCount;

    private boolean status;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectLink> links;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectImage> images;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectComment> comments;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectBookmark> bookmarks;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectLike> likes;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectMember> members;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectTechStack> techStacks;

    public void modify(ProjectModifyRequestDto dto){
        // this.kind = dto.getKind();
        // this.field = dto.getField();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        // this.link = dto.getLink();
        this.thumbnail = dto.getThumbnail();
        this.bannerContent = dto.getBannerContent();
    }

    public void disable(){
        this.status = false;
    }

    public void addMemberList(List<ProjectMember> members){
        this.members = members;
    }

    public void addTechStackList(List<ProjectTechStack> techStacks){
        this.techStacks = techStacks;
    }

}
