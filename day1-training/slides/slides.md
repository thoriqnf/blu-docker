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


---
layout: default
---

# Agenda for Today

What we will cover in this session:

<div class="mt-12 mx-auto w-3/4">
  <Admonition color="slate" title="Today's Topics" icon="mdi-format-list-bulleted" custom="text-lg">
    <ul class="text-left space-y-2 list-none p-2 text-sm text-slate-200 font-medium">
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 1:</b> The DevOps Cultural Shift</li>
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 2:</b> Containerization with Docker</li>
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 3:</b> Docker Deep Dive (Networking & storage)</li>
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 4:</b> Journey to <b>Kubernetes (K8s)</b></li>
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 5:</b> Deploying to the Cluster</li>
    </ul>
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
  <Admonition color="slate" title="Development (Dev)" icon="mdi-xml" customTitle="text-lg">
    <ul class="text-left text-xs space-y-2 text-slate-200">
      <li>Focus: Velocity & Fast Delivery</li>
      <li>Writes code, adds new features</li>
      <li>Fixes bugs & implements changes</li>
    </ul>
  </Admonition>
</div>

::right::
<div class="ml-4 mt-8">
  <Admonition color="slate" title="Operations (Ops)" icon="mdi-server-network" customTitle="text-lg">
    <ul class="text-left text-xs space-y-2 text-slate-200">
      <li>Focus: Stability & Security</li>
      <li>Maintains infrastructure</li>
      <li>Ensures 24/7 availability</li>
    </ul>
  </Admonition>
</div>

---
layout: two-cols-title
---

# What is DevOps?

::left::

<div class="mt-12 space-y-6">
  <Admonition color="slate" title="The Core Pillars (CALMS)" icon="mdi-pillar" customTitle="text-lg">
    <ul class="text-xs space-y-2 text-slate-200">
      <li><b>Culture:</b> People over tools</li>
      <li><b>Automation:</b> Eliminate toil</li>
      <li><b>Lean:</b> Small batches, fast flow</li>
      <li><b>Measurement:</b> Use data to decide</li>
      <li><b>Sharing:</b> Break the silos</li>
    </ul>
  </Admonition>

  <Admonition color="slate" title="The Mindset" icon="mdi-brain">
    <p class="italic text-xs text-slate-200">"Quality is everyone's responsibility."</p>
  </Admonition>
</div>

::right::

<div class="flex items-center justify-center h-full">
  <img src="/blueprint_devops_cycle.png" class="w-64 opacity-80" />
</div>

---

# Continuous Integration & Continuous Deployment (CI/CD)

The engine that drives DevOps automation.

<div class="grid grid-cols-3 gap-4 mt-8">
  <Admonition color="slate" title="Continuous Integration" icon="mdi-source-merge">
    <ul class="space-y-2 mt-2 text-xs pb-2 text-slate-200">
      <li>Developers merge code frequently into a central repository.</li>
      <li>Automated builds and tests run on every commit.</li>
    </ul>
  </Admonition>

  <Admonition color="slate" title="Continuous Delivery" icon="mdi-truck-delivery">
    <ul class="space-y-2 mt-2 text-xs pb-2 text-slate-200">
      <li>Automatically prepare code changes for a release to production.</li>
      <li>Deployment is manual (a button click away).</li>
    </ul>
  </Admonition>

  <Admonition color="sky" title="Continuous Deployment" icon="mdi-rocket-launch">
    <ul class="space-y-2 mt-2 text-xs pb-2 text-slate-200">
      <li>Every change that passes all stages is released automatically.</li>
      <li>No human intervention required.</li>
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
  <Admonition color="sky" title="Incident Timeline" icon="mdi-fire" customTitle="text-lg">
    <div class="flex flex-col space-y-2 mt-4 text-xs font-mono text-slate-200">
      <div v-click class="flex items-center gap-2"><div class="w-16 text-slate-500">03:00 AM</div> <div class="p-1 px-2 bg-slate-800 rounded border border-slate-700">App crashes in Prod</div></div>
      <div v-click class="flex items-center gap-2"><div class="w-16 text-slate-500">03:15 AM</div> <div class="p-1 px-2 bg-slate-800 rounded border border-slate-700 italic">PagerDuty wakes up a tired engineer</div></div>
      <div v-click class="flex items-center gap-2"><div class="w-16 text-slate-500">03:45 AM</div> <div class="p-1 px-2 bg-slate-800 rounded border border-slate-700">Manually restarting everything...</div></div>
      <div v-click class="flex items-center gap-2 font-bold text-sky-400 mt-2"><div class="w-16">Result</div> <div>Loss of sleep, high stress, and business downtime.</div></div>
    </div>
  </Admonition>
