package com.tobiasz.view;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VolatileApplicationTest {

    @Test
    @DisplayName("application starts")
    void applicationStarts() throws Exception {
        assertThat("bob").isEqualTo("bob");
    }

}