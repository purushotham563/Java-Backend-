# 🧑‍🎓 Spring Boot Student API — PostgreSQL Version

This is a Spring Boot project based on the [Amigoscode Spring Boot Series](https://www.youtube.com/c/amigoscode), using PostgreSQL for data storage.

## 🚀 Features

- REST APIs for student management
- PostgreSQL integration
- Full CRUD operations (Create, Read, Update, Delete)
- Spring Data JPA with Hibernate

---

## 🧠 Project Structure

```
demo/
├── .idea/
├── .mvn/
├── src/
│   ├── main/
│   │   ├── java/com.example.demo/
│   │   │   ├── Student/
│   │   │   │   ├── Student.java
│   │   │   │   ├── StudentConfig.java
│   │   │   │   ├── StudentController.java
│   │   │   │   ├── StudentRepository.java
│   │   │   │   └── StudentService.java
│   │   │   └── DemoApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/java/com.example.demo/
│       └── DemoApplicationTests.java
├── target/
│   └── demo-0.0.1-SNAPSHOT.jar
├── pom.xml
└── .gitignore
```

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

---

## ⚙️ application.properties Configuration

```properties
spring.application.name=demo
spring.datasource.url=jdbc:postgresql://localhost:5432/student
spring.datasource.username=postgres
spring.datasource.password=Appi.9740
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
server.error.include-message=always
```

---

## 🔁 API Endpoints

| Method | Endpoint                  | Description             |
|--------|---------------------------|-------------------------|
| GET    | /api/v1/student           | Get all students        |
| POST   | /api/v1/student           | Add new student         |
| DELETE | /api/v1/student/{id}      | Delete student by ID    |
| PUT    | /api/v1/student/{id}      | Update student name/email |

---

## 🧪 How to Run

1. Start your **PostgreSQL** server.
2. Create a new database:
   ```sql
   CREATE DATABASE student;
   ```
3. Build and run:
   ```bash
   mvn spring-boot:run
   ```

---

## 📦 Build the JAR

```bash
mvn clean install
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

---

## 📚 Credits

Project inspired by [Amigoscode Spring Boot Full Course](https://www.youtube.com/watch?v=9SGDpanrc8U)