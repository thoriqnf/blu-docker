# 🏋️ Hands-on Exercise: Containerize the Notes API

## Time: 60 minutes

## Overview
This final challenge brings together everything you've learned from Demos 01 to 12. You will move through **5 Levels** of maturity, from basic "it runs" to production-ready "it's secure and reliable".

---

## 🎯 The 5 Levels of Excellence

### Level 1: The Basics (Demos 01-03)
**Goal**: Get the app and its dependencies running.
- [ ] Create a `Dockerfile` for `notes-app.jar`.
- [ ] Use `eclipse-temurin:21-jre-alpine` as base image.
- [ ] In `docker-compose.yml`, define 3 services: `app`, `db` (Postgres), and `redis`.
- [ ] Map port `8081` for the app.

### Level 2: Build Optimization (Demos 04-05)
**Goal**: Professional-grade images.
- [ ] Implement **Multi-Stage Build** in your Dockerfile (Build stage -> Run stage).
- [ ] Use a `.dockerignore` to exclude `node_modules`, `.git`, and target folders.
- [ ] Ensure **Layer Caching** is efficient (copy dependencies before source code).

### Level 3: Networking & Persistence (Demos 06-07)
**Goal**: Safe data and isolated communication.
- [ ] Create a custom **Docker Network** and put all 3 services in it.
- [ ] Add a **Named Volume** for PostgreSQL so data survives a `docker compose down`.
- [ ] Use **Service Discovery**: Configure `DB_HOST=db` and `REDIS_HOST=redis` (no IP addresses!).

### Level 4: Reliability & Performance (Demos 08-09)
**Goal**: Production stability.
- [ ] Add **Healthchecks** for `db` (using `pg_isready`) and `redis` (using `redis-cli ping`).
- [ ] Make the `app` wait for them using `depends_on` with `condition: service_healthy`.
- [ ] Add **Resource Limits**: Limit the app to `512MB` RAM and `0.5` CPU.

### Level 5: Security & Compliance (Demos 10-12)
**Goal**: Enterprise-grade security.
- [ ] Use a `.env` file for non-sensitive config (like `APP_NAME`).
- [ ] Implement **Docker Secrets** for the `DB_PASSWORD`.
- [ ] Run `docker compose config` to verify your YAML is perfect.

---

## 🛠️ Configuration Reference

### Environment Variables
| Service | Variable | Value/Hint |
|---------|----------|------------|
| app     | `DB_HOST` | `db` |
| app     | `REDIS_HOST` | `redis` |
| app     | `SERVER_PORT` | `8081` |

### Testing
```bash
# Start the journey
docker compose up -d

# Check results
curl http://localhost:8081/api/notes
docker compose ps
docker stats
```

---

## 💡 Pro Hints
- **DNS**: Use the service name (e.g., `db`) in your `DB_HOST` variable.
- **Ports**: The API runs on `8081` internally.
- **DB Readiness**: If you see `Connection Refused`, your app started before the database was ready! (Level 3 fixes this).

---
> [!IMPORTANT]
> If you get stuck, look at your notes from **Step 05** (Multi-stage), **Step 09** (Healthchecks), and **Step 12** (Secrets)!
