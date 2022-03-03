package com.tobiasz.applicationserver;

import com.tobiasz.applicationserver.setup.InitializerFactory;
import com.tobiasz.applicationserver.setup.Setup;
import lombok.Getter;

public class ApplicationServer {

    @Getter
    private static final InitializerFactory initializerFactory = new InitializerFactory();

    public static void initializeApplicationServer() {
        new Setup(initializerFactory).initializeComponents();
    }

}