</div>

<div class="mt-8 text-center text-lg font-bold text-rose-400">
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
layout: two-cols-title
---

# How Containers Actually Work

::left::

<div class="mt-8">
  <Admonition color="slate" title="1. Namespaces" icon="mdi-eye-outline" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Isolation of resources (Process, Network, User)</p>
  </Admonition>
</div>

::right::

<div class="mt-8 space-y-4">
  <Admonition color="slate" title="2. Control Groups" icon="mdi-speedometer" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Resource limits (CPU, RAM, I/O)</p>
  </Admonition>

  <Admonition color="slate" title="3. UnionFS" icon="mdi-layers-outline" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Efficient layered file systems (Copy-on-write)</p>
  </Admonition>
</div>

---
layout: two-cols-title
---

# Virtual Machines vs. Containers

::left::

<div class="mt-8">
  <Admonition color="slate" title="Virtual Machines" icon="mdi-server">
    <ul class="text-xs space-y-2 text-slate-200">
      <li>Slow startup (minutes)</li>
      <li>High overhead (GBs of RAM)</li>
      <li>Includes full Guest OS</li>
      <li class="font-bold underline">Hardware-level isolation</li>
    </ul>
  </Admonition>
</div>

::right::

<div class="mt-8">
  <Admonition color="sky" title="Containers" icon="mdi-docker">
    <ul class="text-xs space-y-2 text-slate-200">
      <li>Fast startup (seconds)</li>
      <li>Low overhead (MBs of RAM)</li>
      <li>Shares Host OS Kernel</li>
      <li class="font-bold underline">Process-level isolation</li>
    </ul>
  </Admonition>
</div>

---
layout: fact
---

# 🧠 Quick Check!
## What is the main difference between a VM and a Container in terms of the Operating System?

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  Containers share the <b>Host OS Kernel</b>, while VMs include a full <b>Guest OS</b>.
</Admonition>
</v-click>

---
layout: default
---

# What is Docker?

Docker is the most popular platform to build, share, and run containers.

<div class="mt-12 text-left">
  <Admonition color="slate" title="The Standard" icon="mdi-information-outline" customTitle="text-xl">
    <p class="text-lg text-center font-semibold mt-4 mb-4 text-slate-200">
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

# Docker Setup Gotchas (Deepening the OS Setup)

Beginners often hit walls immediately after installation. Be aware of your host OS:

<div class="grid grid-cols-2 gap-4 mt-6">
  <Admonition color="slate" title="1. Windows (WSL2)" icon="mdi-microsoft-windows">
    <p class="text-[10px] text-slate-200">Docker runs inside the Windows Subsystem for Linux (WSL2). <br/><br/><b>Tip:</b> Keep your source code inside the WSL2 filesystem (e.g., <code>\\wsl$\Ubuntu\home\user</code>) rather than <code>/mnt/c/</code> for profoundly faster performance.</p>
  </Admonition>

  <Admonition color="slate" title="2. Linux (Post-install)" icon="mdi-linux">
    <p class="text-[10px] text-slate-200">Tired of typing <code>sudo docker ...</code>?<br/><br/><b>Fix:</b> Add your user to the docker group so you can run commands as a non-root user.</p>
    <div class="bg-gray-800 p-2 mt-2 rounded"><code>sudo usermod -aG docker $USER</code></div>
  </Admonition>

  <Admonition color="slate" title="3. Apple Silicon (M1/M2/M3)" icon="mdi-apple">
    <p class="text-[10px] text-slate-200">Macs use ARM, not standard Intel (x86) architectures.<br/><br/><b>Fix:</b> Use <code>--platform linux/amd64</code> when building images on a Mac to ensure they work on standard cloud servers.</p>
  </Admonition>
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
layout: fact
---

