package com.creavispace.project.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

// 임시파일
@Getter
@Entity
public class TechStack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}