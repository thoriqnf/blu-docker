---
theme: neversink

class: text-center
highlighter: shiki
lineNumbers: true
fonts:
  sans: Outfit
  serif: Outfit
  mono: Fira Code
aspectRatio: '16/9'
colorSchema: dark
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

<div class="mt-10 flex justify-center gap-8">
  <img src="/docker-architecture.png" class="h-40 rounded shadow-xl" />
  <img src="/k8s-architecture.png" class="h-40 rounded shadow-xl" />
</div>

<div class="abs-br m-6 flex gap-2">
  <a href="https://github.com/slidevjs/slidev" target="_blank" alt="GitHub" title="Open in GitHub"
    class="text-xl slidev-icon-btn opacity-50 !border-none !hover:text-white">
    <carbon-logo-github />
  </a>
</div>

---
layout: top-title
---

# Agenda for Today

What we will cover in this session:

<div class="mt-8 mx-auto w-3/4">
  <Admonition color="blue" title="Today's Topics" icon="mdi-format-list-bulleted" custom="text-lg">
    <v-clicks>
    <ol class="mt-4 space-y-4 list-decimal ml-6 pb-4">
      <li><b>DevOps Fundamentals</b></li>
      <li><b>Docker Containerization</b></li>
      <li><b>Practice: Docker Compose</b>
        <ul class="ml-6 list-disc text-blue-200 text-sm mt-1"><li><em>Hands-on: Build & run a Spring Boot + PostgreSQL Todo App</em></li></ul>
      </li>
      <li><b>Kubernetes Introduction</b></li>
      <li><b>YAML manifests + best practices</b></li>
      <li><b>ConfigMap & Secret</b></li>
      <li><b>Hands-on Exercise</b>
        <ul class="ml-6 list-disc text-blue-200 text-sm mt-1"><li><em>Containerize and compose a Notes API with Redis</em></li></ul>
      </li>
    </ol>
    </v-clicks>
  </Admonition>
</div>

---
layout: section
---

# Section 1: DevOps Fundamentals

The Mindset, Culture, and Tools

---
layout: two-cols-title
---

# The Problem: The "Wall of Confusion"

Before DevOps, Dev and Ops teams worked in silos, creating friction.
<br/>

::left::
<div class="mr-4 mt-8">
  <Admonition color="blue" title="Development (Dev)" icon="mdi-xml" customTitle="text-lg">
    <ul class="space-y-4 mt-4 pb-4">
      <li><b>Goal:</b> Ship new features fast.</li>
      <li><b>Mindset:</b> Embrace change.</li>
      <li class="italic border-l-2 border-blue-400 pl-2 mt-4 text-blue-200">"Works on my machine!"</li>
    </ul>
  </Admonition>
</div>

::right::
<div class="ml-4 mt-8">
  <Admonition color="rose" title="Operations (Ops)" icon="mdi-server-network" customTitle="text-lg">
    <ul class="space-y-4 mt-4 pb-4">
      <li><b>Goal:</b> Keep the system stable.</li>
      <li><b>Mindset:</b> Resist change (causes downtime).</li>
      <li class="italic border-l-2 border-rose-400 pl-2 mt-4 text-rose-200">"It doesn't work in production!"</li>
    </ul>
  </Admonition>
</div>

---
layout: two-cols-title
---

# What is DevOps?

It is **NOT** just a job title, a specific team, or a set of tools. It is a **cultural philosophy** and practice.

::left::
<div class="mr-4 mt-4">
  <Admonition color="emerald" title="The Core Pillars (CALMS)" icon="mdi-pillar" customTitle="text-lg">
    <ul class="space-y-2 mt-2">
      <li><b>Culture</b>: Empathy and collaboration over blame.</li>
      <li><b>Automation</b>: Removing manual toil to increase reliability.</li>
      <li><b>Lean</b>: Eliminating waste in processes.</li>
      <li><b>Measurement</b>: Data-driven decisions (metrics, logs).</li>
      <li><b>Sharing</b>: Feedback loops and cross-functional teams.</li>
    </ul>
  </Admonition>

  <div class="mt-4">
    <Admonition color="blue" title="The Mindset" icon="mdi-brain">
      <p class="italic">“You build it, you run it.”</p>
      <p class="text-xs text-blue-200 text-right">— Werner Vogels (CTO, Amazon)</p>
    </Admonition>
  </div>
