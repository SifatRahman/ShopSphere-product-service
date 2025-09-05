# Use Java 21 instead of 17
FROM openjdk:21-jdk-slim

WORKDIR /app

# Copy the JAR built by Gradle/Maven
ARG JAR_FILE=build/libs/ShopSphere-product-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} ShopSphere-product-service-0.0.1-SNAPSHOT.jar

EXPOSE 4002
ENTRYPOINT ["java", "-jar", "ShopSphere-product-service-0.0.1-SNAPSHOT.jar"]