# 🧠 Quick Check!
## True or False: Docker Image layers are read-only.

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  <b>True!</b> Every layer is a read-only snapshot. Only the top "Container Layer" is writable.
</Admonition>
</v-click>

---

# Image Management & Registries (The "Push")

`docker pull` handles downloading. What about the "Ship" part of "Build, Ship, Run"?

<div class="mt-8 space-y-4">
  <Admonition color="slate" title="The Shipping Workflow" icon="mdi-truck-delivery">
    <ol class="text-xs space-y-2 mt-4 text-slate-200 list-decimal pl-6">
      <li><b>Build:</b> <code>docker build -t username/my-app:v1 .</code></li>
      <li><b>Authenticate:</b> <code>docker login</code> (to Docker Hub or private registry)</li>
      <li><b>Ship:</b> <code>docker push username/my-app:v1</code></li>
    </ol>
  </Admonition>
</div>

<div class="mt-6 flex justify-center">
  <Admonition color="rose" title="Avoid the :latest Trap" icon="mdi-alert" customTitle="font-bold">
    <p class="text-[10px] text-rose-100 leading-relaxed">
      Using <code>:latest</code> in production is a <b>terrible practice</b> because you never know exactly what version of your code you are running, and rollbacks are virtually impossible. Always use semantic versioning tags (e.g., <code>:1.0.1</code>).
    </p>
  </Admonition>
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

# Deep Dive: The Docker Engine Internals

When you type `docker run`, what *actually* happens? The Docker Engine is not a monolith.

<div class="grid grid-cols-2 gap-4 mt-4">
  <Admonition color="slate" title="1. dockerd (The Manager)" icon="mdi-account-tie">
    <p class="text-[10px] text-slate-200 leading-tight">Permanent background daemon. Handles REST API, manages images/disk, and configures networking.</p>
  </Admonition>

  <Admonition color="slate" title="2. containerd (The Supervisor)" icon="mdi-eye-check">
    <p class="text-[10px] text-slate-200 leading-tight">Industry-standard core container runtime. Tells runc what image to run. Manages layers & lifecycle.</p>
  </Admonition>

  <Admonition color="slate" title="3. runc (The Executioner)" icon="mdi-engine">
    <p class="text-[10px] text-slate-200 leading-tight">Low-level binary talking to Linux kernel to create Namespaces and Cgroups. Spawns process and exits.</p>
  </Admonition>

  <Admonition color="slate" title="4. containerd-shim" icon="mdi-glue">
    <p class="text-[10px] text-slate-200 leading-tight">Allows restarting dockerd without killing containers. Stays glued to runc to keep pipes open.</p>
  </Admonition>
</div>

<div class="mt-8">
  <Admonition color="amber" title="PRO TIP" icon="mdi-lightbulb-on" customTitle="text-lg font-bold">
    <div class="text-slate-200 mt-2 leading-relaxed font-medium text-xs">
      Because of <b>containerd-shim</b>, you can actually restart the main dockerd service without killing your containers! 
      This is the secret to high-availability in maintenance windows.
    </div>
  </Admonition>
</div>

---
layout: default
---

# Dockerfile: Key Instructions (Part 1)

<div class="grid grid-cols-2 gap-4 text-[10px] mt-6">
  <Admonition color="slate" title="FROM" icon="mdi-image-filter-none">
    The base image. Always use specific tags. <br/> <code>FROM node:18-alpine</code>
  </Admonition>
  <Admonition color="slate" title="WORKDIR" icon="mdi-folder-open">
    Sets the current directory for all following commands. <br/> <code>WORKDIR /app</code>
  </Admonition>
  <Admonition color="slate" title="RUN" icon="mdi-console">
    Executes a command (e.g., install packages). Each RUN creates a layer. <br/> <code>RUN npm install</code>
  </Admonition>
  <Admonition color="slate" title="CMD" icon="mdi-play">
    The default command to run when the container starts. <br/> <code>CMD ["npm", "start"]</code>
  </Admonition>
