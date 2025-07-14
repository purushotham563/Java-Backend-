package io.backend.backend_caffeine;

import com.github.benmanes.caffeine.cache.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Population {

    public static void main(String[] args) {
        String key="user-1";
        Graph graph=new Graph("ID-001");
        //Manual
        Cache<String,Graph>cache= Caffeine.newBuilder().
                expireAfterWrite(10, TimeUnit.MINUTES).
                maximumSize(10000).build();
        Graph graph1=cache.getIfPresent(key);
        graph1=cache.get(key,GraphFactory::createExpensiveGraph);
        //put and invalidate method
        System.out.println(graph1);


        //Loading
        LoadingCache<String,Graph>cache1=Caffeine.newBuilder().maximumSize(10000).
                expireAfterWrite(10,TimeUnit.MINUTES).
                build(GraphFactory::createExpensiveGraph);
        List<String>keyList=List.of("user-1","user-2","user-10");
        Map<String,Graph>bulkData=cache1.getAll(keyList);
        for (Map.Entry<String,Graph>entry:bulkData.entrySet()
             ) {
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }

      //Asynchronous(Manual)
        AsyncCache<String,Graph>cache2=Caffeine.newBuilder().
                expireAfterWrite(10000,TimeUnit.MINUTES).
                maximumSize(1000).buildAsync();
        CompletableFuture<Graph>graph2=cache2.getIfPresent(key);
        System.out.println(graph2);
        cache2.synchronous().invalidate(key);

        //Asynchronously Loading
        Executor executor= ForkJoinPool.commonPool();
        AsyncLoadingCache<String,Graph>cache3=
                Caffeine.newBuilder().expireAfterWrite(1000,TimeUnit.SECONDS).maximumSize(1000)
                        .buildAsync(((key1,executor1) -> GraphFactory.createExpensiveGraph(key1,executor)));
        System.out.println(cache3.get("user-1").thenAccept(System.out::println));




    }
}
