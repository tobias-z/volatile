package com.tobiasz.applicationserver.setup;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SetupTest {

    private InitializerFactory initializerFactory;

    @BeforeEach
    void setUp() {
        this.initializerFactory = new InitializerFactory();
        new Setup(this.initializerFactory).initializeComponents();
    }

    @Test
    @DisplayName("when get all components then returns two")
    void whenGetAllComponentsThenReturnsTwo() throws Exception {
        assertThat(this.initializerFactory.getAllComponents().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("when getMessage from OneInitializer then returns message set in init method")
    void whenGetMessageFromOneInitializerThenReturnsMessageSetInInitMethod() throws Exception {
        OneInitializer component = this.initializerFactory.getComponent(OneInitializer.class);
        assertThat(component.getMessage()).isEqualTo("bob");
    }

}