</div>

::right::
<div class="ml-4 flex h-full items-center justify-center">
  <img src="/devops-cycle.png" class="w-full rounded-xl shadow-2xl border flex border-gray-700" alt="DevOps Cycle" />
</div>

---

# Continuous Integration & Continuous Deployment (CI/CD)

The engine that drives DevOps automation.

<div class="grid grid-cols-3 gap-4 mt-8">
  <Admonition color="blue" title="Continuous Integration" icon="mdi-source-merge">
    <ul class="space-y-2 mt-2 text-sm pb-2">
      <li>Developers merge code frequently into a central repository.</li>
      <li>Automated builds and tests run on every commit.</li>
      <li class="italic text-blue-200 border-l-2 border-blue-400 pl-2">Goal: Find and fix bugs quicker, improve software quality.</li>
    </ul>
  </Admonition>

  <Admonition color="amber" title="Continuous Delivery" icon="mdi-truck-delivery">
    <ul class="space-y-2 mt-2 text-sm pb-2">
      <li>Automatically prepare code changes for a release to production.</li>
      <li class="italic text-amber-200 border-l-2 border-amber-400 pl-2">Deployment is manual (a button click away).</li>
    </ul>
  </Admonition>

  <Admonition color="emerald" title="Continuous Deployment" icon="mdi-rocket-launch">
    <ul class="space-y-2 mt-2 text-sm pb-2">
      <li>Every change that passes all stages of your production pipeline is released to your customers.</li>
      <li class="italic text-emerald-200 border-l-2 border-emerald-400 pl-2">No human intervention.</li>
    </ul>
  </Admonition>
</div>

---
layout: section
---

# ☕ Intermezzo: The "3 AM Production Fire"

A short story of why DevOps matters.

---

# The 3 AM Production Fire

Before CI/CD and automated tests, releasing software was a terrifying event called "Deployment Night".

<div class="mt-8 mb-4">
  <Admonition color="rose" title="Incident Timeline" icon="mdi-fire" customTitle="text-lg">
    <div class="space-y-4 my-2 text-sm pl-4 relative border-l border-rose-800">
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>10:00 PM:</b> Operations team takes servers offline. Customers see maintenance page.</div>
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>10:30 PM:</b> Developers hand over a 50-page Word doc with install instructions.</div>
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>11:00 PM:</b> Ops engineer makes typo on step 34. Database migration fails.</div>
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>1:00 AM:</b> Team tries to roll back. Rollback script hasn't been tested in 6 months.</div>
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>3:00 AM:</b> CEO is awake. Developers are paged. Everyone is sweating.</div>
      <div class="relative"><span class="absolute -left-5 bg-rose-500 rounded-full w-2 h-2 mt-1.5"></span><b>7:00 AM:</b> Site is back up, but minor bug forces team to patch live.</div>
    </div>
  </Admonition>
</div>

<div class="mt-8 text-center text-lg font-bold text-rose-200">
  This is why "throw it over the wall" doesn't work.<br/>We need automated pipelines, not Word documents.
</div>

---
layout: section
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
layout: section
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

Docker relies on three fundamental Linux kernel features.

