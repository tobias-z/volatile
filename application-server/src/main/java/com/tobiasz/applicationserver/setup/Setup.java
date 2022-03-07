package com.tobiasz.applicationserver.setup;

import static com.tobiasz.common.util.ReflectionUtil.getClassInstance;
import static com.tobiasz.common.util.ReflectionUtil.getConfigurationBuilder;
import static com.tobiasz.common.util.ThreadExecution.executeInParallel;

import java.lang.reflect.Modifier;
import lombok.RequiredArgsConstructor;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

@RequiredArgsConstructor
public class Setup {

    private final InitializerFactory initializerFactory;

    public void initializeComponents() {
        var reflections = new Reflections(getConfigurationBuilder(Scanners.SubTypes));
        createComponents(reflections, Initializer.class);
        executeInParallel(this.initializerFactory.getAllComponents(), Initializer::init);
    }

    private void createComponents(Reflections reflections, Class<? extends Initializer> initializerClass) {
        for (Class<? extends Initializer> aClass : reflections.getSubTypesOf(initializerClass)) {
            if (Modifier.isAbstract(aClass.getModifiers())) {
                createComponents(reflections, aClass);
                continue;
            }
            this.initializerFactory.addComponent(aClass, getClassInstance(aClass));
        }
    }

}
