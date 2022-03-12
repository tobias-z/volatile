package com.tobiasz.view.util;

import com.tobiasz.view.View;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testfx.framework.junit5.ApplicationTest;

public class UITest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("starting");
        View.displayView(stage);
    }
}
