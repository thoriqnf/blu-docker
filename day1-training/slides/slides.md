---
theme: seriph
background: https://images.unsplash.com/photo-1451187580459-43490279c0fa?q=80&w=2072&auto=format&fit=crop
class: text-center
highlighter: shiki
lineNumbers: false
info: |
  ## DevOps Day 1 Training
  Containerization & Kubernetes Fundamentals
drawings:
  persist: false
transition: slide-up
title: Day 1 - Containerization & Kubernetes Fundamentals
---

# DevOps Training: Day 1

## Containerization & Kubernetes Fundamentals

<div class="mt-10">
  <img src="/docker-architecture.png" class="h-40 rounded shadow mx-auto inline-block" />
  <img src="/k8s-architecture.png" class="h-40 rounded shadow mx-auto inline-block ml-4" />
</div>

<div class="abs-br m-6 flex gap-2">
  <a href="https://github.com/slidevjs/slidev" target="_blank" alt="GitHub" title="Open in GitHub"
    class="text-xl slidev-icon-btn opacity-50 !border-none !hover:text-white">
    <carbon-logo-github />
  </a>
</div>

---
layout: intro
---

# Agenda for Today

What we will cover in this 6-hour session:

<v-clicks>

1. **DevOps Fundamentals** (~30 min)
2. **Docker Containerization** (~45 min)
3. **Practice: Docker Compose** (~75 min)
   - *Hands-on: Build & run a Spring Boot + PostgreSQL Todo App*
4. **Kubernetes Introduction** (~40 min)
5. **YAML manifests + best practices** (~30 min)
6. **ConfigMap & Secret** (~25 min)
7. **Hands-on Exercise** (~60 min)
   - *Containerize and compose a Notes API with Redis*

</v-clicks>

---
layout: center
class: text-center
---

# Section 1: DevOps Fundamentals

The Mindset, Culture, and Tools

---
layout: default
---

# What is DevOps?

It is **NOT** just a job title or a set of tools. It is a **cultural philosophy**.

<div class="grid grid-cols-2 gap-4 mt-8">
<div>

### The Core Pillars

- **Culture**: Collaboration between Development and Operations.
- **Automation**: Removing manual toil.
- **Measurement**: Data-driven decisions (metrics, logs).
- **Sharing**: Feedback loops and cross-functional teams.

### The Mindset
*“You build it, you run it.”* — Werner Vogels (CTO, Amazon)
</div>
<div>
  <img src="/devops-cycle.png" class="w-full rounded shadow" alt="DevOps Cycle" />
</div>
</div>

---

# Continuous Integration & Continuous Deployment (CI/CD)

The engine that drives DevOps automation.

<v-clicks>

- **Continuous Integration (CI):**
  - Developers merge code frequently into a central repository.
  - Automated builds and tests run on every commit.
  - *Goal:* Find and fix bugs quicker, improve software quality.

- **Continuous Delivery (CD):**
  - Automatically prepare code changes for a release to production.
  - Deployment is manual (a button click away).

- **Continuous Deployment (CD):**
  - Every change that passes all stages of your production pipeline is released to your customers.
  - *No human intervention.*

</v-clicks>

---
layout: center
class: text-center
---

# Section 2: Docker Containerization

Packaging applications for consistency

---

# The Problem: "It works on my machine!"

Why do we need containers in the first place?

<div class="grid grid-cols-2 gap-8 mt-8">
<div>
<h3 class="text-red-500 mb-4">The Development Matrix of Hell</h3>

- Developer A uses Mac (Java 17, Postgres 14)
- Developer B uses Windows (Java 21, MySQL)
- Staging server is Ubuntu (Java 11, Postgres 12)
- Production server is RHEL...

*Result:* Code works locally, crashes in production. Dependencies conflict. Upgrades are terrifying.
</div>

<div v-click>
<h3 class="text-green-500 mb-4">The Container Solution</h3>

- Package the application AND all its dependencies together.
- OS libraries, runtime (JRE), configuration, code.
- Run exactly the same everywhere: local, staging, production.
</div>
</div>

---

# Virtual Machines vs. Containers

