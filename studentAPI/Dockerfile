FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app


COPY *.jar app.jar

EXPOSE 8081

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]


