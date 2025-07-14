package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Duration;

public class Refresh {
    public static void main(String[] args)  {
        LoadingCache<String,Graph>cache= Caffeine.newBuilder().
                maximumSize(10000).expireAfterWrite(Duration.ofMinutes(5)).
                refreshAfterWrite(Duration.ofMillis(20)).build(GraphFactory::createExpensiveGraph);
        cache.put("user-1",new Graph("ID-01"));
        try {
            Thread.sleep(200);
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(cache.getIfPresent("user-1"));


    }
}