<div class="grid grid-cols-3 gap-6 mt-12">
  <Admonition color="blue" title="1. Namespaces" icon="mdi-eye-outline" customTitle="text-sm">
    <p class="text-xs italic text-blue-200 border-b border-blue-900 pb-2 mb-2">What a container <b>can see</b>.</p>
    <ul class="text-xs text-gray-300 space-y-2 pb-2">
      <li><b>PID:</b> Process IDs (Container sees PID 1)</li>
      <li><b>NET:</b> Network cards, firewall rules</li>
      <li><b>MNT:</b> Mount points, filesystems</li>
      <li><b>UTS:</b> Hostnames</li>
    </ul>
  </Admonition>

  <Admonition color="emerald" title="2. Control Groups" icon="mdi-speedometer" customTitle="text-sm">
    <p class="text-xs italic text-emerald-200 border-b border-emerald-900 pb-2 mb-2">What a container <b>can use</b>.</p>
    <ul class="text-xs text-gray-300 space-y-2 pb-2">
      <li>CPU limits (e.g. max 50% of 1 core)</li>
      <li>Memory limits (e.g. max 512MB RAM)</li>
      <li>Block I/O limits (Disk speed)</li>
    </ul>
  </Admonition>

  <Admonition color="amber" title="3. UnionFS" icon="mdi-layers-outline" customTitle="text-sm">
    <p class="text-xs italic text-amber-200 border-b border-amber-900 pb-2 mb-2">How an image is <b>stored</b>.</p>
    <ul class="text-xs text-gray-300 space-y-2 pb-2">
      <li>Layered filesystems (Overlay2)</li>
      <li>Directories mounted as a single view</li>
      <li>Enables lightweight caching & fast boots</li>
    </ul>
  </Admonition>
</div>

---

---
layout: two-cols-title
---

# Virtual Machines vs. Containers

<br/>

::left::
<div class="mr-4 mt-8">
  <Admonition color="rose" title="Virtual Machines" icon="mdi-server">
    <div class="flex flex-col space-y-2 mt-4 text-center items-center">
      <div class="bg-rose-900/40 p-3 rounded-lg border border-rose-800 text-sm w-4/5 shadow-inner">App 1 + Bins/Libs + Guest OS</div>
      <div class="bg-rose-900/40 p-3 rounded-lg border border-rose-800 text-sm w-4/5 shadow-inner">App 2 + Bins/Libs + Guest OS</div>
      <div class="bg-gray-800/80 p-2 rounded border border-gray-600 font-bold text-xs w-4/5">Hypervisor (VMWare, KVM)</div>
      <div class="bg-gray-800 p-2 rounded border border-gray-600 font-bold text-xs w-full">Host Operating System</div>
      <div class="bg-black p-2 rounded border border-gray-700 font-bold text-xs w-full">Server Hardware</div>
    </div>
    <ul class="text-left mt-8 text-sm space-y-2 pb-4">
      <li><span class="text-rose-400 font-bold px-1">✕</span> Heavy (Gigabytes)</li>
      <li><span class="text-rose-400 font-bold px-1">✕</span> Slow boot time (Minutes)</li>
      <li><span class="text-rose-400 font-bold px-1">✕</span> Dedicated RAM/CPU (Waste)</li>
    </ul>
  </Admonition>
</div>

::right::
<div class="ml-4 mt-8">
  <Admonition color="emerald" title="Containers" icon="mdi-docker">
    <div class="flex flex-col space-y-2 mt-4 items-center text-center relative h-[184px]">
      <div class="flex w-4/5 gap-2">
        <div class="bg-emerald-900/40 p-3 rounded-lg border border-emerald-800 text-sm w-1/2 shadow-inner h-full flex items-center justify-center">App 1<br/>Bins</div>
        <div class="bg-emerald-900/40 p-3 rounded-lg border border-emerald-800 text-sm w-1/2 shadow-inner h-full flex items-center justify-center">App 2<br/>Bins</div>
      </div>
      <div class="bg-blue-900/60 p-2 rounded border border-blue-700 font-bold text-xs w-4/5 transform absolute bottom-16">Container Engine (Docker)</div>
      <div class="bg-gray-800 p-2 rounded border border-gray-600 font-bold text-xs w-full absolute bottom-8">Host Operating System</div>
      <div class="bg-black p-2 rounded border border-gray-700 font-bold text-xs w-full absolute bottom-0">Server Hardware</div>
    </div>
    <ul class="text-left mt-8 text-sm space-y-2 pb-4">
      <li><span class="text-emerald-400 font-bold px-1">✓</span> Lightweight (Megabytes)</li>
      <li><span class="text-emerald-400 font-bold px-1">✓</span> Instant boot (Milliseconds)</li>
      <li><span class="text-emerald-400 font-bold px-1">✓</span> Shares OS kernel (High density)</li>
    </ul>
  </Admonition>
