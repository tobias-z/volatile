package com.tobiasz.view.loader;

import static java.lang.Thread.currentThread;

import java.io.File;
import java.net.URL;
import javafx.scene.Scene;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StyleSheetLoader {

    private final Scene scene;

    public void load() {
        ClassLoader loader = currentThread().getContextClassLoader();
        URL url = loader.getResource("com/tobiasz/view");
        String path = url.getPath();
        this.loadDirectory(new File(path), "");
    }

    private void loadDirectory(File directory, String prevPath) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                this.loadDirectory(file, String.format("%s%s%s/", prevPath, prevPath.isEmpty() ? "" : "/", file.getName()));
                continue;
            }
            if (file.getName().split("\\.")[1].equals("css")) {
                this.scene.getStylesheets().add(String.format("file:%s", file.getAbsolutePath()));
            }
        }
    }

}
