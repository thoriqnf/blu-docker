# Level 2+: Multi-Stage Build & Optimization
# Stage 1: Build the application from source
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY notes-app/pom.xml .
COPY notes-app/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre-alpine
LABEL description="Notes API - Production Ready Solution"

# Level 4: Security (Run as non-root)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
WORKDIR /app

# Copy the JAR from the build stage
COPY --from=build /app/target/*.jar app.jar
RUN chown -R appuser:appgroup /app

USER appuser
EXPOSE 8081

# Level 4: Reliability (Healthcheck)
HEALTHCHECK --interval=30s --timeout=5s --start-period=40s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
