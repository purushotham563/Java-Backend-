# ⚡ Spring Boot Caffeine Caching
A comprehensive implementation of high-performance caching using Caffeine cache library with Spring Boot, demonstrating various caching strategies and configurations.

---

## 📌 Features
- ✅ **Manual Cache** - Direct cache manipulation with get/put operations
- ✅ **Loading Cache** - Automatic cache population with cache loaders
- ✅ **Asynchronous Cache** - Non-blocking cache operations with CompletableFuture
- ✅ **Size-based Eviction** - LRU eviction based on maximum size or weight
- ✅ **Time-based Expiration** - TTL configuration with multiple expiration strategies
- ✅ **Weak/Soft References** - Memory-efficient caching with garbage collection cooperation
- ✅ **Cache Refresh** - Automatic background refresh of expired entries
- ✅ **Cache Statistics** - Performance monitoring and metrics
- ✅ **Removal Listeners** - Event-driven cache entry removal notifications
- ✅ **String Deduplication** - Memory optimization using interning

---

## 📁 Project Structure
```
src
├── main
│   ├── java
│   │   └── io.backend.backend_caffeine
│   │       ├── BackendCaffeineApplication.java
│   │       ├── Cleanup.java
│   │       ├── Deduplication.java
│   │       ├── Eviction.java
│   │       ├── FakeDB.java
│   │       ├── Graph.java
│   │       ├── GraphFactory.java
│   │       ├── Population.java
│   │       ├── Refresh.java
│   │       ├── Removal.java
│   │       └── Statistics.java
│   └── resources
│       └── application.properties
└── test
```

---

## 🚀 How It Works

### 1. **Cache Population Strategies**
- **Manual Cache**: Direct control over cache entries with explicit get/put operations
- **Loading Cache**: Automatic population using cache loaders when keys are missing
- **Async Cache**: Non-blocking operations returning CompletableFuture for better concurrency

### 2. **Eviction Policies**
- **Size-based**: LRU eviction when cache reaches maximum size or weight
- **Time-based**: Automatic expiration after write/access or custom expiry logic
- **Reference-based**: Weak/soft references for memory-sensitive applications

### 3. **Cache Lifecycle Management**
- **Refresh**: Background refresh of entries before they expire
- **Cleanup**: Manual and scheduled cleanup of expired entries
- **Monitoring**: Statistics and removal listeners for observability

---

## 🛠️ Technologies Used
- Java 17+
- Spring Boot
- Caffeine Cache Library
- CompletableFuture (Async operations)
- Maven

---

## 📦 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/your-username/caffeine-caching.git
cd caffeine-caching
```

### 2. Add Caffeine dependency to your `pom.xml`
```xml
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>3.1.8</version>
</dependency>
```

### 3. Build the project
```bash
./mvnw clean install
```

### 4. Run the application
```bash
./mvnw spring-boot:run
```

---

## 🧪 Cache Configuration Examples

### Manual Cache
```java
Cache<String, Graph> cache = Caffeine.newBuilder()
    .expireAfterWrite(10, TimeUnit.MINUTES)
    .maximumSize(10000)
    .build();

// Get with fallback
Graph graph = cache.get(key, GraphFactory::createExpensiveGraph);
```

### Loading Cache
```java
LoadingCache<String, Graph> cache = Caffeine.newBuilder()
    .maximumSize(10000)
    .expireAfterWrite(10, TimeUnit.MINUTES)
    .build(GraphFactory::createExpensiveGraph);

// Automatic loading
Graph graph = cache.get("user-1");
```

### Async Loading Cache
```java
AsyncLoadingCache<String, Graph> cache = Caffeine.newBuilder()
    .expireAfterWrite(1000, TimeUnit.SECONDS)
    .maximumSize(1000)
    .buildAsync(GraphFactory::createExpensiveGraph);

// Non-blocking operation
CompletableFuture<Graph> future = cache.get("user-1");
```

### Size-based Eviction
```java
LoadingCache<String, Graph> cache = Caffeine.newBuilder()
    .maximumSize(100)  // Maximum number of entries
    .build(GraphFactory::createExpensiveGraph);

// Or weight-based
LoadingCache<String, Graph> weightedCache = Caffeine.newBuilder()
    .maximumWeight(1000)
    .weigher((String key, Graph graph) -> graph.getValue().length())
    .build(GraphFactory::createExpensiveGraph);
