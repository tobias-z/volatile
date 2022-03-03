package com.tobiasz.applicationserver.project;

import static com.tobiasz.common.util.ArrayUtil.arrayOf;
import static com.tobiasz.common.util.ThreadExecution.executeInParallel;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import com.tobiasz.applicationserver.setup.Initializer;
import com.tobiasz.common.util.ResourceUtil;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import javafx.scene.image.Image;

public class IconReader extends Initializer {

    private static final String BASE_FOLDER = "folder-base";
    private static final String BASE_FILE = "file";
    private final ConcurrentHashMap<String, Image> iconMap = new ConcurrentHashMap<>();

    public IconReader() {
        executeInParallel(List.of(
            arrayOf(BASE_FOLDER),
            arrayOf(BASE_FILE),
            arrayOf("folder-app", "folder-application"),
            arrayOf("folder-intellij", "folder-.idea")
        ), strings -> {
            String iconName = strings[0];
            this.loadIcon(iconName, Arrays.copyOfRange(strings, 1, strings.length));
        });
    }

    @Override
    public void init() {
    }

    public Image getIcon(String iconName) {
        if (!iconMap.containsKey(iconName)) {
            this.loadIcon(iconName);
        }
        return this.findIcon(iconName);
    }

    private Image findIcon(String iconName) {
        Image svgIcon = this.iconMap.get(iconName);
        if (nonNull(svgIcon)) {
            return svgIcon;
        }
        if (iconName.startsWith("folder")) {
            return this.iconMap.get(BASE_FOLDER);
        }
        return this.iconMap.get(BASE_FILE);
    }

    private void loadIcon(String iconName, String... abbreviations) {
        Image svgIcon = ResourceUtil.getSvgIcon(iconName);
        if (isNull(svgIcon)) {
            return;
        }
        this.iconMap.put(iconName, svgIcon);
        for (String abbreviation : abbreviations) {
            this.iconMap.put(abbreviation, svgIcon);
        }
    }
}
