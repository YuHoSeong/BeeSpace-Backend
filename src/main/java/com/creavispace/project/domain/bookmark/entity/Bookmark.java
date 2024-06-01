package com.creavispace.project.domain.bookmark.entity;

import com.creavispace.project.common.entity.BaseTimeEntity;
import com.creavispace.project.domain.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

public interface Bookmark{

    String postType = null;

}