<div class="grid grid-cols-2 gap-8 mt-8 text-center">
<div>
  <h3>Virtual Machines</h3>
  <div class="bg-blue-100 p-4 rounded text-black font-semibold text-sm">App 1 + Bins/Libs + Guest OS (Linux)</div>
  <div class="bg-blue-100 p-4 rounded text-black font-semibold text-sm mt-2">App 2 + Bins/Libs + Guest OS (Windows)</div>
  <div class="bg-gray-300 p-2 rounded text-black font-bold mt-2">Hypervisor (VMWare, KVM)</div>
  <div class="bg-gray-400 p-2 rounded text-black font-bold mt-2">Host Operating System</div>
  <div class="bg-gray-500 p-2 rounded text-white font-bold mt-2">Server Hardware</div>

  <ul class="text-left mt-4 text-sm mt-8 space-y-2">
    <li>❌ Heavy (Gigabytes)</li>
    <li>❌ Slow boot time (Minutes)</li>
    <li>❌ Wastes resources (dedicates RAM/CPU)</li>
  </ul>
</div>
<div>
  <h3>Containers</h3>
  <div class="bg-green-100 p-4 rounded text-black font-semibold text-sm inline-block w-[48%]">App 1 + Bins/Libs</div>
  <div class="bg-green-100 p-4 rounded text-black font-semibold text-sm inline-block w-[48%] ml-2 mt-[2px]">App 2 + Bins/Libs</div>
  <div class="bg-blue-500 p-2 rounded text-white font-bold mt-[20px]">Container Engine (Docker)</div>
  <div class="bg-gray-400 p-2 rounded text-black font-bold mt-[8px]">Host Operating System</div>
  <div class="bg-gray-500 p-2 rounded text-white font-bold mt-[8px]">Server Hardware</div>

  <ul class="text-left mt-4 text-sm mt-[32px] space-y-2">
    <li>✅ Lightweight (Megabytes)</li>
    <li>✅ Instant boot time (Milliseconds)</li>
    <li>✅ Shares OS kernel (high density)</li>
  </ul>
</div>
</div>


---

# Docker Architecture

How Docker works under the hood.

<div class="grid grid-cols-2 gap-4">
<div>
<br/>

- **Docker Client:** The CLI (`docker run`, `docker build`). You talk to this.
- **Docker Daemon:** The background service on the host that creates and manages containers.
- **Docker Images:** Read-only templates with instructions to create a container.
- **Containers:** Runnable instances of an image.
- **Docker Registry:** Storage for images (Docker Hub, AWS ECR, local registry).

</div>
<div>
  <img src="/docker-architecture.png" class="w-full rounded shadow" alt="Docker Architecture" />
</div>
</div>

---

# The Dockerfile

The recipe for building an image.

