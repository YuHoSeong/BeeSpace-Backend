package com.creavispace.project.position;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class PositionsTest {

    @Test
    void positions() {
        List<Positions> positions = Positions.positions();
        assertThat(positions.toString()).isEqualTo("[Backend, Frontend, FullStack, Design, PM, DevOps]");
    }
}