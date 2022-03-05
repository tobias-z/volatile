package com.tobiasz.applicationserver.project;

import static com.tobiasz.common.util.FXUtil.fitToParent;

import com.tobiasz.applicationserver.ApplicationServer;
import com.tobiasz.applicationserver.project.listener.DirectoryOpenListener;
import java.io.File;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import lombok.Getter;

@Getter
public class ProjectFileListItem {

    private final IconReader iconReader = ApplicationServer.getInitializerFactory().getComponent(IconReader.class);
    private TitledPane node;

    public void createItem(File file, int directoriesDeep) {
        String iconName = this.getIconName(file);
        ImageView itemIcon = new ImageView(this.iconReader.getIcon(iconName));
        itemIcon.setPreserveRatio(true);
        itemIcon.setFitWidth(18);
        itemIcon.setFitHeight(18);
        itemIcon.setCache(true);
        itemIcon.setCacheHint(CacheHint.SPEED);
        FlowPane flowPane = getFlowPane(file, itemIcon);
        flowPane.setHgap(3);
        if (directoriesDeep != 1) {
            flowPane.setStyle("-fx-padding: 0 0 0 " + 8 * (directoriesDeep - 1));
        }
        this.node = getTitledPane(flowPane);
        if (file.isDirectory()) {
            addOpenChangeListener(iconName, itemIcon, flowPane);
        }
        this.node.setExpanded(false);
        this.node.setCollapsible(false);
    }

    private void addOpenChangeListener(String iconName, ImageView itemIcon, FlowPane flowPane) {
        this.node.expandedProperty().addListener(new DirectoryOpenListener(
            (ImageView) flowPane.getChildren().get(0),
            this.iconReader,
            (isOpen) -> itemIcon.setImage(
                this.iconReader.getIcon(isOpen ? String.format("%s-open", iconName) : iconName)
            ))
        );
    }

    private FlowPane getFlowPane(File file, ImageView itemIcon) {
        if (file.isDirectory()) {
            return new FlowPane(new ImageView(), itemIcon, new Label(file.getName()));
        }
        return new FlowPane(itemIcon, new Label(file.getName()));
    }

    private TitledPane getTitledPane(FlowPane flowPane) {
        TitledPane listItem = new TitledPane();
        listItem.setAnimated(false);
        listItem.setGraphic(flowPane);
        listItem.setAlignment(Pos.TOP_LEFT);
        fitToParent(listItem);
        return listItem;
    }

    private String getIconName(File file) {
        if (file.isFile()) {
            String[] split = file.getName().split("\\.");
            return split[split.length - 1];
        }
        return String.format("folder-%s", file.getName());
    }
}
