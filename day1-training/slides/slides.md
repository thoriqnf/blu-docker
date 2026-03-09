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

What we will cover in this session:

<v-clicks>

1. **DevOps Fundamentals**
2. **Docker Containerization**
3. **Practice: Docker Compose**
   - *Hands-on: Build & run a Spring Boot + PostgreSQL Todo App*
4. **Kubernetes Introduction**
5. **YAML manifests + best practices**
6. **ConfigMap & Secret**
7. **Hands-on Exercise**
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

# The Problem: The "Wall of Confusion"

Before DevOps, Dev and Ops teams worked in silos, creating friction.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### Development (Dev)
- **Goal:** Ship new features fast.
- **Mindset:** Embrace change.
- *"Works on my machine!"*

</div>
<div>

### Operations (Ops)
- **Goal:** Keep the system stable.
- **Mindset:** Resist change (change causes downtime).
- *"It doesn't work in production!"*

</div>
</div>

*Result:* Pointing fingers, slow releases, and unhappy customers.

---
layout: default
---

# What is DevOps?

It is **NOT** just a job title, a specific team, or a set of tools. It is a **cultural philosophy** and practice.

<div class="grid grid-cols-2 gap-4 mt-8">
<div>

### The Core Pillars (CALMS)

- **Culture**: Empathy and collaboration over blame.
- **Automation**: Removing manual toil to increase reliability.
- **Lean**: Eliminating waste in processes.
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

---
layout: center
class: text-center
---

# ☕ Intermezzo: The "3 AM Production Fire"

A short story of why DevOps matters.

---

# The 3 AM Production Fire

Before CI/CD and automated tests, releasing software was a terrifying event called "Deployment Night".

<div class="space-y-4 mt-8">

1. **10:00 PM:** Operations team takes the servers offline. Customers see a maintenance page.
2. **10:30 PM:** Developers hand over a 50-page Word document with installation instructions.
3. **11:00 PM:** An Ops engineer makes a typo on step 34. The database migration fails.
4. **1:00 AM:** The team tries to roll back, but the rollback script hasn't been tested in 6 months.
5. **3:00 AM:** The CEO is awake. The developers are paged. Everyone is sweating.
6. **7:00 AM:** The site is back up, but a minor bug forces the team to patch it live.

</div>

<div class="mt-8 p-4 bg-red-900 border border-red-500 rounded text-white font-bold text-center">
  This is why "throw it over the wall" doesn't work. We need automated pipelines, not Word documents.
</div>

---
layout: center
class: text-center
---

# Section 2: Docker Containerization

Packaging applications for consistency

---

# Step 1: The "Why" (Bridging the Gap)
Before learning commands, understand the problem Docker solves.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### The "It works on my machine" Problem
-   You write code on Windows, but the server runs Linux.
-   It crashes because of a tiny version difference (e.g. Node 18 vs Node 20).
-   **Result:** Wasted hours debugging environment paths.

</div>
<div>

### The Solution: Isolation
-   Docker packages the code AND the environment (the OS, the libraries, the settings) into one unit.
-   **Concept to Master:** One container cannot "see" or mess with another container unless you allow it.

</div>
</div>

---
layout: center
class: text-center
---

# A Brief History of Containers

Before Docker, there was chaos.

---

# The Evolution of Isolation

Docker didn't invent containers. It just made them exceptionally easy to use.

- **1979 - Unix V7 (`chroot`):**
  - The concept of "changing root". A process could only see files down a specific directory branch, creating a very basic jail.
- **2000 - FreeBSD Jails:**
  - Added process isolation. A "jail" was a partition inside a FreeBSD OS that felt like a full independent system.
- **2008 - Linux Containers (LXC):**
  - The true precursor to Docker. It combined Cgroups (resource limiting) and Namespaces (isolation) into a usable tool, but it was incredibly complex to configure.
- **2013 - Docker:**
  - Introduced the Docker Engine, the portable Docker Image format, and a dead-simple CLI. It took LXC's power and gave it a great developer experience.

---

# How Containers Actually Work (Linux Magic)

Docker relies on three fundamental Linux kernel features. If you understand these, you understand containers.

<div class="grid grid-cols-3 gap-4 mt-8">
<div>

