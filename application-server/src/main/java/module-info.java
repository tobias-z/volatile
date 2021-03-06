module com.tobiasz.applicationserver {
    requires com.tobiasz.common;
    requires org.reflections;
    requires lombok;
    requires javafx.controls;

    exports com.tobiasz.applicationserver;
    exports com.tobiasz.applicationserver.project;
    exports com.tobiasz.applicationserver.setup;
    exports com.tobiasz.applicationserver.project.listener;

    opens com.tobiasz.applicationserver.setup;
    opens com.tobiasz.applicationserver.project;
    opens com.tobiasz.applicationserver.project.listener;
    opens com.tobiasz.applicationserver;
}