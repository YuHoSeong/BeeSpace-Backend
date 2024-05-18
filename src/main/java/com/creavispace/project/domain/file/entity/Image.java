package com.creavispace.project.domain.file.entity;

import com.creavispace.project.domain.common.entity.BaseTimeEntity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class Image extends BaseTimeEntity {
    protected String url;
}