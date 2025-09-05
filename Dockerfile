# Use Java 21 instead of 17
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR built by Gradle/Maven
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

EXPOSE 4002
ENTRYPOINT ["java", "-jar", "app.jar"]

