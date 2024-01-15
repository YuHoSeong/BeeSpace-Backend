package com.creavispace.project.domain.recruit.entity;

import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.common.entity.TechStack;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RecruitTechStack extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    @ManyToOne
    @JoinColumn(name = "recruit_id")
    private Recruit recruit;
}