### 1. Namespaces
What a container **can see**.
<ul class="text-sm text-gray-400 mt-2 space-y-2">
  <li>**PID:** Process IDs (Container sees PID 1, Host sees PID 4503)</li>
  <li>**NET:** Network cards, firewall rules</li>
  <li>**MNT:** Mount points, filesystems</li>
  <li>**UTS:** Hostnames</li>
</ul>

</div>
<div>

### 2. Control Groups (Cgroups)
What a container **can use**.
<ul class="text-sm text-gray-400 mt-2 space-y-2">
  <li>CPU limits (e.g. max 50% of 1 core)</li>
  <li>Memory limits (e.g. max 512MB RAM)</li>
  <li>Block I/O limits (Disk speed)</li>
</ul>
*Prevents one bad container from crashing the whole server.*

</div>
<div>

### 3. Union File System (UnionFS)
How an image is **stored**.
<ul class="text-sm text-gray-400 mt-2 space-y-2">
  <li>Layered filesystems (Overlay2)</li>
  <li>Multiple directories are mounted as a single unified view.</li>
  <li>Enables Docker's lightweight image layers and fast boot times.</li>
</ul>

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

# What is Docker?

Docker is the most popular platform to build, share, and run containers.

<v-clicks>

- **Build:** Package your code and dependencies into an immutable "Image".
- **Share:** Distribute your Image globally via Registries (like Docker Hub).
- **Run:** Execute the Image as a "Container" locally or in the cloud.

</v-clicks>

<div class="mt-8 p-4 bg-blue-100 rounded text-black text-center font-semibold">
  Docker standardizes how software is shipped, making implementations perfectly repeatable regardless of the underlying infrastructure.
</div>

---

# How to Setup Docker

Getting started on your local machine.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### Installation

