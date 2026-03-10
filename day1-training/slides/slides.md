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
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 4:</b> Docker Configuration & Secrets</li>
      <li><mdi-check-circle-outline class="text-slate-500 inline mr-2"/> <b>Section 5:</b> Introduction to Kubernetes (K8s)</li>
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
layout: section
---

# The C.A.L.M.S. Framework
The Core Pillars of DevOps

---

# What is DevOps?

<div class="mt-12 space-y-6">
  <Admonition color="slate" title="The Overarching Framework" icon="mdi-shield-check" customTitle="text-lg">
    <p class="text-xs text-slate-200">
      DevOps is not just tools. It is a <b>Cultural and Professional movement</b> that stresses communication, collaboration, integration, and automation.
    </p>
  </Admonition>

  <Admonition color="blue" title="The C.A.L.M.S. Model" icon="mdi-pillar">
    <p class="text-xs text-slate-200">
      A conceptual framework for assessing an organization's ability to adopt DevOps processes.
    </p>
  </Admonition>
</div>

<div class="flex flex-col items-center justify-center mt-4">
  <img src="/calms.png" class="w-full max-w-sm rounded" />
  <p class="text-[10px] text-slate-400 mt-4 italic">The Infinite Feedback Loop</p>
</div>

---
layout: default
---

# 1. C is for Culture

DevOps starts with people, not products.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="People Over Tools" icon="mdi-account-group">
    <ul class="text-xs space-y-2 text-slate-200">
      <li><b>Shared Responsibility:</b> Dev and Ops own the service together.</li>
      <li><b>Psychological Safety:</b> No-blame post-mortems.</li>
      <li><b>Breaking Silos:</b> Direct communication instead of "ticket tossing".</li>
    </ul>
  </Admonition>
  
  <div class="flex items-center justify-center bg-slate-800/20 rounded p-4 border border-slate-700">
    <p class="text-sm italic text-center">"Culture eats strategy for breakfast."<br/>— Peter Drucker</p>
  </div>
</div>

---
layout: default
---

# 2. A is for Automation

Eliminate "Toil" to focus on value.

<div class="grid grid-cols-2 gap-8 mt-8">
  <div class="space-y-4">
    <Admonition color="blue" title="What to Automate?" icon="mdi-robot">
      <ul class="text-xs space-y-2 text-slate-200">
        <li><b>CI/CD Pipelines:</b> Test and deploy every commit.</li>
        <li><b>Infrastructure:</b> Treat servers as code (Terraform/Ansible).</li>
        <li><b>Testing:</b> Unit, Integration, and Security scans.</li>
      </ul>
    </Admonition>
  </div>

  <Admonition color="slate" title="The Goal" icon="mdi-target">
    <p class="text-xs text-slate-200">
      Automation creates <b>repeatability</b> and <b>predictability</b>. If a task must be done twice, automate it.
    </p>
  </Admonition>
</div>

---
layout: default
---

# 3. L is for Lean

Focus on efficiency and flow.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="green" title="Lean Principles" icon="mdi-leaf">
    <ul class="text-xs space-y-2 text-slate-200">
      <li><b>Small Batches:</b> Deploy 5 files, not 500. Easier to debug!</li>
      <li><b>Reduce Waste:</b> Remove manual approvals and wait times.</li>
      <li><b>Visualize Work:</b> Use Kanban boards to see bottlenecks.</li>
    </ul>
  </Admonition>

  <div class="flex items-center justify-center p-4">
     <mdi-speedometer class="text-6xl text-green-400 opacity-50" />
     <p class="ml-4 text-sm font-bold">Fast Flow = Fast Feedback</p>
  </div>
</div>

---
layout: default
---

# 4. M is for Measurement

You can't improve what you can't measure.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="rose" title="Key Data Points" icon="mdi-chart-line">
    <ul class="text-xs space-y-2 text-slate-200">
      <li><b>DORA Metrics:</b> Deployment Frequency, Lead Time for Changes.</li>
      <li><b>Stability:</b> Change Failure Rate, Time to Restore (MTTR).</li>
      <li><b>Quality:</b> Automated test coverage and bug counts.</li>
    </ul>
  </Admonition>

  <Admonition color="slate" title="Feedback Loops" icon="mdi-refresh">
    <p class="text-xs text-slate-200 leading-relaxed font-italic">
      Data provides the <b>truth</b> needed to decide what to automate or fix next.
    </p>
  </Admonition>
</div>

---
layout: default
---

# 5. S is for Sharing

The final pillar: Total Transparency.

