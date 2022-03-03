package com.tobiasz.applicationserver.setup;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class InitializerFactory {

    private final static Map<Class<? extends Initializer>, Initializer> componentMap = new HashMap<>();

    public void addComponent(Class<? extends Initializer> key, Initializer value) {
         componentMap.put(key, value);
    }

    public <T extends Initializer> T getComponent(Class<T> tClass) {
        if (componentMap.containsKey(tClass)) {
            return (T) componentMap.get(tClass);
        }
        return null;
    }

    public Collection<Initializer> getAllComponents() {
        return componentMap.values();
    }
}
