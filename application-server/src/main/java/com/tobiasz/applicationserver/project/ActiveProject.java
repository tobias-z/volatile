package com.tobiasz.applicationserver.project;

import com.tobiasz.applicationserver.setup.Initializer;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

@Getter
public class ActiveProject extends Initializer {

    private final Map<String, ProjectFile> projectFileMap = new HashMap<>();
    private ProjectFile rootProjectFile;

    @Override
    public void init() {
        String activeDir = System.getProperty("user.dir");
        File root = new File(activeDir);
        this.rootProjectFile = new ProjectFile(root);
        this.rootProjectFile.createProject(this.projectFileMap, 1);
    }
}