<div class="grid grid-cols-2 gap-8 mt-8">
  <div class="space-y-4">
    <Admonition color="orange" title="Knowledge Transfer" icon="mdi-share-variant">
      <ul class="text-xs space-y-2 text-slate-200">
        <li><b>Open Documentation:</b> No "hidden knowledge".</li>
        <li><b>Pairing:</b> Dev and Ops working together.</li>
        <li><b>Open Source Mindset:</b> Share tools and scripts internally.</li>
      </ul>
    </Admonition>
  </div>

  <div class="flex items-center justify-center bg-orange-400/10 rounded p-6 border border-orange-400/30">
    <p class="text-sm font-bold text-center text-orange-200">Sharing transforms individual brilliance into organizational capability.</p>
  </div>
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

  <Admonition color="slate" title="Continuous Deployment" icon="mdi-rocket-launch">
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
  <Admonition color="slate" title="Incident Timeline" icon="mdi-fire" customTitle="text-lg">
    <div class="flex flex-col space-y-4 mt-6 text-sm font-mono">
      <div v-click class="flex items-center gap-4">
        <div class="w-20 font-bold text-slate-300">03:00 AM</div> 
        <div class="p-2 px-3 bg-slate-800/80 rounded border border-slate-700 w-full text-slate-200">App crashes in Prod</div>
      </div>
      <div v-click class="flex items-center gap-4">
        <div class="w-20 font-bold text-slate-300">03:15 AM</div> 
        <div class="p-2 px-3 bg-slate-800/80 rounded border border-slate-700 italic w-full text-slate-200">PagerDuty wakes up a tired engineer</div>
      </div>
      <div v-click class="flex items-center gap-4">
        <div class="w-20 font-bold text-slate-300">03:45 AM</div> 
        <div class="p-2 px-3 bg-slate-800/80 rounded border border-slate-700 w-full text-slate-200">Manually restarting everything...</div>
      </div>
      <div v-click class="flex items-center gap-4 pt-4 border-t border-slate-700/50">
        <div class="w-20 font-bold text-rose-400 text-lg uppercase">Result</div> 
        <div class="text-rose-100 font-bold italic">Loss of sleep, high stress, and business downtime.</div>
      </div>
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

<div class="mt-8">
  <Admonition color="slate" title="1. Namespaces" icon="mdi-eye-outline" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Isolation of resources (Process, Network, User)</p>
  </Admonition>
</div>

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

<div class="mt-8">
  <Admonition color="slate" title="Containers" icon="mdi-docker">
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
<Admonition color="slate" title="Answer" icon="mdi-check">
  Containers share the <b>Host OS Kernel</b>, while VMs include a full <b>Guest OS</b>.
</Admonition>
</v-click>

---
layout: fact
---

# 📺 LIVE DEMO 01
## Hello World
Goal: Sanity check the Docker installation and run our first container.

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

</div>
</div>

---
layout: fact
---

# 📺 LIVE DEMO 02
## Nginx Basic
Goal: Learn port mapping and background (-d) execution.

---
layout: default
---

<div class="mt-4 text-center">
  <p class="italic text-sm text-slate-400">*If you see "Hello from Docker!", it's working!*</p>
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
<Admonition color="slate" title="Answer" icon="mdi-check">
  <b>True!</b> Every layer is a read-only snapshot. Only the top "Container Layer" is writable.
</Admonition>
</v-click>

---
layout: fact
---

# 📺 LIVE DEMO 03
## Simple Dockerfile
Goal: Build a custom image from a Node.js source.

---
layout: fact
---

# 📺 LIVE DEMO 04
## Dockerfile Optimization
Goal: Compare "bad" vs "good" layer orders to see caching in action.

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
  <Admonition color="slate" title="COPY vs ADD" icon="mdi-file-move">
    <b>COPY:</b> Moves files from host to image. <br/> 
    <b>ADD:</b> Similar, but can extract tarballs and fetch URLs. (Use COPY unless you need extract).
  </Admonition>
  <Admonition color="slate" title="ENV vs ARG" icon="mdi-variable">
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
  <Admonition color="slate" title="The Magic Cleanup Command" icon="mdi-broom" customTitle="text-lg">
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

  <Admonition color="slate" title="2. host" icon="mdi-server-network">
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
<Admonition color="slate" title="Answer" icon="mdi-check">
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
<Admonition color="slate" title="Answer" icon="mdi-check">
  <b>CMD</b>. The new command replaces whatever was in CMD.
</Admonition>
</v-click>

---
layout: fact
---

