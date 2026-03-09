# ===========================================
# Solution: Dockerfile for Notes API
# ===========================================

FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="devops-training"
LABEL description="Notes API - Spring Boot Application"

RUN addgroup -S appgroup && adduser -S appuser -G appgroup

WORKDIR /app

COPY notes-app.jar app.jar

RUN chown -R appuser:appgroup /app

USER appuser

EXPOSE 8081

HEALTHCHECK --interval=30s --timeout=10s --start-period=40s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:8081/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]
