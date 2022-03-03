package com.tobiasz.applicationserver.project.listener;

import com.tobiasz.applicationserver.project.IconReader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectoryOpenListener implements ChangeListener<Boolean> {

    private final ImageView imageView;
    private final IconReader iconReader;

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        imageView.setImage(iconReader.getIcon(observableValue.getValue() ? "arrow-opened" : "arrow-closed"));
    }
}
