# 🚀 Spring Boot DTO with Validation
A comprehensive implementation of Data Transfer Objects (DTOs) with nested validation using Spring Boot, demonstrating advanced validation strategies, MapStruct mapping, and clean architecture patterns.

---

## 📌 Features
- ✅ **Bean Validation** - Jakarta validation annotations with custom messages
- ✅ **Nested DTO Validation** - Complex object validation with @Valid annotation
- ✅ **Collection Validation** - List and array validation with size constraints
- ✅ **Custom Validation** - Pattern matching and regex validation
- ✅ **MapStruct Integration** - Automatic mapping between entities and DTOs
- ✅ **Global Exception Handling** - Centralized validation error handling
- ✅ **One-to-Many Relationships** - User-Order relationship with nested DTOs
- ✅ **Geographic Validation** - Latitude/longitude range validation
- ✅ **Email & Format Validation** - Built-in and custom format validators
- ✅ **Error Response Formatting** - Structured error messages for API responses

---

## 📁 Project Structure
```
src
├── main
│   ├── java
│   │   └── com.example.dto
│   │       │   ├── LocationDTO.java
│   │       │   ├── UserDTO.java
│   │       │   ├── OrderDTO.java
│   │       │   └── UserWithOrdersDTO.java
│   │       │   ├── User.java
│   │       │   ├── Location.java
│   │       │   └── Order.java
│   │       │   └── UserMapper.java
│   │       │   └── UserService.java
│   │       │   └── UserController.java
│   │       └── DtoValidationApplication.java
│   └── resources
│       └── application.properties
└── test
```

---

## 🚀 How It Works

### 1. **Validation Strategies**
- **Field-level Validation**: Individual field constraints with custom messages
- **Nested Validation**: Complex object validation using @Valid annotation
- **Collection Validation**: List size constraints and element validation
- **Cross-field Validation**: Custom validators for related field validation

### 2. **DTO Mapping**
- **MapStruct Integration**: Automatic bi-directional mapping between entities and DTOs
- **Nested Object Mapping**: Automatic handling of complex nested relationships
- **Collection Mapping**: List and array mapping with validation preservation

### 3. **Error Handling**
- **Global Exception Handler**: Centralized validation error processing
- **Structured Error Response**: Consistent error message formatting
- **Field-specific Messages**: Detailed validation feedback for each field

---

## 🛠️ Technologies Used
- Java 17+
- Spring Boot 3.x
- Jakarta Bean Validation
- MapStruct
- Spring Web
- JPA/Hibernate
- Maven

---

## 📦 Setup Instructions

### 1. Clone the repository
```bash
git clone https://github.com/your-username/spring-dto-validation.git
cd spring-dto-validation
```

### 2. Add required dependencies to your `pom.xml`
```xml
<dependencies>
    <!-- Spring Boot Starter Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Boot Starter Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
    
    <!-- MapStruct -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.5.5.Final</version>
    </dependency>
    
    <!-- MapStruct Processor -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct-processor</artifactId>
        <version>1.5.5.Final</version>
        <scope>provided</scope>
    </dependency>
    
    <!-- Spring Boot Starter JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
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

## 🧪 DTO Configuration Examples

### Basic DTO with Validation
```java
public class LocationDTO {
    @NotBlank(message = "Place name is required")
    @Size(min = 2, max = 100, message = "Place name must be between 2 and 100 characters")
    private String place;
    
    @NotNull(message = "Longitude is required")
    @DecimalMin(value = "-180.0", message = "Longitude must be >= -180")
    @DecimalMax(value = "180.0", message = "Longitude must be <= 180")
    private Double longitude;
    
