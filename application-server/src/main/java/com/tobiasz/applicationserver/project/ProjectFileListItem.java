package com.tobiasz.applicationserver.project;

import static com.tobiasz.common.util.FXUtil.fitToParent;

import com.tobiasz.applicationserver.ApplicationServer;
import com.tobiasz.applicationserver.project.listener.DirectoryOpenListener;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import lombok.Getter;

@Getter
public class ProjectFileListItem {

    private final IconReader iconReader = ApplicationServer.getInitializerFactory().getComponent(IconReader.class);
    private TitledPane node;

    public void createItem(File file, int directoriesDeep) {
        ImageView itemIcon = new ImageView(this.iconReader.getIcon(this.getIconName(file)));
        itemIcon.setPreserveRatio(true);
        itemIcon.setFitWidth(18);
        itemIcon.setFitHeight(18);
        FlowPane flowPane = getFlowPane(file, itemIcon);
        flowPane.setHgap(3);
        if (directoriesDeep != 1) {
            flowPane.setStyle("-fx-padding: 0 0 0 " + 8 * (directoriesDeep - 1));
        }
        this.node = getTitledPane(flowPane);
        if (file.isDirectory()) {
            this.node.expandedProperty().addListener(new DirectoryOpenListener((ImageView) flowPane.getChildren().get(0), this.iconReader));
        }
    }

    private FlowPane getFlowPane(File file, ImageView itemIcon) {
        if (file.isDirectory()) {
            Image image = this.iconReader.getIcon("arrow-closed");
            ImageView imageView = new ImageView(image);
            return new FlowPane(imageView, itemIcon, new Label(file.getName()));
        }
        return new FlowPane(itemIcon, new Label(file.getName()));
    }

    private TitledPane getTitledPane(FlowPane flowPane) {
        TitledPane listItem = new TitledPane();
        listItem.setAnimated(false);
        listItem.setGraphic(flowPane);
        listItem.setExpanded(false);
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
