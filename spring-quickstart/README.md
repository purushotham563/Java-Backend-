# Spring Boot Quickstart (Java Brains) — MySQL Version

This is a Spring Boot application based on the [Java Brains Spring Boot Quick Start Series](https://www.youtube.com/playlist?list=PLqq-6Pq4lTTZSKAFG6aCDVDP86Qx4lNas), modified to use **MySQL** instead of Apache Derby as the database.

## 🚀 Features

- REST APIs for managing Topics and Courses
- Spring Boot with JPA (Hibernate)
- MySQL Database Integration
- CRUD operations using Spring Data JPA

---

## 🧠 Project Structure

javaBrains/
└── demo/
├── src/
│ ├── main/
│ │ ├── java/com.example.demo/
│ │ │ ├── Course/
│ │ │ │ ├── Course.java
│ │ │ │ ├── CourseController.java
│ │ │ │ ├── CourseRepository.java
│ │ │ │ └── CourseService.java
│ │ │ ├── Topic/
│ │ │ │ ├── Topic.java
│ │ │ │ ├── TopicController.java
│ │ │ │ ├── TopicRepository.java
│ │ │ │ └── TopicService.java
│ │ │ └── DemoApplication.java
│ │ └── resources/
│ │ ├── application.properties
│ │ ├── static/
│ │ └── templates/
│ └── test/java/com.example.demo/
│ └── DemoApplicationTests.java
├── pom.xml
└── .gitignore

---

## ⚙️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- RESTful API
- MySQL (used as database)
- Maven (build tool)

---

## ⚙️ application.properties Configuration

properties
spring.application.name=demo
server.port=8081
#spring.datasource.url=jdbc:mysql://localhost:3306/SpringDB?useSSL=false&serverTimezone=UTC
spring.datasource.url=jdbc:mysql://localhost:3306/SpringDB?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true

spring.datasource.username=root
spring.datasource.password=Appi.9740171
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect


🔁 API Endpoints
📘 Topic Endpoints
Method	Endpoint	Description
GET	/topics	Get all topics
GET	/topics/{id}	Get topic by ID
POST	/topics	Add new topic
PUT	/topics/{id}	Update existing
DELETE	/topics/{id}	Delete a topic

🎓 Course Endpoints (Nested under Topic)
Method	Endpoint	Description
GET	/topics/{topicId}/courses	Get all courses for topic

GET	/topics/{topicId}/courses/{id}	Get course by ID
POST	/topics/{topicId}/courses	Add new course
PUT	/topics/{topicId}/courses/{id}	Update course
DELETE	/topics/{topicId}/courses/{id}	Delete course
💡 Note on Relationships
A Course is related to a Topic using @ManyToOne.

While adding a course, we set its topic like:
course.setTopic(new Topic(topicId, "", ""));
This creates a lightweight reference (only ID is needed) so JPA can link it correctly without requiring the full topic object to exist in memory.
🧪 Run the Project
1.Start your MySQL server

2.Create a schema:
 CREATE DATABASE springcourse;
3.Build and run:
mvn spring-boot:run
📦 Build the JAR
mvn clean install
Then run:
java -jar target/demo-0.0.1-SNAPSHOT.jar

📤 Deployment (Future Scope)
Add Docker support

Deploy to AWS EC2 or Railway

Add Swagger for API documentation

📚 Credits
This project is based on the Java Brains Spring Boot Quickstart series.