- **Mac / Windows:** Install [Docker Desktop](https://www.docker.com/products/docker-desktop/). It includes the Docker Engine GUI and the `docker` CLI. 
- **Linux:** Install `docker-engine` via your package manager (`apt`, `yum`).

</div>
<div>

### Verification

Open your terminal and check the version:
```bash
$ docker --version
Docker version 24.0.6, build ed223bc
```

Run a test container:
```bash
$ docker run hello-world
```
*If you see "Hello from Docker!", it's working!*

</div>
</div>

---

# What is a Docker Image?

Before creating a container, you need an Image. 

<div class="space-y-4 mt-8">

- An **Image** is a read-only template with instructions for creating a Docker container.
- It contains your code, runtime (e.g., Node.js, Java), system tools, libraries, and settings.
- Images are built from **Layers**. Each instruction adds a layer, which are cached for speed.

</div>

### Basic Image Commands:

```bash
# Search for an image on Docker Hub
$ docker search ubuntu

# Download an image to your machine
$ docker pull nginx:latest

# List downloaded images
$ docker image ls
```

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

# Deep Dive: The Docker Engine Internals

When you type `docker run`, what *actually* happens? The Docker Engine is not a monolith.

<div class="mt-8 space-y-4 text-sm bg-gray-900 border border-gray-700 p-6 rounded shadow-lg">

1. **`dockerd` (The Manager):** The permanent background daemon. It handles the REST API, manages images on the disk, and configures networking. But it *does not* run containers itself.
2. **`containerd` (The Supervisor):** An industry-standard core container runtime. `dockerd` tells `containerd` what image to run. `containerd` downloads the image layers and manages the container lifecycle (start, stop, pause).
3. **`runc` (The Executioner):** The low-level binary that actually talks to the Linux kernel to create Namespaces and Cgroups. `runc` spawns the container process and exits.
4. **`containerd-shim`:** Because `runc` exits immediately, `containerd-shim` stays glued to the container to keep `stdout/stderr` (logs) open, allowing you to run `docker logs` without `dockerd` crashing.

</div>

<div class="mt-8 text-center text-gray-400 text-sm">
  <strong>Why this matters:</strong> If dockerd crashes or restarts, your containers keep running perfectly fine without interruption thanks to containerd-shim!
</div>

---

# Deep Dive: Docker Network Drivers

By default, Docker containers are isolated. We use "Drivers" to connect them.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

<h3 class="text-blue-400 font-bold">1. bridge (The Default)</h3>

- Creates a private, internal virtual network inside the host.
- Containers on the same bridge can talk via internal DNS hostname.
- **Port Mapping (`-p 8080:80`)** is required to allow external traffic through the host firewall.

<h3 class="text-green-400 font-bold mt-4">2. host</h3>

- Removes all network isolation entirely. The container shares the host's networking namespace.
- **Use Case:** Extreme performance software (e.g. High-Frequency Trading API) that cannot afford the 1ms latency penalty of a virtual network hop.

</div>
<div>

<h3 class="text-gray-400 font-bold">3. none</h3>

- The container receives a loopback interface (`localhost`) and literally nothing else. Cannot reach the internet, cannot reach other containers.
- **Use Case:** Creating an absolute air-gapped system to securely process cryptographic keys without fear of data exfiltration.

<h3 class="text-purple-400 font-bold mt-4">4. macvlan</h3>

- Bypasses virtual bridges completely and assigns the container a MAC address directly on your physical office/data-center network.
- The container appears as an independent physical computer to your actual router.
- **Use Case:** Legacy applications that expect to be directly attached to a physical VLAN and broadcast their own IP.

</div>
</div>

---

# Step 4: Building Your Own (The Dockerfile)

The recipe for building an image.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### **Analogy**
It’s like a grocery list and cooking instructions combined into one file.

### **The Bridge**
Instead of manually installing Node.js or Python on a server, you write `FROM node:20`. Docker does the exact same installation for you every time.

</div>
<div>

```dockerfile {1-2|4-5|7-8|10-11|13-14}
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

</div>
</div>

---

# Advanced Dockerfile: COPY vs ADD

They look identical, but they behave differently.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### `COPY`
- The preferred, safer command.
- It literally just copies a file or folder from your laptop (the host) into the container's filesystem.
- **Rule of thumb:** Always use `COPY` unless you explicitly need `ADD` features.

```dockerfile
COPY ./src /app/src
COPY package.json /app/
```

</div>
<div>

### `ADD`
- The dangerous, powerful command.
- It copies files, **BUT** if the file is a `.tar.gz` archive, `ADD` will automatically extract it.
- **BUT** `ADD` can also download URLs directly into the container.

```dockerfile
# This will download from the internet during build
ADD https://example.com/big-dataset.json /app/data/

# This will magically extract the archive into /opt
ADD my-app-source.tar.gz /opt/
```

</div>
</div>

---

# Advanced Dockerfile: CMD vs ENTRYPOINT

The age-old confusing question: How do I start my app?

<div class="space-y-6 mt-8">

<div>
  <h3 class="text-blue-400">CMD (Command)</h3>
  <p class="text-sm">Sets the default command or parameters. **It is easily overridden** by the user at runtime.</p>
  <div class="bg-gray-800 p-2 rounded mt-2">
    <code>CMD ["python", "app.py"]</code><br/>
    <span class="text-xs text-gray-500">If user runs <code>docker run my-image bash</code>, the CMD is ignored, and <code>bash</code> runs instead.</span>
  </div>
</div>

<div>
  <h3 class="text-green-400">ENTRYPOINT</h3>
  <p class="text-sm">Configures a container that will run as an executable. **It cannot be easily overridden.**</p>
  <div class="bg-gray-800 p-2 rounded mt-2">
    <code>ENTRYPOINT ["nginx", "-g", "daemon off;"]</code><br/>
    <span class="text-xs text-gray-500">If user runs <code>docker run my-nginx bash</code>, Docker actually runs <code>nginx -g "daemon off;" bash</code> (which crashes NGINX).</span>
  </div>
</div>

</div>

<div class="mt-4 p-2 bg-gray-900 border-l-4 border-blue-500 rounded text-sm italic">
  <strong>Best Practice:</strong> Use ENTRYPOINT for the actual application binary, and use CMD to pass default arguments to that ENTRYPOINT.
</div>

---

# Dockerfile Best Practices & Caching

Docker images are built strictly layer-by-layer, from top to bottom.

1. **Each instruction (`RUN`, `COPY`, `ADD`) creates a new Layer.**
   - If Layer 2 changes, Docker has to rebuild Layer 2, Layer 3, Layer 4, etc.
   - Therefore, put instructions that change frequently (like copying source code) at the **BOTTOM** of the file.

2. **The classic Node.js caching trick:**
   ```dockerfile
   # BAD: Re-downloads all NPM packages every time you edit a single line of code!
   COPY . /app
   RUN npm install
   
   # GOOD: Only re-downloads NPM packages if package.json actually changed.
   COPY package*.json /app/
   RUN npm install
   COPY . /app
   ```

3. **Use `.dockerignore`:** 
   - Never copy your local `node_modules` or `.git` folder into the image. It bloats the image by gigabytes and overwrites Linux binaries with Mac binaries.

---

# Security: Running as Non-Root

By default, Docker containers run as the `root` user. **This is a massive security risk.**

If a hacker compromises your app inside the container, they are `root`. If they find a way to escape the container, they are `root` on your physical host server.

```dockerfile {all|3-4|5-6|8-9|all}
FROM node:20-alpine

# 1. Create a dedicated user and group (Alpine Linux uses addgroup/adduser)
RUN addgroup -S appgroup && adduser -S appuser -G appgroup

# 2. Tell Docker to switch to this user for the rest of the build and runtime
USER appuser

# 3. Now when the app runs, it has zero privileges to modify OS files!
CMD ["node", "server.js"]
```

---

# Multi-Stage Builds: The 1-Gigabyte Problem

Compiled languages (Java, Go, C++) require heavy SDKs to build, but only need a tiny runtime to actually execute.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### The Anti-Pattern (Huge Image)
If you build and run Java in one image, you must ship the heavy JDK to production.
- **Image Size:** 800MB+
- **Security:** Shipping compilers to production means hackers can compile rootkits directly on your container!

</div>
<div>

### The Solution (Multi-Stage)
You use multiple `FROM` statements. You build the app in Stage 1, copy the compiled binary, and throw away Stage 1 completely.
- **Image Size:** 150MB
- **Security:** Hardened, minimal attack surface.

</div>
</div>

---

# Multi-Stage Example 1: Java Spring Boot

```dockerfile {all|1-5|7-9|11-13|all}
# STAGE 1: "builder" (Heavy JDK ~ 400MB)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# STAGE 2: "production" (Light JRE ~ 100MB)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Magically pull the compiled JAR from Stage 1. Everything else is discarded!
COPY --from=builder /app/target/todo-api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# Multi-Stage Example 2: Go (The Ultimate Flex)

Go can compile to a static binary with exactly ZERO external OS dependencies.

```dockerfile {all|1-2|4|6-7|9-10|all}
# STAGE 1: Builder (Heavy Go SDK ~ 800MB)
FROM golang:1.22-alpine AS builder
WORKDIR /app
COPY . .
# Compile a 100% standalone static binary
RUN CGO_ENABLED=0 GOOS=linux go build -o my-api 

# STAGE 2: "scratch" (Empty Image ~ 0MB)
# The "scratch" image has no OS, no shell, no libraries. Literal nothingness.
FROM scratch

COPY --from=builder /app/my-api /my-api
ENTRYPOINT ["/my-api"]
```

**Final Image Size:** Exact size of your binary (~10MB). No OS vulnerabilities possible!

---

# Step 5: Persistence (Volumes)

**Crucial Beginner Tip:** When a container is deleted, everything inside it is wiped. If you have a database inside a container, your data dies with it!

<div class="mt-8 p-6 bg-gray-800 rounded border border-gray-600">

### The Bridge
**Volumes** are like "External Hard Drives" for your containers. You plug them in so that even if the container is destroyed, the data stays safe on your actual computer's hard drive.

</div>

---

# The 4 Types of Docker Mounts

Docker actually provides four distinct ways to manage state.

<div class="grid grid-cols-2 gap-4 mt-6 text-sm flex items-stretch">
<div class="bg-gray-800 p-4 border border-gray-700 rounded block h-full">

<h3 class="text-blue-400">1. Named Volumes (The Best)</h3>
<p class="text-xs mt-2 text-gray-300">Managed entirely by Docker. Docker chooses exactly where to store the data on your host OS (`/var/lib/docker/volumes/`). You just reference it by a friendly name.</p>
<code class="block mt-2 bg-black p-2">docker run -v my-db-data:/var/lib/mysql</code>

</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded block h-full">

<h3 class="text-green-400">2. Bind Mounts (The Developer Way)</h3>
<p class="text-xs mt-2 text-gray-300">You map an explicit, absolute folder path from your Mac/Windows into the container. Excellent for live-reloading code as you type.</p>
<code class="block mt-2 bg-black p-2">docker run -v $(pwd)/src:/app/src</code>

</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded block h-full">

<h3 class="text-red-400">3. Anonymous Volumes (The Danger)</h3>
<p class="text-xs mt-2 text-gray-300">You ask for a volume, but give it no name. Docker generates a random hash (`e3b0c442...`). If you lose the hash, you effectively lose the data.</p>
<code class="block mt-2 bg-black p-2">docker run -v /var/lib/mysql mysql</code>

</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded block h-full">

<h3 class="text-purple-400">4. tmpfs Mounts (The Secret)</h3>
<p class="text-xs mt-2 text-gray-300">Data never touches the host hard drive. It is written completely into the physical RAM memory. If the container stops, the RAM is flushed.</p>
<code class="block mt-2 bg-black p-2">docker run --tmpfs /app/secrets</code>

</div>
</div>

---

# Scenario: Taking a Backup

"I have a Named Volume holding my production Postgres database. How do I actually back that up to an S3 bucket or a `.tar.gz` file without shutting down the database?"

<div class="mt-8 space-y-4">

**The Trick:** You use an ephemeral container as a courier.

1. You start a temporary Alpine container (`--rm` means delete immediately when done).
2. You mount the *database* volume to `/volume`.
3. You mount your *laptop's desktop* to `/backup`.
4. You run a Linux command inside the short-lived container to zip them up.

```bash
docker run --rm \
  -v my-prod-db-data:/volume \          # Attach the DB
  -v /Users/thq/Desktop:/backup \       # Attach your Macbook
  alpine \
  tar cvf /backup/db-backup.tar /volume # Run the backup!
```
*Result: A neat `db-backup.tar` appears on your Macbook Desktop, while Postgres continues running happily!*

</div>

---

---

# Step 6: Orchestration (Docker Compose)

Real apps have a front-end, a back-end, and a database. Running three separate `docker run` commands is annoying.

- **Docker Compose:** This is a single YAML file that says "Start these three containers together and make sure they can talk to each other."
- **The Command:** `docker-compose up`. This is the "Magic Button" for developers.

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

# Deep Dive: Docker Compose Networking

How does the API find the Database without knowing its IP address?

<div class="mt-8 p-4 bg-gray-800 rounded border border-gray-600 space-y-4">

1. When you run `docker-compose up`, Docker creates a **custom internal Bridge Network**.
2. Both `db` and `api` are securely attached to this virtual network.
3. Docker provides **embedded DNS resolution**.
4. The `api` container simply needs to connect to the hostname `db` (the exact name of the service in the YAML file). Docker transparently resolves `db` to `172.18.0.3` (or whatever the internal IP is).

</div>

**Important:** You do NOT need to map ports (e.g., `ports: ["5432:5432"]`) for containers to talk to *each other* on the same Compose network. Port mapping is only required if you want your *laptop* (localhost) to talk to the container.

---

# Deep Dive: Depends_on & Healthchecks

`depends_on` only waits for the container to *start*, not for the application inside to be *ready*. If your Java API boots in 1 second, but Postgres takes 5 seconds to initialize up, your API will crash on boot!

**The Fix:** Use `healthcheck`.

```yaml {all|3-7|10-14|all}
services:
  db:
    image: postgres:16-alpine
    healthcheck:
      test: ["CMD-SHELL", "pg_isready -U myuser -d mydb"]
      interval: 5s
      timeout: 5s
      retries: 5

  api:
    build: .
    depends_on:
      db:
        condition: service_healthy
```

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
<div class="bg-blue-100 p-4 rounded text-black text-center max-w-2xl mx-auto">
  <strong>Hint:</strong> Use the <code>README.md</code> in the starter folder for step-by-step guidance!
</div>

---

# Scenario: The Monorepo Architecture

"My company uses a Monorepo. We have 5 microservices (Payment, Auth, Inventory, Storefront, DB) all in the exact same git repository. Do I need 5 `docker-compose.yml` files?"

**No.** Docker Compose shines at orchestrating a complete stack from a single file.

```yaml {1-8|10-14|16-20|all}
# /my-monorepo/docker-compose.yml
services:
  db:
    image: postgres:16
    volumes:
      - db-data:/var/lib/postgresql/data
  
  auth-service:
    # Instead of "build: .", point it to the subfolder!
    build: ./services/auth
    depends_on: { db: { condition: service_healthy } }

  payment-service:
    build: ./services/payment
    depends_on: [auth-service]
    
volumes:
  db-data: # This declares the Named Volume!
```

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

# Final Hands-on Exercise
## Containerize the Notes API

Navigate to the `exercise/` folder and open the `README.md`.
Your challenge is to write a Dockerfile and a 3-service Docker Compose file entirely from scratch!
