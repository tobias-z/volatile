module com.tobiasz.common {
    requires lombok;
    requires org.reflections;
    requires javafx.controls;
    requires batik.anim;
    requires batik.transcoder;
    requires batik.util;
    requires batik.bridge;
    requires commons.io;
    requires java.desktop;

    exports com.tobiasz.common.util;
}