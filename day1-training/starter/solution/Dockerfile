# ===========================================
# Solution: Starter Dockerfile
# ===========================================
# This Dockerfile will containerize the Todo API.
# The app is a pre-built Spring Boot JAR file.
# ===========================================

# TODO 1: Choose a base image
# Hint: We need Java 21 runtime. Use eclipse-temurin:21-jre-alpine
FROM eclipse-temurin:21-jre-alpine

# TODO 2: Set the working directory
# Hint: Use /app as the working directory
WORKDIR /app

# TODO 3: Copy the JAR file into the container
# Hint: Copy todo-app.jar and name it app.jar
COPY todo-app/todo-app.jar app.jar

# TODO 4: Expose the application port
# Hint: The app runs on port 8080
EXPOSE 8080

# TODO 5: Set the startup command
# Hint: Use java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

# ===========================================
# BONUS: Add a health check
# Hint: Use wget to check /actuator/health
# ===========================================
HEALTHCHECK --interval=30s --timeout=10s --retries=3 \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1
