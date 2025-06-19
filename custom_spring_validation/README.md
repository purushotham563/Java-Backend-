
# Spring Boot Validation with Custom Annotations and MySQL

This project demonstrates **custom Java Bean Validation** with MySQL integration, featuring a complete REST API with proper validation and exception handling.

## ✨ Features

- ✅ **Custom validation** with `@ContactNumberConstraint` annotation
- 🛡️ Bean Validation using standard annotations (`@NotNull`, `@Email`, etc.)
- 🗃️ Spring Data JPA with MySQL integration
- 🏗️ Clean layered architecture:
  - Controller → Service → Repository

## 🛠️ Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Validation
- Spring Data JPA
- MySQL 8.x
- Maven

## 📂 Project Structure

```
src/main/java/com/validation/validation/
├── annotation/
│   ├── ContactNumberConstraint.java
│   └── ContactNumberValidator.java
├── config/
│   └── SwaggerConfig.java
├── controller/
│   └── UserController.java
├── exception/
│   └── GlobalExceptionHandler.java
├── model/
│   └── User.java
├── repository/
│   └── UserRepository.java
├── service/
│   └── UserService.java
└── ValidationInJavaAndSpringApplication.java
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

| Method | Endpoint         | Description         | Validation Rules                     |
|--------|------------------|---------------------|--------------------------------------|
| POST   | `/users`         | Create new user     | Phone format, email, name required   |
                 

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
