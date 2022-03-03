package com.tobiasz.applicationserver.project.listener;

import com.tobiasz.applicationserver.project.IconReader;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DirectoryOpenListener implements ChangeListener<Boolean> {

    private final ImageView imageView;
    private final IconReader iconReader;
    private final Consumer<Boolean> updateImage;

    @Override
    public void changed(ObservableValue<? extends Boolean> observableValue, Boolean aBoolean, Boolean t1) {
        Boolean isOpen = observableValue.getValue();
        imageView.setImage(iconReader.getIcon(isOpen ? "arrow-opened" : "arrow-closed"));
        updateImage.accept(isOpen);
    }
}
