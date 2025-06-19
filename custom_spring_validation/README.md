
# Spring Boot Validation with Custom Annotations and MySQL

This project demonstrates **custom Java Bean Validation** with MySQL integration, featuring a complete REST API with proper validation and exception handling.

## ✨ Features

- ✅ **Custom validation** with `@ContactNumberConstraint` annotation
- 🛡️ Bean Validation using standard annotations (`@NotNull`, `@Email`, etc.)
- 🚨 Global Exception Handling with `@ControllerAdvice`
- 🗃️ Spring Data JPA with MySQL integration
- 🏗️ Clean layered architecture:
  - Controller → Service → Repository
- 📝 Swagger API documentation (optional)
- 🧪 Test setup with `ValidationTesting.java`

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Validation
- Spring Data JPA
- MySQL 8.x
- Maven
- Lombok (recommended addition)

## 📂 Project Structure

```
com.validation.validation
├── annotation/
│   ├── ContactNumberConstraint.java     # Custom validation annotation
│   └── ContactNumberValidator.java      # Validator implementation
├── config/
│   └── SwaggerConfig.java               # API documentation
├── controller/
│   └── UserController.java              # REST endpoints
├── dto/
│   └── UserRequest.java                 # Validation-annotated DTO
├── exception/
│   └── GlobalExceptionHandler.java      # Centralized error handling
├── model/
│   └── User.java                        # JPA Entity
├── repository/
│   └── UserRepository.java              # Data access layer
├── service/
│   └── UserService.java                 # Business logic
└── ValidationInJavaAndSpringApplication.java
```

## 🔌 MySQL Configuration

Configure `application.properties`:

```properties
# Datasource
spring.datasource.url=jdbc:mysql://localhost:3306/validation_db
spring.datasource.username=root
spring.datasource.password=yourpassword

# JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Optional: Show validation errors
server.error.include-message=always
```

## 🚀 Running the Application

Command line:

```bash
./mvnw spring-boot:run
```

Or in IDE:

- Open `ValidationInJavaAndSpringApplication.java`
- Run as Spring Boot application

## 📡 API Endpoints

| Method | Endpoint        | Description         | Validation Rules                     |
|--------|------------------|---------------------|--------------------------------------|
| POST   | `/api/users`     | Create new user     | Phone format, email, name required   |
| GET    | `/api/users`     | List all users      | -                                    |
| GET    | `/api/users/{id}`| Get user by ID      | Valid UUID check                     |

### ✅ Sample Valid Request

```json
POST /api/users
{
    "name": "John Doe",
    "email": "john@example.com",
    "phoneNumber": "+1234567890"
}
```

## 🧪 Testing Validation

### ❌ Invalid Phone

```json
{
    "phoneNumber": "123"  // Fails @ContactNumberConstraint
}
```

### ❌ Missing Name

```json
{
    "email": "test@example.com"  // Fails @NotBlank
}
```

## 🛠️ Custom Validation Explained

### Annotation Definition

```java
@Constraint(validatedBy = ContactNumberValidator.class)
public @interface ContactNumberConstraint {
    String message() default "Invalid phone number";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

### Validator Implementation

```java
public class ContactNumberValidator 
    implements ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext ctx) {
        return phone != null && phone.matches("^\+?[0-9]{10,15}$");
    }
}
```

## 📜 License

MIT

## 👨💻 Author

Purushotham Reddy
