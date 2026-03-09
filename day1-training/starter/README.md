# 🚀 Practice: Containerize the Todo API

## Overview
In this practice session, you'll containerize a Spring Boot Todo API application using Docker and Docker Compose.

**You will NOT modify any Java code.** Your task is to write the Docker configuration files.

## Pre-requisites
- Docker Desktop installed and running
- Terminal / Command Line access

## What's Included
```
starter/
├── todo-app/
│   └── todo-app.jar    ← Pre-built Spring Boot application
├── Dockerfile           ← Your task: complete the TODOs
├── docker-compose.yml   ← Your task: complete the TODOs
└── README.md            ← You are here!
```

## The Application
The Todo API is a REST API built with Spring Boot that:
- Connects to a PostgreSQL database
- Provides CRUD endpoints for managing todos
- Runs on port **8080**
- Has a health check at `/actuator/health`

### API Endpoints
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | App info |
| GET | `/api/todos` | List all todos |
| POST | `/api/todos` | Create a todo |
| PUT | `/api/todos/:id` | Update a todo |
| DELETE | `/api/todos/:id` | Delete a todo |
| GET | `/actuator/health` | Health check |

---

## Step-by-Step Instructions

### Step 1: Complete the Dockerfile (5 TODOs)

Open `Dockerfile` and fill in each TODO:

1. **Base image** — Use `eclipse-temurin:21-jre-alpine`
2. **Working directory** — Set to `/app`
3. **Copy JAR** — Copy `todo-app.jar` as `app.jar`
4. **Expose port** — The app runs on `8080`
5. **Startup command** — Run `java -jar app.jar`

### Step 2: Build & Test Your Docker Image

```bash
# Build the image
docker build -t todo-app:1.0.0 .

# Run it (it will fail because no database — that's expected!)
docker run -p 8080:8080 todo-app:1.0.0
```

### Step 3: Complete the Docker Compose (11 TODOs)

Open `docker-compose.yml` and fill in each TODO:

1. **PostgreSQL environment** — Set DB name, user, password
2. **PostgreSQL port** — Map `5432:5432`
3. **PostgreSQL volume** — Persist data
4. **PostgreSQL network** — Connect to shared network
5. **App build context** — Point to current directory
6. **App port** — Map `8080:8080`
7. **App environment** — Set DB connection variables
8. **App dependency** — Depends on postgres
9. **App network** — Same network as postgres
10. **Define network** — Bridge driver
11. **Define volume** — For postgres data

### Step 4: Run the Full Stack

```bash
# Start everything
docker compose up -d

# Check status
docker compose ps

# View logs
docker compose logs -f todo-app

# Wait for "Started TodoApplication" in logs...
```

### Step 5: Test the API

```bash
# Check app info
curl http://localhost:8080/

# Create a todo
curl -X POST http://localhost:8080/api/todos \
  -H "Content-Type: application/json" \
  -d '{"title": "Learn Docker", "description": "Containerize all the things!", "priority": "HIGH"}'

# List all todos
curl http://localhost:8080/api/todos

# Health check
curl http://localhost:8080/actuator/health
```

### Step 6: Clean Up

```bash
# Stop and remove everything
docker compose down -v
```

---

## 🎯 Success Criteria
- [ ] Docker image builds successfully
- [ ] Both containers start without errors
- [ ] API responds on `http://localhost:8080`
- [ ] You can create and list todos
- [ ] Health check returns `{"status": "UP"}`

## 💡 Tips
- If the app fails to connect to the database, check that `DB_HOST` matches the postgres service name
- Use `docker compose logs postgres` to check if the database is ready
- Remember: Docker containers communicate using **service names**, not `localhost`