</div>

---

# Dockerfile: Key Instructions (Part 2)

<div class="grid grid-cols-2 gap-4 text-[10px] mt-6">
  <Admonition color="sky" title="COPY vs ADD" icon="mdi-file-move">
    <b>COPY:</b> Moves files from host to image. <br/> 
    <b>ADD:</b> Similar, but can extract tarballs and fetch URLs. (Use COPY unless you need extract).
  </Admonition>
  <Admonition color="sky" title="ENV vs ARG" icon="mdi-variable">
    <b>ENV:</b> Available both at build time and inside the running container. <br/>
    <b>ARG:</b> Only available during the build process.
  </Admonition>
  <Admonition color="slate" title="EXPOSE" icon="mdi-lan-connect">
    Documentation for which ports should be mapped. <br/> <code>EXPOSE 8080</code>
  </Admonition>
</div>

---

# Troubleshooting: Docker "Exec" & Logs

How do you debug an app that is running headlessly inside an isolated container?

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### 1. Interactive Debugging
You can "SSH" (conceptually) directly into a running container:

```bash
# General Linux syntax
$ docker exec -it <container_id> /bin/bash

# Alpine Linux (no bash)
$ docker exec -it <container_id> sh
```
*You are now inside the container, with its local filesystem and environment.*

</div>
<div>

### 2. Live Log Streaming
To watch what your application is writing to stdout/stderr in real-time:

```bash
# Print all logs and stream new ones
$ docker logs -f <container_id>

# Print only the last 50 lines and stream
$ docker logs --tail 50 -f <container_id>
```

</div>
</div>

---

# Housekeeping & Hygiene

Docker runs on layers, caching, and hidden networks. It will quickly eat up dozens of gigabytes of disk space if left unchecked.

<div class="mt-8">
  <Admonition color="sky" title="The Magic Cleanup Command" icon="mdi-broom" customTitle="text-lg">
    <div class="bg-slate-900 p-2 my-2 rounded font-mono text-sm leading-none inline-block">docker system prune</div>
    <div class="text-xs text-slate-200 mt-2">
      This safely deletes everything that is not actively running:<br/>
      - All stopped containers<br/>
      - All unused networks<br/>
      - All dangling build cache
    </div>
  </Admonition>
</div>

<div class="mt-6 flex justify-center">
  <Admonition color="slate" title="What are Dangling Images?" icon="mdi-ghost">
    <p class="text-[10px] text-slate-300">
      Every time you rewrite your code and rebuild an image using the same tag (e.g., <code>:v1</code>), the previous image doesn't get deleted! It loses its tag, becoming a hidden "ghost" image (<code>&lt;none&gt;:&lt;none&gt;</code>) that eats your hard drive. Pruning cleans these up.
    </p>
  </Admonition>
</div>

---
layout: section
---

# Docker Deep Dive
Persistence, Networking, and Orchestration

---
layout: two-cols-title
---

# Deep Dive: Docker Network Drivers

By default, Docker containers are isolated. We use "Drivers" to connect them.

<br/>

::left::
<div class="mr-4 mt-8 space-y-6">
  <Admonition color="slate" title="1. bridge (The Default)" icon="mdi-bridge">
    <p class="text-xs text-slate-200">Creates a private internal network on the host. Containers talk via IP or name. Most common for stand-alone containers.</p>
  </Admonition>

  <Admonition color="sky" title="2. host" icon="mdi-server-network">
    <p class="text-xs text-slate-200">Removes network isolation between container and host. Container uses host's IP directly (No port mapping needed).</p>
  </Admonition>
</div>

