package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.Scheduler;

import java.lang.ref.Cleaner;
import java.time.Duration;

public class Cleanup {
    public static void main(String[] args) {
        //System time based clean up
        LoadingCache<String,Graph>cache= Caffeine.newBuilder().
                scheduler(Scheduler.systemScheduler()).
                expireAfterWrite(Duration.ofMinutes(10)).build(GraphFactory::createExpensiveGraph);
        //Manual
        cache.cleanUp();
        //using weak keys and weak or soft value
        Cache<String,Graph>cache1=Caffeine.newBuilder().weakKeys().weakValues().build();
        Cleaner cleaner=Cleaner.create();
        cleaner.register(cache,cache1::cleanUp);

    }
}
