package io.backend.backend_caffeine;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class GraphFactory {

    public static Graph createExpensiveGraph(String key) {
        return new Graph(FakeDB.DATA.get(key));
    }

    public static CompletableFuture<Graph> createExpensiveGraph(String key, Executor executor) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Running expensive task for: " + key + " on thread: " + Thread.currentThread().getName());
            String value = FakeDB.DATA.get(key);
            return new Graph(value);
        }, executor);
    }
}

