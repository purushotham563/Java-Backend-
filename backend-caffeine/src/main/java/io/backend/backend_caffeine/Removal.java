package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;

public class Removal {
    public static void main(String[] args) {
        //Removal Listeners
        Cache<String,Graph>cache= Caffeine.newBuilder().
                evictionListener((String key,Graph graph, RemovalCause cause)->
                        System.out.printf("Key %s was evicted (%s)%n",key,cause)) .
                removalListener((String key,Graph graph, RemovalCause cause)->{
                    if(cause.wasEvicted()){
                        System.out.printf("Key %s was evicted (%s)%n",key,cause);
                    }else{
                        System.out.printf("Key %s was removed (%s)%n",key,cause);
                    }
                        }
                ).build();
    }
}
