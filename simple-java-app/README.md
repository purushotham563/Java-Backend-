# Simple Java CI/CD Pipeline

A Java application with automated testing and Docker deployment using GitHub Actions.

## 🚀 Quick Start

1. **Clone & Build**
git clone https://github.com/purushotham563/Java-Backend-/simple-java-app.git
cd simple-java-app
mvn clean package
java -jar target/simple-java-app-1.0.0.jar


2. **Setup CI/CD**
- Add GitHub secrets: `DOCKERHUB_USERNAME` and `DOCKERHUB_TOKEN`
- Push to `main` branch → Automatic build & deploy to Docker Hub

## 📁 Project Structure
simple-java-app/
├── src/main/java/com/example/App.java # Main application
├── src/test/java/com/example/AppTest.java # Unit tests
├── .github/workflows/ci-cd.yml # CI/CD pipeline
├── Dockerfile # Docker configuration
└── pom.xml # Maven configuration

text

## 🔧 Technologies
- **Java 17** + **Maven** (build)
- **JUnit 5** (testing)
- **Docker** (containerization)
- **GitHub Actions** (CI/CD)

## 📊 Pipeline Flow
Push to main → Run Tests → Build JAR → Create Docker Image → Push to Docker Hub


## 🐳 Docker Usage
docker pull your-username/simple-java-app:latest
docker run your-username/simple-java-app:latest

text

## 📝 Output
Hello World from CI/CD Pipeline!
Current timestamp: 1691234567890(this may be diff -_-)


Author: Purushotham Reddy D A
