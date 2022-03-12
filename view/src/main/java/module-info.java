module com.tobiasz.view {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires com.tobiasz.applicationserver;
    requires java.desktop;
    requires lombok;

    exports com.tobiasz.view;
    exports com.tobiasz.view.loader;
    opens com.tobiasz.view.loader to javafx.fxml;
}
