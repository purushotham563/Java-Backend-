# Spring Boot Validation with MySQL

This is a simple Spring Boot project demonstrating **Java Bean Validation** and **Exception Handling** using MySQL as the database. The structure follows best practices and was built based on Baeldung tutorials.

## 🧠 Features

- ✅ Bean Validation using annotations like `@NotNull`, `@Email`, etc.
- 🚫 Global Exception Handling with `@ControllerAdvice`
- 💾 Spring Data JPA with MySQL integration
- 📂 Clean folder structure with layers: Controller, Service, Repository
- 🧪 Basic test setup with `ValidationTesting.java`

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## 🏗️ Project Structure

```
com.validation.validation
│
├── GlobalExceptionHandler.java
├── User.java
├── User1.java
├── UserController.java
├── UserRepository.java
├── UserService.java
├── ValidationInJavaAndSpringApplication.java
└── ValidationTesting.java
```

## 🔌 MySQL Configuration

Edit `src/main/resources/application.properties` with your MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 🚀 Running the App

```bash
./mvnw spring-boot:run
```

Or use IntelliJ IDEA to run the `ValidationInJavaAndSpringApplication` class.

## 📤 API Endpoints

- `POST /users` – Create a new user (validates body)
- `GET /users` – Get all users
- `GET /users/{id}` – Get user by ID

## 🙋‍♂️ Author

Built by [Purushotham Reddy](https://github.com/purushotham563)