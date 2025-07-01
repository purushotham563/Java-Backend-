
# Spring Boot Masterclass Project

This repository contains the complete Spring Boot backend application built as part of the **Spring Boot Masterclass** by [Amigoscode](https://www.amigoscode.com/). It demonstrates key Spring Boot concepts with a fully functional backend API.

## 🚀 Features

- Spring Boot 3
- RESTful API with CRUD operations
- Spring Data JPA (Hibernate)
- H2 in-memory database
- Exception handling with custom exceptions
- Feign Client for API consumption
- Actuator for monitoring
- Profiles for environment configuration (`dev` and `prod`)
- Project structure with service, controller, and repository layers

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   └── com.example.demo
│   │       ├── customer                # Customer API logic
│   │       ├── exception               # Global exception handling
│   │       ├── infoapp                 # Spring Actuator & app info
│   │       ├── jsonplaceholder         # External API consumption using Feign
│   │       └── DemoApplication.java    # Main Spring Boot app
│   └── resources
│       ├── application.properties
│       ├── application-dev.properties
│       ├── schema.sql
│       └── data.sql
```

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Maven
- H2 Database
- Feign Client
- IntelliJ IDEA

## 📦 How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/spring-boot-masterclass.git
cd spring-boot-masterclass
```

### 2. Run with Maven

```bash
./mvnw spring-boot:run
```

Or from IntelliJ via the `DemoApplication.java`.

### 3. Access

- H2 Console: `http://localhost:8080/h2-console`
- Actuator Info: `http://localhost:8080/actuator/info`
- Customer API: `http://localhost:8080/api/v1/customers`
- JSON Placeholder (Feign Client): `/api/v1/posts`

## ✅ Endpoints Overview

- `GET /api/v1/customers` — List all customers
- `POST /api/v1/customers` — Add a new customer
- `PUT /api/v1/customers/{id}` — Update existing customer
- `DELETE /api/v1/customers/{id}` — Delete customer
- `GET /api/v1/posts` — Fetch posts from JSONPlaceholder API (via Feign)

## 📄 License

This project is for educational purposes based on the course by Amigoscode.
