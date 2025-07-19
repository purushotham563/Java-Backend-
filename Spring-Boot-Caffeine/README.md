# 🚀 Spring Boot Caffeine Cache Example

A simple Spring Boot application demonstrating how to implement **Caffeine-based caching** in a RESTful service using Spring's Cache Abstraction.

---

## 📦 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MYSQL
- Caffeine Cache
- Maven

---

## ⚙️ Features

- ✅ RESTful CRUD operations on Users
- ⚡ Caching using **Caffeine** for performance
- 📉 Cache Statistics Endpoint
- 🔁 Conditional and selective caching
- 🧹 Auto-initialized test data on startup

---

## 🧱 Project Structure

```
src/main/java/io/backend/Spring_Boot_Caffeine
│
├── layer/
│   ├── CacheConfig.java         # Caffeine configuration
│   ├── CacheController.java     # Monitor/clear cache stats
│   ├── DataInitializer.java     # Preload test users
│   ├── User.java                # Entity
│   ├── UserController.java      # REST API controller
│   ├── UserRepository.java      # JPA repository
│   └── UserService.java         # Service layer with caching
│
├── SpringBootCaffeineApplication.java  # Main class with @EnableCaching
```

---

## 🧪 Sample API Endpoints

| Method | Endpoint                         | Description                       |
|--------|----------------------------------|-----------------------------------|
| GET    | `/api/users`                     | Get all users (cached)            |
| GET    | `/api/users/{id}`                | Get user by ID (cached)           |
| GET    | `/api/users/username/{username}` | Get user by username (cached)     |
| POST   | `/api/users`                     | Create/update user (cache update) |
| DELETE | `/api/users/{id}`                | Delete user (evicts cache)        |
| DELETE | `/api/users/cache/clear`         | Clear all users cache             |
| GET    | `/api/cache/stats`               | View cache statistics             |
| DELETE | `/api/cache/clear/{cacheName}`   | Clear specific cache              |

---

## 📥 How to Run

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-username/spring-boot-caffeine-cache.git
   cd spring-boot-caffeine-cache
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the app**
   ```bash
   mvn spring-boot:run
   ```

4. **Test API using Postman or curl**
   - Sample GET: `http://localhost:8080/api/users`
   - Sample POST:
     ```json
     {
       "username": "mike_ross",
       "email": "mike@example.com",
       "firstName": "Mike",
       "lastName": "Ross"
     }
     ```

---

## 📊 Caffeine Cache Settings

Defined in `application.properties` or `CacheConfig.java`:

```properties
spring.cache.type=caffeine
spring.cache.caffeine.spec=maximumSize=500,expireAfterWrite=5m,recordStats
```

You can also define detailed per-bean settings using the `Caffeine.newBuilder()` inside `CacheConfig.java`.

---

## ✅ Sample Test Data Initialized

Automatically inserted on startup if DB is empty:

- john_doe
- jane_smith
- bob_wilson
- alice_brown

---

## 📈 Cache Monitoring

Hit this endpoint to check real-time cache stats:

```
GET /api/cache/stats
```

---

## 📚 Learn More

- [Spring Cache Abstraction Docs](https://docs.spring.io/spring-framework/reference/integration/cache.html)
- [Caffeine GitHub](https://github.com/ben-manes/caffeine)

---

## 📝 License

This project is licensed under the MIT License.