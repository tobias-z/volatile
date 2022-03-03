package com.tobiasz.applicationserver.project;

import static java.util.Objects.requireNonNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProjectFile {

    private final File file;
    private final List<ProjectFile> projectFiles = new ArrayList<>();
    private final ProjectFileListItem listItem = new ProjectFileListItem();

    public void createProject(Map<String, ProjectFile> projectFileMap, int directoriesDeep) {
        projectFileMap.put(this.file.getAbsolutePath(), this);
        this.listItem.createItem(this.file, directoriesDeep);
        if (this.file.isDirectory()) {
            int newCount = ++directoriesDeep;
            for (File listFile : requireNonNull(this.file.listFiles())) {
                ProjectFile projectFile = new ProjectFile(listFile);
                projectFile.createProject(projectFileMap, newCount);
                this.projectFiles.add(projectFile);
            }
        }
        // TODO: Can this be done in one sort?
        this.projectFiles.sort(this::byFirstChar);
        this.projectFiles.sort(this::directoriesOnTop);
    }

    private int byFirstChar(ProjectFile o1, ProjectFile o2) {
        return o1.getFile().getName().charAt(0) - o2.getFile().getName().charAt(0);
    }

    private int directoriesOnTop(ProjectFile o1, ProjectFile o2) {
        return o1.getFile().isDirectory() ? o2.getFile().isDirectory() ? 0 : -1 : 1;
    }
}
