# 📦 Spring Boot JWT Authentication

A simple and secure JWT-based authentication and authorization system using Spring Boot and Spring Security.

---

## 📌 Features

- ✅ User login with JWT token generation  
- ✅ JWT validation on each request via filter  
- ✅ Stateless authentication  
- ✅ Role-based authorization (if extended)  
- ✅ Configurable security settings  
- ✅ Clean architecture (filters, services, utils, models)

---

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   └── io.backend.spring_security_jwt
│   │       ├── JWT
│   │       │   └── JWTUtil.java
│   │       ├── filters
│   │       │   └── JwtRequestFilter.java
│   │       ├── models
│   │       │   ├── AuthenticationRequest.java
│   │       │   └── AuthenticationResponse.java
│   │       ├── Services
│   │       │   └── MyUserDetailService.java
│   │       ├── HelloResource.java
│   │       ├── SecurityConfigure.java
│   │       └── SpringSecurityJwtApplication.java
│   └── resources
│       └── application.properties
└── test
```

---

## 🚀 How It Works

1. **User sends a login request** with username and password.
2. **JWT is generated** on successful authentication and returned in the response.
3. The client sends the **JWT token in the Authorization header** for subsequent requests.
4. `JwtRequestFilter` intercepts each request and **validates the token**.
5. If valid, the request is forwarded with authenticated user context.

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- Maven

---

## 📦 Setup Instructions

### 1. Clone the repository

```bash
git clone https://github.com/your-username/spring-security-jwt.git
cd spring-security-jwt
```

### 2. Build the project

```bash
./mvnw clean install
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

---

## 🧪 API Endpoints

| Method | Endpoint         | Description             |
|--------|------------------|-------------------------|
| POST   | `/authenticate`  | Authenticate & get JWT |
| GET    | `/hello`         | Secured hello message  |

- Use `Authorization: Bearer <token>` in the header to access secured endpoints.

---

## 🔐 Sample Authentication Request

```json
POST /authenticate
{
  "username": "admin",
  "password": "admin123"
}
```

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author

**Purushotham Reddy**  
Feel free to connect or raise an issue for collaboration or queries.