</div>

---
layout: statement
---

# What is Docker?

Docker is the most popular platform to build, share, and run containers.

<div class="mt-12 text-left">
  <Admonition color="cyan" title="The Standard" icon="mdi-information-outline" customTitle="text-xl">
    <p class="text-lg text-center font-semibold mt-4 mb-4">
      Docker standardizes how software is shipped, making implementations perfectly repeatable regardless of the underlying infrastructure.
    </p>
  </Admonition>
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

<div class="grid grid-cols-2 gap-4 mt-6">
  <Admonition color="blue" title="1. dockerd (The Manager)" icon="mdi-account-tie">
    <p class="text-xs text-blue-100">The permanent background daemon. It handles the REST API, manages images on the disk, and configures networking. But it <i>does not</i> run containers itself.</p>
  </Admonition>
  
  <Admonition color="emerald" title="2. containerd (The Supervisor)" icon="mdi-eye-check">
    <p class="text-xs text-emerald-100">An industry-standard core container runtime. `dockerd` tells `containerd` what image to run. It manages layers & lifecycle.</p>
  </Admonition>

  <Admonition color="rose" title="3. runc (The Executioner)" icon="mdi-engine">
    <p class="text-xs text-rose-100">The low-level binary that actually talks to the Linux kernel to create Namespaces and Cgroups. `runc` spawns the container process and exits.</p>
  </Admonition>

  <Admonition color="amber" title="4. containerd-shim" icon="mdi-glue">
    <p class="text-xs text-amber-100">Because `runc` exits immediately, `containerd-shim` stays glued to the container to keep `stdout/stderr` open without crashing.</p>
  </Admonition>
</div>

<v-drag pos="447,214,249,114">
  <StickyNote color="amber" title="PRO TIP" customTitle="text-lg font-bold">
    <div class="text-gray-900 mt-2 leading-snug font-medium">Because of containerd-shim, you can actually restart the main dockerd service without killing your containers!</div>
  </StickyNote>
</v-drag>

---

# Deep Dive: Docker Network Drivers

By default, Docker containers are isolated. We use "Drivers" to connect them.

<br/>

::left::
<div class="mr-4 mt-8 space-y-6">
  <Admonition color="blue" title="1. bridge (The Default)" icon="mdi-bridge">
    <ul class="text-xs text-blue-100 mt-2 space-y-2">
      <li>Creates a private, internal virtual network inside the host.</li>
      <li>Containers talk via internal DNS hostname.</li>
      <li><b>Port Mapping</b> is required for external traffic.</li>
    </ul>
  </Admonition>

  <Admonition color="emerald" title="2. host" icon="mdi-server-network">
    <ul class="text-xs text-emerald-100 mt-2 space-y-2">
      <li>Removes all network isolation entirely.</li>
      <li>Container shares the host's networking namespace.</li>
      <li><b>Use Case:</b> Extreme performance (High-Freq Trading).</li>
    </ul>
  </Admonition>
</div>