# 📺 LIVE DEMO 05
## Multi-Stage Build
Goal: Use two stages to ship a tiny 30MB JAR image instead of 600MB SDK.

---

# Dockerfile Best Practices: Caching

Docker images are built strictly layer-by-layer. Efficiency comes from reusing these layers.

1. **Layer Sensitivity:**
   - Instructions like `RUN`, `COPY`, and `ADD` create layers.
   - If a layer changes, **all subsequent layers** must be rebuilt.
   - **Rule:** Put stable instructions at the top, and frequent changes (code) at the **BOTTOM**.

2. **The Dependency Cache Trick (Node.js Example):**
   ```dockerfile {all|2-3|5-7|all}
   # BAD: Re-downloads EVERYTHING if any file changes
   COPY . /app
   RUN npm install

   # GOOD: Only re-installs if package.json changes
   COPY package*.json /app/
   RUN npm install
   COPY . /app
   ```

---

# Dockerfile Best Practices: .dockerignore

A `.dockerignore` file is just as important as a `.gitignore` file.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### Why use it?
- **Speed:** Sends less data to the Docker daemon (smaller build context).
- **Security:** Prevents shipping sensitive files (e.g., `.env`, `keys.pem`).
- **Consistency:** Prevents Windows/Mac binaries (like `node_modules`) from overwriting Linux binaries inside the container.

</div>
<div>

### What to ignore?
```text
.git
node_modules
dist
*.log
.env
Dockerfile
.dockerignore
```

</div>
</div>
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

# 📺 LIVE DEMO 06
## Persistence
Goal: Use Bind Mounts for live-reloading an Nginx index page.

---
layout: fact
---

# 🧠 Quick Check!
## Which mount type is best for live-reloading code during development?

<v-click>
<Admonition color="slate" title="Answer" icon="mdi-check">
  <b>Bind Mounts</b> (mapping a host folder directly).
</Admonition>
</v-click>

---
layout: fact
---

# 📺 LIVE DEMO 07
## Networking
Goal: Inspect the difference between bridge and host network drivers.

---
layout: fact
---

# 🧠 Quick Check!
## What is the default networking driver in Docker?

<v-click>
<Admonition color="slate" title="Answer" icon="mdi-check">
  <b>Bridge Network</b>.
</Admonition>
</v-click>

---

# The 4 Types of Docker Mounts

Docker provides four distinct ways to manage state and persistence.

<div class="grid grid-cols-2 gap-6 mt-6">
  <div class="bg-slate-800/50 p-4 border border-blue-500/30 rounded-xl hover:bg-slate-800/80 transition-all duration-300">
    <div class="flex items-center gap-3 mb-2">
      <div class="p-2 bg-blue-500/10 rounded-lg"><mdi-database class="text-blue-400 text-xl" /></div>
      <h3 class="text-blue-200 font-bold">1. Named Volumes</h3>
    </div>
    <p class="text-[10px] text-slate-300 leading-relaxed mb-3">Managed entirely by Docker. Stored in a hidden system directory. Safest for production.</p>
    <div class="text-[9px] bg-blue-500/10 text-blue-300 px-2 py-1 rounded inline-block font-bold mt-auto">BEST FOR: Production Databases</div>
  </div>

  <div class="bg-slate-800/50 p-4 border border-emerald-500/30 rounded-xl hover:bg-slate-800/80 transition-all duration-300">
    <div class="flex items-center gap-3 mb-2">
      <div class="p-2 bg-emerald-500/10 rounded-lg"><mdi-folder-sync-outline class="text-emerald-400 text-xl" /></div>
      <h3 class="text-emerald-200 font-bold">2. Bind Mounts</h3>
    </div>
    <p class="text-[10px] text-slate-300 leading-relaxed mb-3">Maps a specific path from your host OS directly into the container. Very flexible.</p>
    <div class="text-[9px] bg-emerald-500/10 text-emerald-300 px-2 py-1 rounded inline-block font-bold mt-auto">BEST FOR: Local Development (Hot Reload)</div>
  </div>

  <div class="bg-slate-800/50 p-4 border border-slate-600/30 rounded-xl hover:bg-slate-800/80 transition-all duration-300">
    <div class="flex items-center gap-3 mb-2">
      <div class="p-2 bg-slate-500/10 rounded-lg"><mdi-ghost-outline class="text-slate-400 text-xl" /></div>
      <h3 class="text-slate-200 font-bold">3. Anonymous Volumes</h3>
    </div>
    <p class="text-[10px] text-slate-300 leading-relaxed mb-3">Similar to Named Volumes but with a random ID. Hard to track and easy to lose.</p>
    <div class="text-[9px] bg-slate-500/10 text-slate-400 px-2 py-1 rounded inline-block font-bold mt-auto">BEST FOR: Temporary data / "Throwaway"</div>
  </div>

  <div class="bg-slate-800/50 p-4 border border-amber-500/30 rounded-xl hover:bg-slate-800/80 transition-all duration-300">
    <div class="flex items-center gap-3 mb-2">
      <div class="p-2 bg-amber-500/10 rounded-lg"><mdi-memory class="text-amber-400 text-xl" /></div>
      <h3 class="text-amber-200 font-bold">4. tmpfs Mounts</h3>
    </div>
    <p class="text-[10px] text-slate-300 leading-relaxed mb-3">Stored in the host's memory (RAM), never written to disk. Blazing fast.</p>
    <div class="text-[9px] bg-amber-500/10 text-amber-300 px-2 py-1 rounded inline-block font-bold mt-auto">BEST FOR: High Security / Sensitive Secrets</div>
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
layout: fact
---

