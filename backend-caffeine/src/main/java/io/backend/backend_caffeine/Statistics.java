package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;

public class Statistics {
    public static void main(String[] args) {
        Cache<String,Graph>cache= Caffeine.newBuilder().expireAfterWrite(100, TimeUnit.MINUTES).
                maximumSize(100).recordStats().build();


    }
}
