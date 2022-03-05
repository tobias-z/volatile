package com.tobiasz.applicationserver.project;

import static org.assertj.core.api.Assertions.assertThat;

import javafx.scene.image.Image;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IconReaderIntegrationTest {

    private IconReader iconReader;
    private static final String FILE_NAME = "java";

    @BeforeEach
    void setUp() {
        iconReader = new IconReader();
    }

    @Test
    @DisplayName("when getIcon then return image")
    void whenGetIconThenReturnImage() throws Exception {
        Image file = iconReader.getIcon(FILE_NAME);
        assertThat(file).isNotNull();
    }

    @Test
    @DisplayName("when getIcon then image has width and height")
    void whenGetIconThenImageHasWidthAndHeight() throws Exception {
        Image file = iconReader.getIcon(FILE_NAME);
        assertThat(file.getWidth()).isGreaterThan(0);
        assertThat(file.getHeight()).isGreaterThan(0);
    }

    @Test
    @DisplayName("when given incorrect file name then return getIcon returns null")
    void whenGivenIncorrectFileNameThenReturnGetIconReturnsNull() throws Exception {
        assertThat(iconReader.getIcon("INCORRECT")).isNull();
    }

}