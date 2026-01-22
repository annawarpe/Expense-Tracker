# Use OpenJDK 21
FROM eclipse-temurin:21-jdk-jammy

# Set working directory inside container
WORKDIR /app

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the project (skip tests for faster build)
RUN mvn clean package -DskipTests

# Expose Spring Boot default port
EXPOSE 8080

# Start the Spring Boot application
CMD ["java", "-jar", "target/expense-tracker-0.0.1-SNAPSHOT.jar"]
