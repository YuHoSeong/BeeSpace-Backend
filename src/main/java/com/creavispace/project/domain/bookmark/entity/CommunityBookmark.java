package com.creavispace.project.domain.bookmark.entity;

import com.creavispace.project.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.community.entity.Community;
import com.creavispace.project.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "community_id"}))
public class CommunityBookmark extends BaseTimeEntity implements Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Community.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "community_id", nullable = false)
    private Community community;

    @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @JsonBackReference
    Member member;

    @Setter
    boolean enable;

    LocalDateTime contentsCreatedDate;

}
