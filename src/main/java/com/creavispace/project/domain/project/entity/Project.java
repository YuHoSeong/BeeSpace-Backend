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
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectKind kind;

    private String field;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    private String link;

    private String thumbnail;

    @Column(name = "banner_content", nullable = false, columnDefinition = "TEXT")
    private String bannerContent;

    private Integer viewCount;

    private Integer weekViewCount;

    private Boolean status;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectImage> imageList;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectComment> commentList;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectBookmark> bookmarkList;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectLike> likeList;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectMember> memberList;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<ProjectTechStack> techStackList;

    public void modify(ProjectModifyRequestDto dto){
        // this.kind = dto.getKind();
        this.field = dto.getField();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        // this.link = dto.getLink();
        this.thumbnail = dto.getThumbnail();
        this.bannerContent = dto.getBannerContent();
    }

    public void disable(){
        this.status = false;
    }

    public void addMemberList(List<ProjectMember> memberList){
        this.memberList = memberList;
    }

    public void addTechStackList(List<ProjectTechStack> techStackList){
        this.techStackList = techStackList;
    }

}
