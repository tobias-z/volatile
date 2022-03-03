package com.tobiasz.common.util;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

public class ReflectionUtil {

    public static ConfigurationBuilder getConfigurationBuilder(Scanners... scanners) {
        return new ConfigurationBuilder().addUrls(getAllPackages())
            .addScanners(scanners);
    }

    private static Collection<URL> getAllPackages() {
        return Arrays.stream(Package.getPackages())
            .map(Package::getName)
            .map(s -> s.split("\\.")[0])
            .distinct()
            .map(ClasspathHelper::forPackage)
            .reduce((c1, c2) -> {
                Collection<URL> c3 = new HashSet<>();
                c3.addAll(c1);
                c3.addAll(c2);
                return c3;
            }).get();
    }

    public static <T> T getClassInstance(Class<T> aClass) throws RuntimeException {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Unable to create instance of class: " + aClass);
        }
    }

    public static <T> Set<Class<? extends T>> getAllInPackage(String packageName, Class<T> clazz) {
        var reflections = new Reflections(packageName);
        return reflections.getSubTypesOf(clazz);
    }

}
