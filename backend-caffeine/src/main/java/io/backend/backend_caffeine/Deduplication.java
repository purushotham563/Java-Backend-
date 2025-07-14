package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Interner;
import com.github.benmanes.caffeine.cache.LoadingCache;

public class Deduplication {
    public static void main(String[] args) {
        Interner<String>interner=Interner.newStrongInterner();
        String s1=interner.intern("Value");
        String s2=interner.intern("Value");
        assert s1==s2;

        //deduplication
        LoadingCache<String, Graph>cache= Caffeine.newBuilder().weakKeys().
                build(GraphFactory::createExpensiveGraph);
        Interner<String>interner1=Interner.newWeakInterner();
        String canonical=interner1.intern("user1");
        Graph graph=cache.get(canonical);


    }
}
