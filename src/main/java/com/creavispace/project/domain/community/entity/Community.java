package com.creavispace.project.domain.community.entity;

import java.util.List;

import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.community.dto.request.CommunityRequestDto;
import com.creavispace.project.domain.like.entity.CommunityLike;
import com.creavispace.project.domain.member.entity.Member;

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
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Community extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String category;
    
    @Column(nullable = false)
    private String title;
    
    @Column(nullable = false)
    private String content;

    private int viewCount;

    private Boolean status;

    @OneToMany(mappedBy = "community")
    private List<CommunityHashTag> communityHashTags;

    @OneToMany(mappedBy = "community")
    private List<CommunityLike> communityLikes;

    public void modify(CommunityRequestDto dto){
        this.category = dto.getCategory();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    public void disable(){
        this.status = Boolean.FALSE;
    }
}
