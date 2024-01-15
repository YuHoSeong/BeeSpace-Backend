package com.creavispace.project.domain.recruit.entity;

import creavispace.project.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitBookmark extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private List<Recruit> recruitList;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private List<Member> memberList;

}
