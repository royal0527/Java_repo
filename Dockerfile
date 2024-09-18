# Use a base image that contains Java
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the jar file into the container
COPY target/invoice-service-0.0.1-SNAPSHOT.jar /app/invoice-service.jar

# Expose the port that your Spring Boot app runs on
EXPOSE 8080

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/invoice-service.jar"]
