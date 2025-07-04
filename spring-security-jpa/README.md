# 📦 Spring Security JPA Demo

This is a Spring Boot application that demonstrates **authentication** and **authorization** using **Spring Security** with **JPA** and a custom `UserDetailsService`.

---

## 🧰 Tech Stack

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven

---

## 🚀 How to Run

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/spring-security-jpa.git
   cd spring-security-jpa
   ```

2. **Run with Maven:**

   ```bash
   ./mvnw spring-boot:run
   ```

   Or build and run:

   ```bash
   ./mvnw clean install
   java -jar target/spring-security-jpa-0.0.1-SNAPSHOT.jar
   ```

---

## 🗂️ Project Structure

```
spring-security-jpa
│
├── src
│   ├── main
│   │   ├── java/io/backend/spring_security_jpa
│   │   │   ├── models
│   │   │   │   ├── User.java
│   │   │   │   ├── UserRepository.java
│   │   │   │   └── MyUserDetailsService.java
│   │   │   ├── HomeResource.java
│   │   │   ├── SecurityConfiguration.java
│   │   │   └── SpringSecurityJpaApplication.java
│   │   └── resources
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
```

---

## 🔐 Endpoints & Access

| Endpoint     | Role Required |
|--------------|----------------|
| `/`          | Public         |
| `/user`      | USER / ADMIN   |
| `/admin`     | ADMIN          |

---

## 🔑 Authentication Setup

- User details are loaded from the database using a custom `MyUserDetailsService`.
- Password encoding is set to `NoOpPasswordEncoder` (not for production).
- Role-based access is handled via `.hasRole(...)` in `SecurityFilterChain`.

---

## 📄 application.properties Example

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springsecurity
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
```

---

## ⚠️ Notes

- Change `NoOpPasswordEncoder` to `BCryptPasswordEncoder` for production use.
- Consider adding proper exception handling and a login page for full implementation.

---

## 🙌 Acknowledgements

- Thanks to [Java Brains](https://javabrains.io/) for the amazing Spring Security tutorials.