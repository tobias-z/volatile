package com.tobiasz.common.util;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ThreadExecution {

    public static <T> void executeInParallel(Collection<T> items, Consumer<T> consumer) {
        try {
            ExecutorService executorService = Executors.newFixedThreadPool(items.size());
            for (T initializer : items) {
                executorService.execute(() -> consumer.accept(initializer));
            }
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
