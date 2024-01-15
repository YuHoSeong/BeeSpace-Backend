package com.creavispace.project.domain.recruit.entity;

import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private int amount;

    private int now;

    private boolean status;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;
}
