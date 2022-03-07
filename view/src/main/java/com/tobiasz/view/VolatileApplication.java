package com.tobiasz.view;

import com.tobiasz.applicationserver.ApplicationServer;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class VolatileApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        ApplicationServer.initializeApplicationServer();
        View.displayView(stage);
    }

}