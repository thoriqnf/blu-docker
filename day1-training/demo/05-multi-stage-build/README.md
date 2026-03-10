# Step 05: Multi-Stage Build (Java)

In this step, we learn how to create a production-ready, ultra-slim Docker image. This is one of the most important patterns in professional DevOps.

## The Problem: The "Heavy" Build Environment
To build a Java application, you need a full **JDK (Java Development Kit)** which includes compilers, build tools (Maven), and source code. This environment can be over **800MB**.

However, once the code is compiled into a `.jar` file, you only need a tiny **JRE (Java Runtime Environment)** to run it. 

## The Solution: Multi-Stage Builds
Think of this like a professional kitchen:
1. **The Prep Kitchen (Stage 1: Builder)**: You have all the knives, cutting boards, and raw ingredients. You do the heavy work here.
2. **The Dining Room (Stage 2: Runtime)**: You only bring the finished plate to the customer. All the messy tools stay in the kitchen.

### How it Works in the Dockerfile
1. **`FROM maven... AS builder`**: We start a temporary container with all the build tools, copy our code, and compile it.
2. **`FROM eclipse-temurin...`**: We start a **fresh**, tiny container.
3. **`COPY --from=builder ...`**: This is the magic line. It reaches back into the first container, grabs ONLY the finished `.jar` file, and puts it into the new one. 
4. The first container (and all its heavy tools) is then thrown away!

## Benefits

### 1. Minimal Size
By shipping only the essentials, your image goes from **800MB down to ~150MB**. Small images are faster to pull, faster to start, and save money on storage.

### 2. Maximum Security
In a production image, we don't include:
- Source code.
- Package managers (Maven).
- Compilers.
- Even common tools like `bash` or `curl` are often removed.

If a hacker gets into your container, they won't find any tools to help them explore or attack your network. **If the tool isn't there, it can't be used against you.**

## Commands to Run

### 1. Build the image
```bash
cd todo-app
docker build -t multi-stage-todo .
```

### 2. The "Connection Refused" Fix (Networking)
Your application needs a database to store Todo items. If you just run the app, it will fail with a "Connection Refused" error because it can't find PostgreSQL. 

Follow these steps to link them together:

#### A. Create a Network
Create a private "tunnel" where both containers can talk to each other by name.
```bash
docker network create todo-net
```

#### B. Start the Database
Start a PostgreSQL container on the same network.
```bash
docker run -d \
  --name db \
  --network todo-net \
  -e POSTGRES_DB=tododb \
  -e POSTGRES_PASSWORD=postgres \
  postgres:16-alpine
```

#### C. Start the App
Now, tell the app to look for `db` on that network.
```bash
docker run -d \
  -p 8080:8080 \
  --name app \
  --network todo-net \
  -e DB_HOST=db \
  -e DB_NAME=tododb \
  -e DB_USERNAME=postgres \
  -e DB_PASSWORD=postgres \
  multi-stage-todo
```

## Verifying Success
1. **Check Logs**: `docker logs -f app` (Wait for "Started TodoApplication").
2. **Access API**: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)
   - You should see `"status": "UP"`.

## Troubleshooting

### ❌ Error: "Bind for 0.0.0.0:8080 failed: port is already allocated"
**What happened?** Another container (perhaps from Step 02) or another app on your Mac is already using port 8080.
**The Fix:** 
1. Identify and stop the conflicting container: `docker ps`
2. Change the port in your run command to something else, like `-p 9090:8080`.

### ❌ Error: "Connection Refused" (inside logs)
**What happened?** The app started before the database was ready.
**The Fix:** Wait a few seconds and restart the app container: `docker restart app`.

## Cleanup
```bash
docker stop app db
docker rm app db
docker network rm todo-net
```
