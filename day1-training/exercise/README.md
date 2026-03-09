# 🏋️ Hands-on Exercise: Containerize the Notes API

## Time: 60 minutes

## Overview
You've learned how to containerize the Todo API. Now it's your turn to do it independently!

You'll containerize a **Notes API** — a pre-built Spring Boot application — and compose it with **Redis** as a caching layer.

## What's Included
```
exercise/
├── notes-app/
│   └── notes-app.jar    ← Pre-built Spring Boot application
├── Dockerfile            ← Empty — write from scratch!
├── docker-compose.yml    ← Minimal starter
├── README.md             ← You are here!
└── solution/             ← Don't peek! (unless you're stuck 😄)
    ├── Dockerfile
    └── docker-compose.yml
```

## The Application

The Notes API is similar to the Todo API but:
- Has a **Redis cache** for improved performance
- Runs on port **8081**
- Connects to PostgreSQL for persistent storage
- Has a health check at `/actuator/health`

### Environment Variables
| Variable | Description | Example Value |
|----------|-------------|---------------|
| `DB_HOST` | PostgreSQL hostname | `postgres` |
| `DB_PORT` | PostgreSQL port | `5432` |
| `DB_NAME` | Database name | `notesdb` |
| `DB_USERNAME` | Database username | `notesuser` |
| `DB_PASSWORD` | Database password | `notespass123` |
| `REDIS_HOST` | Redis hostname | `redis` |
| `REDIS_PORT` | Redis port | `6379` |
| `SERVER_PORT` | App port | `8081` |
| `APP_NAME` | App display name | `Notes API` |

### API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | App info |
| GET | `/api/notes` | List all notes |
| POST | `/api/notes` | Create a note |
| PUT | `/api/notes/:id` | Update a note |
| DELETE | `/api/notes/:id` | Delete a note |
| GET | `/actuator/health` | Health check |

---

## Your Tasks

### Task 1: Write the Dockerfile (15 min)
Create a Dockerfile for the Notes API from scratch. It should:
- Use `eclipse-temurin:21-jre-alpine` as the base image
- Set working directory to `/app`
- Copy `notes-app.jar` as `app.jar`
- Expose port `8081`
- Run with `java -jar app.jar`

### Task 2: Write Docker Compose (25 min)
Create a `docker-compose.yml` with **3 services**:

1. **PostgreSQL** (port 5432)
   - Image: `postgres:16-alpine`
   - Database: `notesdb`, user: `notesuser`, password: `notespass123`
   - Persistent volume for data

2. **Redis** (port 6379)
   - Image: `redis:7-alpine`
   - Add a health check: `redis-cli ping`

3. **Notes API** (port 8081)
   - Build from your Dockerfile
   - Set all required environment variables
   - Depends on both postgres and redis
   - All services on the same network

### Task 3: Test It (10 min)
```bash
# Start everything
docker compose up -d

# Wait until all services are healthy
docker compose ps

# Test the API
curl http://localhost:8081/
curl -X POST http://localhost:8081/api/notes \
  -H "Content-Type: application/json" \
  -d '{"title": "My first note", "content": "Docker is awesome!"}'
curl http://localhost:8081/api/notes
```

### Bonus Task: Add Health Checks (10 min)
Add health checks to your Docker Compose for all three services:
- **PostgreSQL**: `pg_isready -U notesuser -d notesdb`
- **Redis**: `redis-cli ping`
- **Notes API**: `wget --spider http://localhost:8081/actuator/health`

Make the Notes API wait for healthy postgres and redis before starting.

---

## 🎯 Success Criteria
- [ ] Docker image builds successfully
- [ ] All 3 containers start and are healthy
- [ ] Notes API responds on `http://localhost:8081`
- [ ] You can create and list notes
- [ ] Data persists after `docker compose restart`

## 💡 Hints
- Redis default port is `6379`
- PostgreSQL default port is `5432`
- Docker service names become hostnames on the same network
- If stuck, review the Todo API's Docker setup from the practice session

## ⚠️ Common Mistakes
- Forgetting to put all services on the **same network**
- Using `localhost` instead of the **service name** for DB_HOST / REDIS_HOST
- Not waiting for database to be ready before starting the app
