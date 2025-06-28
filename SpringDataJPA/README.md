
# Spring Data JPA Practice Project

This is a hands-on implementation based on the Spring Data JPA course by Amigoscode. The code was fully written and customized by me for learning and demonstration purposes.

## 📦 Project Overview

The project demonstrates core JPA and Hibernate features using Spring Boot. It models a university system with students, courses, books, ID cards, and enrollment records.

### 📁 Structure

```
src/
└── main/
    └── java/
        └── com.example.demo/
            ├── Application.java
            ├── Book.java
            ├── Course.java
            ├── Enrolment.java
            ├── EnrolmentId.java
            ├── Student.java
            ├── StudentIdCard.java
            ├── StudentRepository.java
            └── StudentIdCardRepository.java
```

## 📚 Features Implemented

### 🔗 Relationships

- **One-to-One**  
  `Student` ↔ `StudentIdCard`

- **One-to-Many**  
  `Student` → `Book`

- **Many-to-Many (with extra fields)**  
  `Student` ⇄ `Course` via `Enrolment` and `EnrolmentId` (composite key)

### ⚙️ JPA Features

- `@OneToOne`, `@OneToMany`, `@ManyToMany`, `@JoinTable`
- `@Embeddable`, `@EmbeddedId` for composite keys
- Custom query methods in repositories
- Cascading and orphan removal
- Fetch types: `LAZY`, `EAGER`
- Entity lifecycle and persistence context usage

## 🧪 How to Run

### Prerequisites

- Java 17+
- Maven
- IDE (IntelliJ recommended)

### Running the App

```bash
./mvnw spring-boot:run
```


## 💡 Sample Use Cases

- Create a student with a student card and books
- Enroll a student into multiple courses
- Query which students are enrolled in which courses
- Demonstrate how removing a student affects related entities

## ✏️ Customization Done

- Manual creation of `Enrolment` join entity for extra fields (e.g., `createdAt`)
- Customized repository queries using `@Query`
- Practice of entity lifecycle: `merge`, `detach`, `remove`
- Full control over cascade types and fetch strategies


## 🧑‍💻 Author

Purushotham Reddy  
Self-built while following Amigoscode  
Project completed as part of Java backend development practice