```

### Time-based Expiration
```java
LoadingCache<String, Graph> cache = Caffeine.newBuilder()
    .expireAfterWrite(Duration.ofMinutes(5))    // Expire after write
    .expireAfterAccess(Duration.ofMinutes(10))  // Expire after access
    .refreshAfterWrite(Duration.ofMinutes(1))   // Refresh after write
    .build(GraphFactory::createExpensiveGraph);
```

### Weak/Soft References
```java
LoadingCache<String, Graph> cache = Caffeine.newBuilder()
    .weakKeys()      // Weak key references
    .softValues()    // Soft value references
    .build(GraphFactory::createExpensiveGraph);
```

---

## 📊 Cache Statistics

Enable statistics to monitor cache performance:

```java
Cache<String, Graph> cache = Caffeine.newBuilder()
    .recordStats()
    .build();

// Get statistics
CacheStats stats = cache.stats();
System.out.println("Hit rate: " + stats.hitRate());
System.out.println("Miss rate: " + stats.missRate());
System.out.println("Load count: " + stats.loadCount());
```

---

## 🔔 Removal Listeners

Monitor cache entry removals:

```java
Cache<String, Graph> cache = Caffeine.newBuilder()
    .evictionListener((String key, Graph graph, RemovalCause cause) -> {
        System.out.printf("Key %s was evicted (%s)%n", key, cause);
    })
    .removalListener((String key, Graph graph, RemovalCause cause) -> {
        if (cause.wasEvicted()) {
            System.out.printf("Key %s was evicted (%s)%n", key, cause);
        } else {
            System.out.printf("Key %s was removed (%s)%n", key, cause);
        }
    })
    .build();
```

---

## 🎯 Key Components

| Component | Description |
|-----------|-------------|
| **Graph.java** | Data model representing cached objects |
| **GraphFactory.java** | Factory for creating expensive graph objects |
| **FakeDB.java** | Simulated database for testing |
| **Population.java** | Demonstrates cache population strategies |
| **Eviction.java** | Shows various eviction policies |
| **Refresh.java** | Automatic cache refresh mechanisms |
| **Cleanup.java** | Manual and scheduled cache cleanup |
| **Statistics.java** | Cache performance monitoring |
| **Removal.java** | Event-driven removal notifications |
| **Deduplication.java** | String interning for memory optimization |

---

## 🔧 Configuration Options

### Cache Builder Options
```java
Caffeine.newBuilder()
    .maximumSize(10000)                           // Max entries
    .maximumWeight(1000)                          // Max weight
    .expireAfterWrite(Duration.ofMinutes(5))      // Expire after write
    .expireAfterAccess(Duration.ofMinutes(10))    // Expire after access
    .refreshAfterWrite(Duration.ofMinutes(1))     // Refresh after write
    .weakKeys()                                   // Weak key references
    .softValues()                                 // Soft value references
    .recordStats()                                // Enable statistics
    .scheduler(Scheduler.systemScheduler())       // Custom scheduler
    .build();
```

---

## 📈 Performance Benefits

- **High Performance**: Caffeine is one of the fastest Java caching libraries
- **Memory Efficient**: Supports weak/soft references and custom eviction policies
- **Concurrency**: Thread-safe operations with minimal contention
- **Flexibility**: Multiple cache types and configuration options
- **Monitoring**: Built-in statistics and event listeners

---

## 🧪 Testing

Run the individual classes to see different caching strategies in action:

```bash
# Manual cache operations
java -cp target/classes io.backend.backend_caffeine.Population

# Eviction policies
java -cp target/classes io.backend.backend_caffeine.Eviction

# Cache refresh
java -cp target/classes io.backend.backend_caffeine.Refresh

# Statistics monitoring
java -cp target/classes io.backend.backend_caffeine.Statistics
```

---

## 📚 Learning Resources

- [Caffeine Cache Documentation](https://github.com/ben-manes/caffeine)
- [Spring Cache Abstraction](https://spring.io/guides/gs/caching/)
- [Java Caching Best Practices](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/ConcurrentHashMap.html)

---

## 📄 License
This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author
**Purushotham Reddy**

*Happy Caching! ⚡