# 📺 LIVE DEMO 08
## Docker Compose Basic
Goal: Orchestrate a Todo API and a Postgres DB with automated network.

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
<Admonition color="slate" title="Answer" icon="mdi-check">
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
layout: fact
---

# 📺 LIVE DEMO 09
## Docker Compose Advanced
Goal: Add Healthcheck and Resource Limits to make our stack robust.

---
layout: section
---

<div class="flex flex-col items-center justify-center h-full">
  <div class="p-6 bg-slate-800/50 border-2 border-dashed border-slate-700 rounded-3xl mb-8">
    <mdi-worker class="text-8xl text-amber-400 opacity-80" />
  </div>
  <h1 class="text-6xl font-black tracking-tighter uppercase">Exercise Time</h1>
  <p class="text-2xl text-slate-400 font-medium mt-4">The Todo API: multi-service orchestration</p>
</div>

---

# Practice: Containerize the Todo API

<div class="grid grid-cols-2 gap-8 mt-4">
  <div class="space-y-6">
    <Admonition color="blue" title="1. Instructions" icon="mdi-book-open-variant" customTitle="text-lg font-bold">
      <ul class="space-y-3 mt-2 text-sm text-slate-200">
        <li><span class="font-bold text-blue-400 mr-2">Step 1:</span> Navigate to the <code>/starter</code> folder.</li>
        <li><span class="font-bold text-blue-400 mr-2">Step 2:</span> Solve <b>5 TODOs</b> in the <code>Dockerfile</code>.</li>
        <li><span class="font-bold text-blue-400 mr-2">Step 3:</span> Solve <b>11 TODOs</b> in <code>docker-compose.yml</code>.</li>
        <li><span class="font-bold text-blue-400 mr-2">Step 4:</span> Deployment: <code>docker compose up -d</code></li>
      </ul>
    </Admonition>
  </div>

  <div class="space-y-6">
    <Admonition color="emerald" title="2. Success Criteria" icon="mdi-trophy-variant" customTitle="text-lg font-bold">
      <ul class="space-y-4 mt-2 text-sm text-slate-200">
        <li class="flex items-center gap-3"><mdi-check-circle-outline class="text-emerald-400 text-xl" /> <span>Multi-stage build succeeds</span></li>
        <li class="flex items-center gap-3"><mdi-check-circle-outline class="text-emerald-400 text-xl" /> <span>Both App and DB are strictly healthy</span></li>
        <li class="flex items-center gap-3"><mdi-check-circle-outline class="text-emerald-400 text-xl" /> <span>API responses on <code>:8080</code></span></li>
        <li class="flex items-center gap-3"><mdi-check-circle-outline class="text-emerald-400 text-xl" /> <span>Network isolation is maintained</span></li>
      </ul>
    </Admonition>
  </div>
</div>

<div class="mt-10 p-4 bg-slate-800/40 border border-slate-700 rounded-xl text-center">
  <p class="text-sm font-medium text-slate-300">
    <mdi-information-outline class="inline mr-1 text-blue-400" /> Need help? Detailed step-by-step guidance is available in the <code>README.md</code> file!
  </p>
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

# Section 4: Configuration & Secrets
Managing environments and sensitive data in Docker Compose

---

# YAML "Gotchas" (Pro-Tip)

