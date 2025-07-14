package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.*;

import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class Eviction {

    public static void main(String[] args) {
      //Size-based
//        no or entries
        LoadingCache<String,Graph>cache= Caffeine.newBuilder().maximumSize(3).
                build(GraphFactory::createExpensiveGraph);
        cache.get("user-1");
        cache.get("user-3");
        cache.get("user-2");
        cache.get("user-4");
        Map<String,Graph>map=cache.asMap();
        for (Map.Entry<String,Graph>entry:map.entrySet()
        ) {
            System.out.println(entry.getKey()+"-->"+entry.getValue());

        }
        System.out.println(map.get("user-1"));//output should be null (-_-)
//        custom parameter
        LoadingCache<String, Graph> cache2 = Caffeine.newBuilder()
                .maximumWeight(20) // Limit total weight
                .weigher((String key, Graph graph) -> {
                    return graph.getValue().length();
                })
                .build(GraphFactory::createExpensiveGraph);
//        Time-based
//        expireAfterAccess
//        expireAfterWrite
//        expireAfter

        LoadingCache<String,Graph>cache3=Caffeine.
                newBuilder().expireAfter(Expiry.creating(
                        (String key,Graph graph)->
                                Duration.between(Instant.now(),
                                        graph.getCreatedAt().plusHours(5)))).
                scheduler(Scheduler.systemScheduler()).
                build(GraphFactory::createExpensiveGraph);
//        weak and soft
        LoadingCache<String,Graph>cache1=
                Caffeine.newBuilder().weakKeys().weakValues().
                        build(GraphFactory::createExpensiveGraph);
        LoadingCache<String,Graph>cache4=
                Caffeine.newBuilder().weakKeys().softValues().
                        build(GraphFactory::createExpensiveGraph);

    }
}