```dockerfile {all|1-2|4-5|7-8|10-11|13-14|all}
# 1. Base Image - Start from an existing OS/Runtime
FROM eclipse-temurin:21-jre-alpine

# 2. Working Directory - Where should we put our files?
WORKDIR /app

# 3. Copy Files - Move files from your laptop to the container
COPY target/my-app.jar app.jar

# 4. Expose Ports - Document which port the app listens on
EXPOSE 8080

# 5. Container Command - What runs when the container starts?
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# Multi-stage Builds

Best practice for compiled languages like Java, Go, or Node (React/Angular).

```dockerfile
# Stage 1: Build the app (heavy image, includes Maven & JDK)
FROM maven:3.9-eclipse-temurin-21-alpine AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the app (lightweight image, only JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Only copy the final compiled JAR from the builder stage!
COPY --from=builder /build/target/app.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

*Result: Image size drops from ~800MB to ~150MB.*

---
layout: center
class: text-center
---

# Section 3: Practice - Docker Compose
Build & run a Todo App (Spring Boot + PostgreSQL)

---

# Introducing Docker Compose

Running multi-container applications easily.

- A `docker run` command for a database + an app becomes dozens of lines of flags.
- **Docker Compose** allows you to declare your entire stack in a single `yaml` file.
- Handles networks, volumes, and service dependencies automatically.

```yaml {all|2-3|4-8|10-14|all}
services:
  db:
    image: postgres:16-alpine
    environment:
      POSTGRES_DB: mydb
      POSTGRES_USER: myuser
      POSTGRES_PASSWORD: secretpassword

  api:
    build: .  # Build from Dockerfile in current dir
    ports:
      - "8080:8080"
    depends_on:
      - db
```

---
layout: iframe-right
url: https://docs.docker.com/compose/
---

# Hands-on: Building the Todo App

We have a Spring Boot API and we need PostgreSQL.

### Your Mission:
1. Navigate to `/starter`
2. Open `Dockerfile` and complete the `TODO` comments.
3. Open `docker-compose.yml` and complete the `TODO` comments.
4. Run: `docker compose up -d`
5. Test the API: `curl http://localhost:8080/`

<br/>
<div class="bg-blue-100 p-4 rounded text-black">
  <strong>Hint:</strong> Use the <code>README.md</code> in the starter folder for step-by-step guidance!
</div>

---
layout: center
class: text-center
---

# ☕ Break Time (15 Minutes)

Next up: Kubernetes Fundamentals

---
layout: center
class: text-center
---

# Section 4: Kubernetes Introduction

Orchestrating containers at scale

---

# Why do we need Kubernetes?

Docker Compose is great for local development. But what about production?

<v-clicks>

- What happens if a container crashes at 3 AM? *(Self-healing)*
- What if web traffic spikes by 1000%? *(Auto-scaling)*
- How do we deploy a new version without downtime? *(Rolling Updates)*
- How do containers on different servers talk to each other? *(Networking & Service Discovery)*
- Where do we securely store database passwords? *(Secret Management)*

</v-clicks>

<div v-click class="mt-8 text-2xl font-bold text-blue-500 text-center">
  Kubernetes (K8s) solves all of these problems.
</div>

---

# Kubernetes Architecture overview

<div class="grid grid-cols-5 gap-4">
<div class="col-span-2">
<br/>

**Control Plane (The Brains):**
- **API Server:** The front-end (receives `kubectl` commands).
- **etcd:** Distributed key-value store (the cluster's memory/state).
- **Scheduler:** Decides which node a new Pod should go to.
- **Controller Manager:** Maintains desired state (e.g., "keep 3 replicas running").

**Worker Nodes (The Muscle):**
- **Kubelet:** The agent that talks to the API Server.
- **Kube-Proxy:** Handles networking networking.
- **Container Runtime:** Docker, containerd.
</div>
<div class="col-span-3">
  <img src="/k8s-architecture.png" class="w-full rounded shadow" alt="K8s Architecture" />
</div>
</div>

---
layout: center
class: text-center
---

# Section 5: YAML Manifests
Deploying the Todo App to Kubernetes

---

# Deployments & Pods

- **Pod:** The smallest unit in K8s. Usually contains 1 container.
- **Deployment:** Manages Pods. Gives you replicas, scaling, and rolling updates.

```yaml {all|1-5|6-10|12-20|all}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: todo-app
  labels: { app: todo }
spec:
  replicas: 3          # Keep exactly 3 pods running!
  selector:
    matchLabels: { app: todo }
  template:            # ⬇️ This is the Pod definition ⬇️
    metadata:
      labels: { app: todo }
    spec:
      containers:
        - name: todo-app
          image: todo-app:1.0.0
          ports:
            - containerPort: 8080
          resources:
            limits: { cpu: "500m", memory: "512Mi" }
```

---

# Services

A Deployment creates Pods, but Pod IP addresses constantly change when they are recreated.
A **Service** acts as an internal load balancer with a stable internal IP and DNS name.

```yaml {all|6-7|8-10|11-14|all}
apiVersion: v1
kind: Service
metadata:
  name: todo-app-service
spec:
  # NodePort allows external access (ClusterIP is internal only)
  type: NodePort
  selector:
    # Route traffic to Pods with this label
    app: todo
  ports:
    - port: 80           # The port the Service listens on
      targetPort: 8080   # The port the Container listens on
      nodePort: 30080    # The port opened on the physical host
```

---
layout: center
class: text-center
---

# Section 6: ConfigMap & Secret

Managing application configuration securely

---

# ConfigMap

Separating configuration from code.
Do not hardcode environment variables like `DB_HOST` in your image!

```yaml {all|5-8|11-14|all}
apiVersion: v1
kind: ConfigMap
metadata:
  name: todo-config
data:
  # These will become environment variables
  APP_ENV: "production"
  DB_HOST: "todo-postgres-service"
```

In your Deployment YAML, you inject the entire ConfigMap:
```yaml
          # Inside your container spec:
          envFrom:
            - configMapRef:
                name: todo-config
```

---

# Secret

For sensitive data: Passwords, API keys, TLS certificates.
Values MUST be base64 encoded.

```yaml {all|6-7|9-13|all}
apiVersion: v1
kind: Secret
metadata:
  name: todo-secret
type: Opaque
data:
  # echo -n "secretpassword" | base64
  # IMPORTANT: This is encoded, NOT encrypted!
  DB_PASSWORD: c2VjcmV0cGFzc3dvcmQ=
```

When injected as an environment variable, K8s decodes it for the container:
```yaml
          env:
            - name: DB_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: todo-secret
                  key: DB_PASSWORD
```

---
layout: center
class: text-center
---

# Final Hands-on Exercise (60 min)
## Containerize the Notes API

Navigate to the `exercise/` folder and open the `README.md`.
Your challenge is to write a Dockerfile and a 3-service Docker Compose file entirely from scratch!