Docker Compose relies heavily on YAML formatting. **YAML is notoriously sensitive to spacing.**

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### The Golden Rules
1. **Spaces, never tabs!** Always use exactly 2 spaces for each indentation level.
2. **Watch your dashes:** A dash (`-`) means an item in a list (array). Without it, it's just a dictionary value.
3. **Strings:** Usually you don't need quotes, but watch out for colons or special characters—wrap those in quotes.

</div>
<div>

### Stop Debugging Spaces
Do not spend 30 minutes debugging a "missing dash".

<Admonition color="slate" title="IDE Lifesaver" icon="mdi-lifebuoy">
  <p class="text-[10px] text-slate-200">
    Install the <b>Docker extension for VS Code</b>. It provides live YAML linting, auto-completion, and will immediately highlight spacing errors before you ever run <code>docker compose up</code>.
  </p>
</Admonition>

</div>
</div>

---
layout: fact
---

# 📺 LIVE DEMO 10
## Docker Compose YAML Validation
Goal: Understand how Docker validates your YAML and find syntax errors.

---

# Environment Variables in Docker Compose

Separating configuration from code.
Do not hardcode environment variables like `DB_HOST` in your images!

<div class="space-y-6 mt-8">
  <Admonition color="blue" title="1. The environment block" icon="mdi-tune">
    <p class="text-sm">Define variables directly in the YAML. Good for non-sensitive data.</p>
    <div class="bg-gray-800 p-2 rounded mt-2 font-mono text-sm">
      environment:<br/>
      &nbsp;&nbsp;- APP_ENV=production<br/>
      &nbsp;&nbsp;- DB_HOST=db
    </div>
  </Admonition>

  <Admonition color="green" title="2. The env_file block" icon="mdi-file-document-outline">
    <p class="text-sm">Load variables from an external <code>.env</code> file. Do not commit this file to Git!</p>
    <div class="bg-gray-800 p-2 rounded mt-2 font-mono text-sm">
      env_file:<br/>
      &nbsp;&nbsp;- .env
    </div>
  </Admonition>
</div>

---
layout: fact
---

# 📺 LIVE DEMO 11
## Docker Compose Environment Variables
Goal: Pass configuration to containers using both environment blocks and .env files.

---

# Docker Secrets

For highly sensitive data: Passwords, API keys, TLS certificates.
Environment variables can be read by any script running in the container. **Secrets** are safer.

<div class="mt-8 space-y-6">
  <Admonition color="rose" title="How they work" icon="mdi-incognito">
    <p class="text-xs text-slate-200">
      Docker Secrets mount sensitive files directly into memory inside the container (usually at <code>/run/secrets/</code>). They never touch the container's hard drive and don't appear in <code>printenv</code>.
    </p>
  </Admonition>
</div>

```yaml {all|4-5|7-9|all}
services:
  db:
    image: postgres:16
    secrets:
      - db_password # Mounts the secret into /run/secrets/db_password

secrets:
  db_password:
    file: ./db_password.txt # The actual file on your host machine
```

---
layout: fact
---

# 📺 LIVE DEMO 12
## Docker Secrets
Goal: Mount a secure database password without exposing it in environment variables.

---
layout: section
---

# Section 5: Kubernetes (K8s) Fundamentals
An Introduction to Orchestration at Scale

---

# The "Why K8s" Bridge

Docker Compose is for **local development** and single-node environments. Kubernetes is for **production** and multi-node clusters.

<div class="mt-8 flex justify-center">
  <Admonition color="slate" title="The Shipping Analogy" icon="mdi-ship-wheel" customTitle="text-lg">
    <table class="w-full text-left text-xs text-slate-200 mt-2">
      <tbody>
        <tr class="border-b border-slate-700">
          <td class="p-2 w-12"><mdi-package-variant-closed class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold w-1/4">Docker</td>
          <td class="p-2">A single shipping container.</td>
        </tr>
        <tr class="border-b border-slate-700">
          <td class="p-2"><mdi-truck-cargo-container class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold text-slate-300">Docker Compose</td>
          <td class="p-2">A small truck carrying a few containers locally.</td>
        </tr>
        <tr>
          <td class="p-2"><mdi-crane class="text-xl text-blue-400" /></td>
          <td class="p-2 font-bold text-slate-300">Kubernetes</td>
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
layout: fact
---

# 🏆 Final Challenge: The Notes API
## Independent Practice: App + DB + Redis

---
layout: fact
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
  <Admonition color="slate" title="Independent Work" icon="mdi-keyboard">
    Navigate to <code>exercise/</code> and follow the <code>README.md</code>. <br/>
    <b>You have 60 minutes!</b>
  </Admonition>
</div>
