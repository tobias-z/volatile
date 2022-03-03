package com.tobiasz.view;

import com.tobiasz.applicationserver.ApplicationServer;
import com.tobiasz.view.loader.StyleSheetLoader;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VolatileApplication extends Application {

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        ApplicationServer.initializeApplicationServer();
        showApplication(stage);
    }

    private void showApplication(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VolatileApplication.class.getResource("src/main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        new StyleSheetLoader(scene).load();
        stage.setTitle("Volatile IDE");
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setResizable(true);
        stage.show();
    }

}