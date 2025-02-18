package com.creavispace.project.common.dto.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProjectCategory {
    TEAM("팀"),
    INDIVIDUAL("개인");

    private final String subName;

    public static ProjectCategory getProjectCategory(String category) {
        if (TEAM.name().equalsIgnoreCase(category)) {
            return TEAM;
        }
        if (INDIVIDUAL.name().equalsIgnoreCase(category)) {
            return INDIVIDUAL;
        }
        return null;
    }
}
