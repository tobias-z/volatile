package com.tobiasz.view;

import com.tobiasz.view.loader.StyleSheetLoader;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View {

    public static void displayView(Stage stage) throws IOException {
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
