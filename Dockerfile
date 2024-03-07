# Build Stage
FROM maven:3.8.1-openjdk-17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package

# Runtime Stage
FROM openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