::right::
<div class="ml-4 mt-8 space-y-6">
  <Admonition color="slate" title="3. none" icon="mdi-cancel">
    <p class="text-xs text-slate-200">Complete network isolation. Only has a loopback interface (`127.0.0.1`). Use for batch jobs with no networking.</p>
  </Admonition>

  <Admonition color="slate" title="4. macvlan" icon="mdi-router-wireless">
    <p class="text-xs text-slate-200">Assigns a MAC address to a container, making it appear as a physical device on your network. (High performance, advanced use).</p>
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
layout: fact
---

# 🧠 Quick Check!
## Which command should you use to copy a .tar.gz and automatically extract it?

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  <b>ADD</b>. (But use COPY for everything else!)
</Admonition>
</v-click>

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
layout: fact
---

# 🧠 Quick Check!
## Which instruction is easily overridden when you run `docker run my-image <command>`?

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  <b>CMD</b>. The new command replaces whatever was in CMD.
</Admonition>
</v-click>

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
layout: fact
---

# 🧠 Quick Check!
## Which mount type is best for live-reloading code during development?

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  <b>Bind Mounts</b> (mapping a host folder directly).
</Admonition>
</v-click>

---

# The 4 Types of Docker Mounts

Docker actually provides four distinct ways to manage state.

<div class="grid grid-cols-2 gap-4 mt-6 text-sm flex items-stretch">
<div class="bg-slate-800/40 p-4 border border-slate-700 rounded block h-full">
<h3 class="text-slate-300 text-sm font-bold">1. Named Volumes (The Best)</h3>
<p class="text-[10px] mt-2 text-slate-400">Managed by Docker. Stored in `/var/lib/docker/volumes/`. Persistence for databases.</p>
</div>

<div class="bg-slate-800/40 p-4 border border-slate-700 rounded block h-full">
<h3 class="text-slate-300 text-sm font-bold">2. Bind Mounts</h3>
<p class="text-[10px] mt-2 text-slate-400">Maps explicit host folder path. Best for live-reloading code during Dev.</p>
</div>

<div class="bg-slate-800/40 p-4 border border-slate-700 rounded block h-full">
<h3 class="text-slate-300 text-sm font-bold">3. Anonymous Volumes</h3>
<p class="text-[10px] mt-2 text-slate-400">Docker generates random hash. Hard to track, data potentially lost on deletion.</p>
</div>

<div class="bg-slate-800/40 p-4 border border-slate-700 rounded block h-full">
<h3 class="text-slate-300 text-sm font-bold">4. tmpfs Mounts</h3>
<p class="text-[10px] mt-2 text-slate-400">Written to RAM only. Flushed when container stops. High speed & security.</p>
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
layout: fact
---

# 🧠 Quick Check!
## Does my-app need to know the IP address of my-db inside Docker Compose?