::right::
<div class="ml-4 mt-8 space-y-6">
  <Admonition color="gray" title="3. none" icon="mdi-cancel">
    <ul class="text-xs text-gray-300 mt-2 space-y-2">
      <li>Container receives a loopback interface (`localhost`) and nothing else.</li>
      <li><b>Use Case:</b> Absolute air-gapped system.</li>
    </ul>
  </Admonition>

  <Admonition color="purple" title="4. macvlan" icon="mdi-router-wireless">
    <ul class="text-xs text-purple-100 mt-2 space-y-2">
      <li>Bypasses bridges completely. Assigns a MAC address directly on physical network.</li>
      <li>Appears as independent physical PC.</li>
      <li><b>Use Case:</b> Legacy apps needing physical VLAN.</li>
    </ul>
  </Admonition>
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
layout: section
---

# Section 4: Kubernetes Introduction

Orchestrating containers at scale

---

# Why do we need Kubernetes?

Docker Compose is great for local development. But what about production?

<div class="mt-8 flex justify-center">
  <Admonition color="blue" title="Production Challenges" icon="mdi-head-question-outline" customTitle="text-lg">
    <ul class="mt-4 space-y-4 list-none pl-2 pb-2 text-blue-100">
      <li v-click><Icon icon="mdi-alert-circle-outline" class="text-blue-400 inline"/> What happens if a container crashes at 3 AM? <em class="text-emerald-400 pl-2">(Self-healing)</em></li>
      <li v-click><Icon icon="mdi-chart-line-variant" class="text-blue-400 inline"/> What if web traffic spikes by 1000%? <em class="text-emerald-400 pl-2">(Auto-scaling)</em></li>
      <li v-click><Icon icon="mdi-update" class="text-blue-400 inline"/> How do we deploy without downtime? <em class="text-emerald-400 pl-2">(Rolling Updates)</em></li>
      <li v-click><Icon icon="mdi-transit-connection-variant" class="text-blue-400 inline"/> How do containers talk to each other? <em class="text-emerald-400 pl-2">(Service Discovery)</em></li>
      <li v-click><Icon icon="mdi-lock-outline" class="text-blue-400 inline"/> How do we securely store passwords? <em class="text-emerald-400 pl-2">(Secret Management)</em></li>
    </ul>
  </Admonition>
</div>

<div v-click class="mt-8 text-2xl font-bold text-center bg-gradient-to-r from-blue-400 to-emerald-400 bg-clip-text text-transparent transform scale-105 transition-transform duration-500">
  Kubernetes (K8s) solves all of these problems automatically.
</div>

---

# Kubernetes Architecture 

<div class="grid grid-cols-2 gap-8 mt-6">
  <div class="space-y-4 flex flex-col justify-center">
    <Admonition color="blue" title="Control Plane (The Brains)" icon="mdi-brain">
      <ul class="text-xs space-y-2 mt-2 text-blue-100">
        <li><b>API Server:</b> The front-end (receives `kubectl` commands).</li>
        <li><b>etcd:</b> Distributed key-value store (the cluster's memory/state).</li>
        <li><b>Scheduler:</b> Decides which node a new Pod should go to.</li>
        <li><b>Controller Manager:</b> Maintains desired state (e.g., "keep 3 replicas").</li>
      </ul>
    </Admonition>

    <Admonition color="emerald" title="Worker Nodes (The Muscle)" icon="mdi-arm-flex-outline">
      <ul class="text-xs space-y-2 mt-2 text-emerald-100">
        <li><b>Kubelet:</b> The agent that talks to the API Server.</li>
        <li><b>Kube-Proxy:</b> Handles networking and load-balancing.</li>
        <li><b>Container Runtime:</b> Docker, containerd.</li>
      </ul>
    </Admonition>
  </div>

  <div class="flex items-center justify-center">
    <img src="/k8s-architecture.png" class="w-full rounded-xl shadow-2xl border border-blue-900 glow-blue-500/50" alt="K8s Architecture" />
  </div>
</div>

---
layout: section
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
layout: section
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
layout: section
---

# Final Hands-on Exercise
## Containerize the Notes API

Navigate to the `exercise/` folder and open the `README.md`.
Your challenge is to write a Dockerfile and a 3-service Docker Compose file entirely from scratch!
