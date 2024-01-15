package com.creavispace.project.domain.project.entity;

import java.util.List;

import com.creavispace.project.domain.Member;
import com.creavispace.project.domain.bookmark.entity.ProjectBookmark;
import com.creavispace.project.domain.like.entity.ProjectLike;
import com.creavispace.project.domain.project.dto.request.ProjectCreateRequestDto;
import com.creavispace.project.domain.project.dto.request.ProjectModifyRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "member_id", nullable = false, insertable = false, updatable = false)
    private Member member;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "kind" , nullable = false, columnDefinition = "VARCHAR(20) COMMENT 'PERSONAL OR TEAM'")
    private String kind;

    private String field;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "banner_content", nullable = false, columnDefinition = "TEXT")
    private String bannerContent;

    private Integer viewCount;

    private Integer weekViewCount;

    private Boolean status;

    @OneToMany(mappedBy = "project")
    private List<ProjectImage> imageList;

    @OneToMany(mappedBy = "project")
    private List<ProjectLink> linkList;

    @OneToMany(mappedBy = "project")
    private List<ProjectComment> commentList;

    @OneToMany(mappedBy = "project")
    private List<ProjectBookmark> bookmarkList;

    @OneToMany(mappedBy = "project")
    private List<ProjectLike> likeList;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> memberList;

    @OneToMany(mappedBy = "project")
    private List<ProjectTechStack> techStackList;

    public Project(ProjectCreateRequestDto dto){
        // this.memberId = memberId;
        this.field = dto.getField();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.thumbnail = dto.getThumbnail();
        this.bannerContent = dto.getBannerContent();
        this.kind = dto.getKind();
        this.status = true;
        this.viewCount = 0;
        this.weekViewCount = 0;
    }

    public void modify(ProjectModifyRequestDto dto){
        this.field = dto.getField();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.thumbnail = dto.getThumbnail();
        this.bannerContent = dto.getBannerContent();
        this.kind = dto.getKind();
    }

    public void disable(){
        this.status = false;
    }

}
