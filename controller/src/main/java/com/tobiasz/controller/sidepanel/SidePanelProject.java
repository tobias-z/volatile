package com.tobiasz.controller.sidepanel;

import com.tobiasz.applicationserver.ApplicationServer;
import com.tobiasz.applicationserver.project.ActiveProject;
import com.tobiasz.applicationserver.project.ProjectFile;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class SidePanelProject {

    private final ActiveProject activeProject;

    @FXML
    public AnchorPane projectPane;

    public SidePanelProject() {
        this.activeProject = ApplicationServer.getInitializerFactory().getComponent(ActiveProject.class);
    }

    @FXML
    public void initialize() {
        ProjectFile rootProjectFile = this.activeProject.getRootProjectFile();
        TitledPane node = rootProjectFile.getListItem().getNode();
        node.setExpanded(true);
        this.projectPane.getChildren().add(node);
        this.renderProjectFile(node, rootProjectFile);
    }

    public void renderProjectFile(TitledPane parent, ProjectFile projectFile) {
        VBox vBox = new VBox();
        for (ProjectFile file : projectFile.getProjectFiles()) {
            TitledPane node = file.getListItem().getNode();
            vBox.getChildren().add(node);
            this.renderProjectFile(node, file);
        }
        vBox.setStyle("-fx-padding: 0; -fx-border-width: 0; -fx-border-radius: 0;");
        parent.setContent(vBox);
    }

}