<v-click>
<Admonition color="sky" title="Answer" icon="mdi-check">
  <b>No!</b> It uses the <b>Service Name</b> (Docker's built-in DNS).
</Admonition>
</v-click>

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

# Resource Constraints (Cgroups)

One "leaky" container can crash your whole server by consuming 100% of its RAM or CPU. You must define limits.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### CLI Flags (The "Blast Radius")
You can strictly limit a container's resources when running it:

```bash
# Limit to 512MB RAM and 50% of 1 CPU core
$ docker run \
  --memory="512m" \
  --cpus="0.5" \
  nginx
```

</div>
<div>

### Docker Compose Equivalent
In Compose, you map these limits under the `deploy` section:

```yaml {all|4-8|all}
services:
  api:
    image: my-app:v1
    deploy:
      resources:
        limits:
          cpus: '0.50'
          memory: 512M
```

</div>
</div>

---

---
layout: section
---

# 🏗️ Exercise Time: The Todo API
## Goal: Containerize a multi-service stack

---

# Practice: Containerize the Todo API

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

### 🎯 Success Criteria
- [x] Docker image builds successfully
- [x] Both containers start (App + DB)
- [x] API responds on `localhost:8080`
- [x] Health check returns `UP`

</div>
<div>

### 📝 Instructions
1. Navigate to `/starter`
2. Complete **5 TODOs** in `Dockerfile`
3. Complete **11 TODOs** in `docker-compose.yml`
4. Run `docker compose up -d`
5. Test with `curl http://localhost:8080/`

</div>
</div>

<div class="mt-8 text-center text-sm italic text-slate-400">
  Check the <code>README.md</code> in the starter folder for step-by-step guidance!
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

# Kubernetes (K8s) Fundamentals
Orchestrating containers at scale

---
layout: default
---

# The "Why K8s" Bridge

Docker Compose is for **local development** and single-node environments. Kubernetes is for **production** and multi-node clusters.

<div class="mt-8 flex justify-center">
  <Admonition color="blue" title="The Shipping Analogy" icon="mdi-ship-wheel" customTitle="text-lg">
    <table class="w-full text-left text-xs text-slate-200 mt-2">
      <tbody>
        <tr class="border-b border-slate-700">
          <td class="p-2 w-12"><mdi-package-variant-closed class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold w-1/4">Docker</td>
          <td class="p-2">A single shipping container.</td>
        </tr>
        <tr class="border-b border-slate-700">
          <td class="p-2"><mdi-truck-cargo-container class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold text-sky-300">Docker Compose</td>
          <td class="p-2">A small truck carrying a few containers locally.</td>
        </tr>
        <tr>
          <td class="p-2"><mdi-crane class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold text-blue-400">Kubernetes</td>
          <td class="p-2">The automated port facility managing thousands of ships and containers.</td>
        </tr>
      </tbody>
    </table>
  </Admonition>
</div>

---

# Why do we need Kubernetes?

Docker Compose is great for local development. But what about production?

<div class="mt-8 flex justify-center">
  <Admonition color="blue" title="Production Challenges" icon="mdi-head-question-outline" customTitle="text-lg">
    <ul class="mt-4 space-y-4 list-none pl-2 pb-2 text-slate-300 text-xs">
      <li v-click><mdi-alert-circle-outline class="text-blue-400 inline"/> What happens if a container crashes at 3 AM? <em class="text-blue-400 pl-2 text-[10px]">(Self-healing)</em></li>
      <li v-click><mdi-chart-line-variant class="text-blue-400 inline"/> What if web traffic spikes by 1000%? <em class="text-blue-400 pl-2 text-[10px]">(Auto-scaling)</em></li>
      <li v-click><mdi-update class="text-blue-400 inline"/> How do we deploy without downtime? <em class="text-blue-400 pl-2 text-[10px]">(Rolling Updates)</em></li>
      <li v-click><mdi-transit-connection-variant class="text-blue-400 inline"/> How do containers talk to each other? <em class="text-blue-400 pl-2 text-[10px]">(Service Discovery)</em></li>
      <li v-click><mdi-lock-outline class="text-blue-400 inline"/> How do we securely store passwords? <em class="text-blue-400 pl-2 text-[10px]">(Secret Management)</em></li>
    </ul>
  </Admonition>
</div>

<div v-click class="mt-8 text-2xl font-bold text-center text-blue-400">
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
    </div>

  <div>
    <Admonition color="blue" title="Worker Nodes (The Muscle)" icon="mdi-arm-flex-outline">
      <ul class="text-xs space-y-2 mt-2 text-slate-300">
        <li><b>Kubelet:</b> The agent that talks to the API Server.</li>
        <li><b>Kube-Proxy:</b> Handles networking and load-balancing.</li>
        <li><b>Container Runtime:</b> Docker, containerd.</li>
      </ul>
    </Admonition>
  </div>
</div>

---
layout: section
---

# YAML Manifests
Deploying the Todo App to Kubernetes

---

# YAML "Gotchas" (Pro-Tip)

Kubernetes heavily relies on YAML manifests. **YAML is notoriously sensitive to spacing.**

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### The Golden Rules
1. **Spaces, never tabs!** Always use exactly 2 spaces for each indentation level.
2. **Watch your dashes:** A dash (`-`) means an item in a list (array). Without it, it's just a dictionary value.
3. **Strings:** Usually you don't need quotes, but wrap numbers like `"500m"` or versions like `"1.22"` in quotes to prevent parsing errors.

</div>
<div>

### Stop Debugging Spaces
Do not spend 30 minutes debugging a "missing dash".

<Admonition color="sky" title="IDE Lifesaver" icon="mdi-lifebuoy">
  <p class="text-[10px] text-slate-200">
    Install the <b>Kubernetes extension for VS Code</b> (or your preferred IDE). It provides live YAML linting, auto-completion, and will immediately highlight spacing errors before you ever run <code>kubectl apply</code>.
  </p>
</Admonition>

</div>
</div>

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

# Networking Visuals: Demystifying Ports

This is the #1 point of confusion. How does a request actually reach the Pod?

<div class="mt-6 p-4 bg-gray-900 rounded-lg border border-gray-700 font-mono text-center shadow-lg flex items-center justify-center text-sm">
  <div class="text-rose-400 font-bold bg-gray-800 p-2 rounded">Internet</div>
  <mdi-arrow-right-thick class="mx-2 text-slate-500 text-xl" />
  <div class="text-sky-400 bg-gray-800 p-2 rounded border border-sky-900">NodePort<br/><span class="text-xs text-sky-200">30080</span></div>
  <mdi-arrow-right-thick class="mx-2 text-slate-500 text-xl" />
  <div class="text-blue-400 bg-gray-800 p-2 rounded border border-blue-900">Service Port<br/><span class="text-xs text-blue-200">80</span></div>
  <mdi-arrow-right-thick class="mx-2 text-slate-500 text-xl" />
  <div class="text-emerald-400 bg-gray-800 p-2 rounded border border-emerald-900">targetPort<br/><span class="text-xs text-emerald-200">8080</span></div>
</div>

<div class="mt-6">
  <table class="w-full text-left text-[10px] text-slate-200 mt-2 border-collapse">
    <thead>
      <tr class="bg-gray-800 text-slate-100">
        <th class="p-2 border border-slate-700">Port Name</th>
        <th class="p-2 border border-slate-700">Where is it?</th>
        <th class="p-2 border border-slate-700">What does it do?</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td class="p-2 border border-slate-700 font-bold text-emerald-400">targetPort</td>
        <td class="p-2 border border-slate-700">Inside the Pod</td>
        <td class="p-2 border border-slate-700">The actual port your application code listens on (e.g., Spring Boot on 8080).</td>
      </tr>
      <tr>
        <td class="p-2 border border-slate-700 font-bold text-blue-400">port (Service Port)</td>
        <td class="p-2 border border-slate-700">The Service (Internal)</td>
        <td class="p-2 border border-slate-700">The stable internal port other Pods use to talk to this service.</td>
      </tr>
      <tr>
        <td class="p-2 border border-slate-700 font-bold text-sky-400">nodePort</td>
        <td class="p-2 border border-slate-700">The physical server</td>
        <td class="p-2 border border-slate-700">Opens a port (30000-32767) on the external IP of the physical K8s node.</td>
      </tr>
    </tbody>
  </table>
</div>

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

---
layout: section
---

# 🏆 Final Challenge: The Notes API
## Independent Practice: App + DB + Redis

---

# Exercise: The Notes API

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

### 🚀 Your Mission
- Build a Dockerfile **from scratch** for port `8081`.
- Compose **3 Services**: Postgres + Redis + Notes API.
- Implement **Healthchecks** and resource limits.

</div>
<div>

### 📋 Requirements
- **Postgres**: Persistence with volumes.
- **Redis**: Use it for the caching layer.
- **API**: Must wait for DB and Redis to be healthy.

</div>
</div>

<div class="mt-12 text-center">
  <Admonition color="sky" title="Independent Work" icon="mdi-keyboard">
    Navigate to <code>exercise/</code> and follow the <code>README.md</code>. <br/>
    <b>You have 60 minutes!</b>
  </Admonition>
</div>
