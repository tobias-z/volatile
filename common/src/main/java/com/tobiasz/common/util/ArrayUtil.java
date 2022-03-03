package com.tobiasz.common.util;

public class ArrayUtil {

    @SafeVarargs
    public static <T> T[] arrayOf(T... args) {
        return args;
    }
}
