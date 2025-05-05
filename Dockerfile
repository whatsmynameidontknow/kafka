# Build stage
FROM maven:3.9.9-eclipse-temurin-21-jammy AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Download dependencies and build
RUN mvn clean package -DskipTests

# Run stage
FROM eclipse-temurin:21.0.7_6-jre-jammy
WORKDIR /app

# Copy jar from build stage
COPY --from=builder /app/target/*.jar app.jar

# Environment variables
ENV KAFKA_BOOTSTRAP_SERVERS=kafka:9092
ENV SCHEMA_REGISTRY_URL=http://schema-registry:8081
ENV SERVER_PORT=8181

# Expose web port
EXPOSE 8181

# Set JVM options for containers
ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-XX:MaxRAMPercentage=75", \
    "-jar", "app.jar" \
    ]