package com.creavispace.project.position;

import com.creavispace.project.member.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "position")
@NoArgsConstructor
public class Position {
    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    Member memberId;
    String position;
}
