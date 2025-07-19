package io.backend.Spring_Boot_Caffeine.layer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cache")
public class CacheController {
    @Autowired
    private CacheManager cacheManager;
    @GetMapping("/stats")
    public Map<String,Object>getCacheStats(){
        Map<String,Object>stats=new HashMap<>();
        cacheManager.getCacheNames().forEach(cacheName->{
            CaffeineCache cache=(CaffeineCache) cacheManager.getCache(cacheName);
            if(cache!=null){
                com.github.benmanes.caffeine.cache.Cache<Object,Object>
                        nativeCache=cache.getNativeCache();
                Map<String,Object>cacheStats=new HashMap<>();
                cacheStats.put("size",nativeCache.estimatedSize());
                cacheStats.put("stats",nativeCache.stats());
                stats.put(cacheName,cacheStats);
            }

        });
        return stats;
    }
    @DeleteMapping("clear/{cacheName}")
    public String clearCache(@PathVariable String cacheName){
        cacheManager.getCache(cacheName).clear();
        return "Cache '" + cacheName + "' cleared!";
    }

}
