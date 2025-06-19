
# Java Bean Validation & Spring Boot Validation

This project demonstrates how to use both **Jakarta Bean Validation** (in plain Java) and **Spring Boot's validation** using `@Valid`, `@RequestBody`, and `BindingResult`.

## 📂 Project Structure

```
java-validation/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.validation.validation/
│   │   │       ├── User.java
│   │   │       ├── UserController.java
│   │   │       ├── ValidationInJavaAndSpringApplication.java
│   │   │       └── ValidationTesting.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
```

## 📌 Key Components

### 1. `User` Class
A simple POJO with validation annotations:
```java
@NotNull
@Min(18)
@Max(65)
private int age;

@Size(min = 10, message = "About me must be at least 10 characters")
private String aboutMe;
```

### 2. `ValidationTesting.java`
Plain Java-based validation using `jakarta.validation`:

```java
ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
Validator validator = factory.getValidator();
Set<ConstraintViolation<User>> violations = validator.validate(user);
```

### 3. `UserController.java`
REST API that uses Spring Boot’s `@Valid` and `BindingResult` to validate incoming POST requests:

```java
@PostMapping
public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result)
```

## ▶️ Running the Project

```bash
# Compile and run
mvn spring-boot:run
```

- Use Postman to POST to `http://localhost:8080/users`
- Send a JSON body:
```json
{
  "age": 17,
  "aboutMe": "Short"
}
```

You’ll get validation errors as a response.

## ✅ Technologies Used

- Java 17+
- Spring Boot
- Jakarta Bean Validation (JSR 380)
- Maven

## 📚 Reference

- [Baeldung – Bean Validation](https://www.baeldung.com/javax-validation)
- [Baeldung – Spring @Valid](https://www.baeldung.com/spring-mvc-custom-validator)

## 🧑‍💻 Author

[Purushotham Reddy](https://github.com/purushotham563)

---

Feel free to star ⭐ the repo if this helped!
