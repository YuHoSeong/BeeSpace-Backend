package com.creavispace.project.member.position;

import java.util.List;

public enum Positions {
    Backend("백엔드"), Frontend("프론트엔드"), Design("디자이너"), PM("PM"), DevOps("데브옵스");

    String position;
    Positions(String position) {
        this.position = position;
    }

    public static List<Positions> positions() {
        List<Positions> positions = List.of(Positions.values());
        return positions;
    }
}
