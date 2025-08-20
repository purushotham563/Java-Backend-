# 📚 Student Management REST API

## 📌 Overview

A Spring Boot REST API project for managing student records with secure
JWT authentication and role-based access control.\
The project follows clean architecture principles (Controller → Service
→ Repository) and is fully containerized with Docker.\
Continuous integration & delivery is automated using GitHub Actions,
pushing images to a private Docker registry.

## 🔧 Tech Stack

-   Java 17
-   Spring Boot 3
-   Spring Security (JWT)
-   MySQL 8
-   Docker & DockerHub
-   GitHub Actions (CI/CD)

## ✨ Features

-   🔐 **Authentication & Authorization**: JWT-based login with
    role-based access (USER, ADMIN)\
-   📖 **Student CRUD APIs**: Create, Read, Update, Delete student
    records\
-   ⚡ **Exception Handling**: Global error handling with proper
    response codes\
-   🛠 **Validation**: Request payload validation using Hibernate
    Validator\
-   🐳 **Dockerized**: Application packaged as Docker image\
-   🔄 **CI/CD**: Automated build & push to DockerHub via GitHub Actions

## 🚀 API Endpoints

### Authentication

-   `POST /api/auth/register` → Register new user (username, password,
    role)\
-   `POST /api/auth/login` → Authenticate & get JWT token

### Student Management (secured with JWT)

-   `GET /api/students` → Get all students\
-   `GET /api/students/{id}` → Get student by ID\
-   `POST /api/students` → Add new student (**Admin only**)\
-   `PUT /api/students/{id}` → Update student details (**Admin only**)\
-   `DELETE /api/students/{id}` → Delete student (**Admin only**)

## 🔑 Authentication Flow

1.  Register a user with `/api/auth/register`\
2.  Login with `/api/auth/login` to get JWT token\
3.  Send requests to `/api/students/**` with the header:

```{=html}
<!-- -->
```
    Authorization: Bearer <JWT_TOKEN>

## 🐳 Running with Docker

### 1. Build and Run Locally

``` bash
# Build jar
mvn clean package -DskipTests

# Build Docker image
docker build -t student-management .

# Run container (configure DB and JWT env vars)
docker run -p 8081:8081   -e SPRING_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/studentdb"   -e SPRING_DATASOURCE_USERNAME="root"   -e SPRING_DATASOURCE_PASSWORD="secret"   -e APP_JWT_SECRET="MySuperSecretKey"   student-management
```

### 2. Pull from DockerHub (via CI/CD build)

``` bash
docker login
docker pull <your-dockerhub-username>/student-management:latest
docker run -p 8081:8081 <your-dockerhub-username>/student-management:latest
```

## ⚙️ CI/CD with GitHub Actions

On each push to `main`, GitHub Actions: 1. Builds Maven project\
2. Creates Docker image\
3. Pushes image → DockerHub (private repo or public if chosen)

## 📂 Project Structure

    studentAPI/
     ├── src/main/java/com/studentapi/
     │    ├── controller/   # REST Controllers
     │    ├── service/      # Business logic
     │    ├── repository/   # CRUD Repositories
     │    ├── model/        # Entities
     │    └── security/     # JWT Auth
     ├── src/main/resources/
     │    ├── application.properties
     ├── pom.xml
     ├── Dockerfile

## ✅ Future Enhancements

-   🔄 Add role-specific access control (e.g., USER view-only, ADMIN
    full CRUD)\
-   📈 Add unit/integration tests (JUnit + Mockito + Jacoco coverage)\
-   ☁️ Deploy to AWS/GCP/Azure with container orchestration
    (Kubernetes/ECS)

------------------------------------------------------------------------

👨‍💻 **Author**: Purushotham
