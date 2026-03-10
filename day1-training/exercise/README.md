# 🏋️ Hands-on Exercise: Containerize the Notes API

## Time: 60 minutes

## Overview
You've learned how to containerize the Todo API and optimized it with Docker Compose. Now it's your turn to apply all those skills (Level 1 to 4) on a production-like application!

You'll containerize the **Notes API** — a pre-built Spring Boot application — and connect it to **PostgreSQL** (Storage) and **Redis** (Caching).

---

## 🎯 The Levels of Excellence

### Level 1: Basic Containerization (The Foundation)
**Goal**: Get the app running in a container.
- Write a `Dockerfile` for the `notes-app.jar`.
- Use `eclipse-temurin:21-jre-alpine`.
- Expose port `8081`.
- Run using `java -jar`.
- Start the app and the 2 required databases (Postgres & Redis) in `docker-compose.yml`.

### Level 2: Optimization & Persistence (The Real-World)
**Goal**: Make it fast and make it save data.
- **Dockerfile**: Use multi-stage if you're feeling adventurous, or just ensure proper layer caching.
- **Persistence**: Add a volume for PostgreSQL so your notes aren't lost when you run `down`.
- **Environment**: Use a `.env` file instead of hardcoding passwords in the YAML.

### Level 3: Reliability & Performance (The Pro)
**Goal**: Make it self-healing and stable.
- **Healthchecks**: Add healthchecks for Postgres AND Redis.
- **Orchestration**: Make the Notes API wait for both to be `healthy` before it starts.
- **Guard Rails**: Limit the API to 512MB RAM and 0.5 CPU.

### Level 4: Security (The Master)
**Goal**: Hide the sensitive keys.
- **Secrets**: Use Docker Secrets to hide the `DB_PASSWORD`.
- **Validation**: Use `docker compose config` to verify everything is perfect before deployment.

---

## 🛠️ Application Configuration

### Environment Variables Required
| Variable | Description | Value |
|----------|-------------|-------|
| `DB_HOST` | Hostname | `db` (or your service name) |
| `DB_NAME` | Database | `notesdb` |
| `REDIS_HOST` | Hostname | `redis` (or your service name) |
| `SERVER_PORT` | App port | `8081` |

### Testing Your Progress
```bash
# Level 1-2 check
docker compose up -d
curl http://localhost:8081/api/notes

# Level 3 check
docker compose ps # All must show "healthy"
docker stats      # Check the RAM limits

# Level 4 check
docker compose exec app ls /run/secrets/ # The secret should be there
```

---

## 💡 Pro Hints
- **DNS**: Use the service name (e.g., `db`) in your `DB_HOST` variable.
- **Ports**: The API runs on `8081` internally.
- **DB Readiness**: If you see `Connection Refused`, your app started before the database was ready! (Level 3 fixes this).

---
> [!IMPORTANT]
> If you get stuck, look at your notes from **Step 05** (Multi-stage), **Step 09** (Healthchecks), and **Step 12** (Secrets)!