    @NotNull(message = "Latitude is required")
    @DecimalMin(value = "-90.0", message = "Latitude must be >= -90")
    @DecimalMax(value = "90.0", message = "Latitude must be <= 90")
    private Double latitude;
}
```

### Nested DTO Validation
```java
public class UserDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Please provide a valid email address")
    private String email;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;
    
    @NotNull(message = "Location is required")
    @Valid // This ensures nested validation
    private LocationDTO location;
}
```

### Pattern Validation
```java
public class OrderDTO {
    @NotBlank(message = "Order number is required")
    @Pattern(regexp = "^ORD-\\d{6}$", message = "Order number must follow pattern ORD-XXXXXX")
    private String orderNumber;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(PENDING|PROCESSING|SHIPPED|DELIVERED|CANCELLED)$", 
             message = "Status must be one of: PENDING, PROCESSING, SHIPPED, DELIVERED, CANCELLED")
    private String status;
}
```

### Collection Validation
```java
public class UserWithOrdersDTO {
    @NotNull(message = "Location is required")
    @Valid
    private LocationDTO location;
    
    @Valid // Validate each order in the list
    @Size(max = 100, message = "User cannot have more than 100 orders")
    private List<OrderDTO> orders;
}
```

### Decimal Validation
```java
@NotNull(message = "Total amount is required")
@DecimalMin(value = "0.01", message = "Total amount must be greater than 0")
@Digits(integer = 10, fraction = 2, message = "Total amount must have max 10 integer digits and 2 decimal places")
private Double totalAmount;
```

---

## 🗺️ MapStruct Configuration

### Basic Mapper Interface
```java
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    // Basic mapping
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
    
    // Nested mapping
    LocationDTO locationToLocationDTO(Location location);
    Location locationDTOToLocation(LocationDTO locationDTO);
    
    // Collection mapping
    List<UserDTO> usersToUserDTOs(List<User> users);
    List<User> userDTOsToUsers(List<UserDTO> userDTOs);
}
```

---

## 🎯 Controller Implementation

### REST Controller with Validation
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, 
                                             @Valid @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }
    
    @GetMapping("/{id}/orders")
    public UserWithOrdersDTO getUserWithOrders(@PathVariable Long id) {
        return userService.getUserWithOrders(id);
    }
}
```

### Global Exception Handler
```java
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<Map<String, String>> handleValidationErrors(
        MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> 
        errors.put(error.getField(), error.getDefaultMessage()));
    return ResponseEntity.badRequest().body(errors);
}
```

---

## 📊 Validation Annotations Reference

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@NotNull` | Field cannot be null | `@NotNull(message = "Required field")` |
| `@NotBlank` | String cannot be null, empty, or whitespace | `@NotBlank(message = "Name is required")` |
| `@Size` | String/Collection size constraints | `@Size(min = 2, max = 50)` |
| `@Email` | Email format validation | `@Email(message = "Invalid email")` |
| `@Pattern` | Regex pattern matching | `@Pattern(regexp = "^[A-Z]{2}\\d{4}$")` |
| `@DecimalMin` | Minimum decimal value | `@DecimalMin(value = "0.01")` |
| `@DecimalMax` | Maximum decimal value | `@DecimalMax(value = "999.99")` |
| `@Digits` | Number format validation | `@Digits(integer = 10, fraction = 2)` |
| `@Valid` | Nested object validation | `@Valid private LocationDTO location` |

---

## 🔔 Error Response Examples

### Field Validation Error
```json
{
    "email": "Please provide a valid email address",
    "firstName": "First name must be between 2 and 50 characters",
    "location.latitude": "Latitude must be >= -90"
}
```

### Nested Validation Error
```json
{
    "location.place": "Place name is required",
    "location.longitude": "Longitude must be <= 180",
    "orders[0].orderNumber": "Order number must follow pattern ORD-XXXXXX"
}
```

---

## 🎯 Key Components

| Component | Description |
|-----------|-------------|
| **LocationDTO.java** | Geographic location with coordinate validation |
| **UserDTO.java** | User information with nested location validation |
| **OrderDTO.java** | Order details with pattern and decimal validation |
| **UserWithOrdersDTO.java** | Complex DTO with nested objects and collections |
| **UserMapper.java** | MapStruct interface for entity-DTO mapping |
| **UserService.java** | Business logic with validation integration |
| **UserController.java** | REST endpoints with validation and error handling |

---


## 📄 License
This project is licensed under the [MIT License](LICENSE).

---

## 🙋‍♂️ Author
**Purushotham Reddy**

*Happy Validating! 🚀*