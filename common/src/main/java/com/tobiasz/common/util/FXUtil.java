package com.tobiasz.common.util;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class FXUtil {

    public static void fitToParent(Node node) {
        AnchorPane.setBottomAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
    }

}
