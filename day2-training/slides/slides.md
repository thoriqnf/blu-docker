---
theme: neversink
colorSchema: dark
highlighter: shiki
lineNumbers: true
fonts:
  sans: Outfit
  serif: Outfit
  mono: Fira Code
aspectRatio: '16/9'
info: |
  ## DevOps Day 2 Training
  Debugging Kubernetes & Advanced Deployment
drawings:
  persist: false
transition: slide-up
title: Day 2 - Debugging Kubernetes & Advanced Deployment
---

# DevOps Training: Day 2

## Debugging Kubernetes & Advanced Deployment

<div class="mt-10 flex justify-center gap-8">
  <img src="/blueprint_k8s_diag.png" class="h-40 rounded shadow-xl" />
  <img src="/blueprint_devops_cycle.png" class="h-40 rounded shadow-xl" />
</div>

---
layout: intro
---

# Agenda for Today

What we will cover in this session:

<div class="mt-8 mx-auto w-3/4">
  <Admonition color="slate" title="Today's Topics" icon="mdi-format-list-bulleted" custom="text-lg">
    <ul class="text-left space-y-2 list-none p-2 text-xs text-slate-200 font-medium">
      <li><mdi-kubernetes class="text-slate-400 inline mr-2"/> <b>Section 1:</b> Kubernetes Basics &amp; Project Setup</li>
      <li><mdi-google-analytics class="text-slate-400 inline mr-2"/> <b>Section 2:</b> Advanced Deployment Strategies</li>
      <li><mdi-lan class="text-slate-400 inline mr-2"/> <b>Section 3:</b> Networking, Architecture &amp; Workloads</li>
      <li><mdi-school class="text-slate-400 inline mr-2"/> <b>Section 4:</b> Deep Dive — Advanced Concepts</li>
      <li><mdi-bug-check class="text-slate-400 inline mr-2"/> <b>Section 5:</b> Debugging Workflow &amp; Practice</li>
      <li><mdi-sync class="text-slate-400 inline mr-2"/> <b>Section 6:</b> CI/CD, Helm &amp; GitOps</li>
    </ul>
    <p class="text-[10px] text-slate-400 italic mt-3 text-center">Central theme: <b class="text-slate-300">Every deployment decision is a risk management decision.</b></p>
  </Admonition>
</div>

---
layout: section
---

# What is Kubernetes?

The Conductor of the Container Orchestra

---

# What is Kubernetes?

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="1. The Problem" icon="mdi-alert-box">
    <p class="text-[10px] text-slate-200">
      Running 1 Docker container is easy. Running 1,000 Docker containers across 50 servers, managing their networks, secrets, storage, and auto-restarting them when they crash is <b>impossible manually.</b>
    </p>
  </Admonition>
  <Admonition color="slate" title="2. The Solution" icon="mdi-check-decagram">
    <p class="text-[10px] text-slate-200">
      Kubernetes (K8s) is an open-source container orchestration engine. You write a YAML file describing your "Desired State" (e.g., <i>"I want 5 copies of NGINX running"</i>), and K8s makes it happen automatically.
    </p>
  </Admonition>
</div>

<div class="mt-8 bg-slate-800 p-4 border border-slate-700 rounded text-center">
  <p class="text-xs font-bold text-slate-300 mb-2">Why K8s Exists (The Chaos Solver)</p>
  <div class="grid grid-cols-4 gap-4 text-[9px] text-slate-200">
    <div class="p-2 border border-slate-700 rounded"><b>Scheduling:</b> Where do containers run?</div>
    <div class="p-2 border border-slate-700 rounded"><b>Self-healing:</b> What if one crashes?</div>
    <div class="p-2 border border-slate-700 rounded"><b>Networking:</b> How do they talk?</div>
    <div class="p-2 border border-slate-700 rounded"><b>Scaling:</b> How do you scale up/down?</div>
  </div>
</div>

# ☕ The Orchestration Wars (2014-2017)

Why did Kubernetes win?

- **Docker Swarm:** Built by Docker. Very easy ("Just like Compose") but struggled with complex enterprise primitives.
- **Apache Mesos:** "The Datacenter OS". Powerhouse for Twitter/Airbnb, but proved too complex for average teams to operate.
- **Kubernetes (Borg's Child):** Won by providing the **perfect abstraction model** ("Desired State") and leveraging a massive open-source ecosystem. **The standard.**

---
layout: fact
---

# 🧠 Quick Check!
## Which component is responsible for ensuring the "Desired State" (e.g., 3 replicas) is always maintained?

<v-click>
<Admonition color="slate" title="Answer" icon="mdi-check">
  The <b>ReplicaSet</b> (managed by the <b>Deployment</b>).
</Admonition>
</v-click>

---

# Deep Dive: Kubernetes Object Anatomy

Every Kubernetes YAML file shares the exact same core DNA structure.

```yaml {all|1|2|3-4|5-8|all}
apiVersion: apps/v1     # Which K8s API schema are we using?
kind: Deployment        # What type of object are we creating?
metadata:               # Data that uniquely identifies the object
  name: my-backend-auth
spec:                   # The "Desired State". What do we want?
  replicas: 3
  template:             
    # (The Pod definition goes inside here)
```

<div class="mt-4">
  <Admonition color="slate" title="The Controller Loop" icon="mdi-refresh">
    <p class="text-xs text-slate-200">
      Kubernetes is constantly reading the <b>spec</b> (what you want) and comparing it to the <b>status</b> (reality). If they don't match, K8s takes action to fix reality!
    </p>
  </Admonition>
</div>

 

---

# Project Setup Workflow

You just finished writing your Node.js application. What are the exact steps to get it into Kubernetes?

<div class="mt-6 space-y-4">

1. **Write the code:** `app.js`, `package.json`
2. **Containerize:** Write the `Dockerfile`.
3. **Build:** `docker build -t mycompany/my-node-app:v1 .`
4. **Push:** `docker push mycompany/my-node-app:v1` (Send it to DockerHub/AWS ECR).
5. **Declare:** Write the `deployment.yaml` and `service.yaml`.
6. **Apply:** `kubectl apply -f deployment.yaml -f service.yaml`

</div>

**Congratulations.** Your app is now running, load-balanced, auto-healing, and accessible inside the cluster!

---

# What is Minikube?

Minikube is a tool that lets you run Kubernetes locally. It runs a single-node Kubernetes cluster inside a VM or container on your laptop.

<div class="grid grid-cols-2 gap-4 mt-8">
  <Admonition color="slate" title="The Goal" icon="mdi-bullseye-arrow">
    <ul class="text-[10px] space-y-1 text-slate-200">
      <li><b>Development:</b> Test your YAMLs before pushing to Prod.</li>
      <li><b>Learning:</b> Experiment without cloud costs.</li>
      <li><b>Reliability:</b> It's a "real" K8s cluster, not a simulator.</li>
    </ul>
  </Admonition>
  
  <Admonition color="slate" title="Installation" icon="mdi-download">
    <div class="bg-slate-900 p-2 rounded font-mono text-[9px] text-slate-300">
      brew install minikube<br/>
      minikube start
    </div>
  </Admonition>
</div>

---

# Demo 1: Minikube Setup

Let's verify our local cluster is healthy and ready for deployment.

<div class="mt-8 space-y-4">

**Instructor Demo:**
1. Start the cluster: `minikube start --driver=docker`
2. Check the status: `minikube status`
3. Verify `kubectl` can talk to it: `kubectl get nodes`

</div>

---

# Resource Management: Tuning your Cluster

Don't let Minikube starve your laptop or crash because it's too small.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="Standard Start Parameters" icon="mdi-tune">
    <ul class="text-xs space-y-4 text-slate-200 font-mono">
      <li><span class="text-blue-400">--cpus=2</span><br/><span class="text-[10px] text-slate-400 font-sans italic">Allocates 2 CPU cores.</span></li>
      <li><span class="text-blue-400">--memory=4096</span><br/><span class="text-[10px] text-slate-400 font-sans italic">Allocates 4GB of RAM.</span></li>
      <li><span class="text-blue-400">--disk-size=20g</span><br/><span class="text-[10px] text-slate-400 font-sans italic">Allocates 20GB of disk.</span></li>
    </ul>
  </Admonition>

  <Admonition color="slate" title="Caution" icon="mdi-alert">
    <p class="text-xs text-slate-200">
      If you are running many microservices (e.g. Istio or complex databases), 2GB of RAM will <b>OOMKill</b> your control plane. Always aim for 4GB+.
    </p>
  </Admonition>
</div>

---

# Minikube Addons: Supercharging K8s

Minikube is modular. You can enable features only when you need them.

<div class="grid grid-cols-2 gap-4 mt-8">
  <div class="space-y-2">
    <Admonition color="slate" title="1. ingress" icon="mdi-router-wireless">
      <p class="text-[10px] text-slate-200">Automatically installs NGINX Ingress Controller.</p>
    </Admonition>
    <Admonition color="slate" title="2. dashboard" icon="mdi-view-dashboard">
      <p class="text-[10px] text-slate-200">A beautiful web UI for managing your cluster.</p>
    </Admonition>
  </div>

  <Admonition color="slate" title="Useful Commands" icon="mdi-terminal-box">
    <ul class="text-[10px] space-y-2 font-mono text-blue-300">
      <li>minikube addons list</li>
      <li>minikube addons enable ingress</li>
      <li>minikube dashboard</li>
    </ul>
  </Admonition>
</div>

---

# Local Exposure: Tunneling and Access

How do you visit your app if it's trapped inside a Docker container or VM?

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="1. Type: LoadBalancer" icon="mdi-bridge">
    <p class="text-xs text-slate-200 leading-relaxed">
      In the cloud, this gives you a public IP. Locally, it stays <code>&lt;pending&gt;</code> forever...
    </p>
    <div class="mt-4 bg-slate-900 p-2 rounded text-xs font-mono text-slate-400">
      minikube tunnel
    </div>
    <p class="text-[10px] mt-2 italic">*This bridges your host network to the cluster.*</p>
  </Admonition>

  <Admonition color="slate" title="2. Type: NodePort" icon="mdi-lan-connect">
    <p class="text-xs text-slate-200 leading-relaxed">
      Automatically opens a browser tab pointing to the correct IP and mapped port.
    </p>
    <div class="mt-4 bg-slate-900 p-2 rounded text-xs font-mono text-slate-400">
      minikube service my-app
    </div>
  </Admonition>
</div>

---
layout: section
---

# Networking Deep Dive: How Containers Communicate

The mechanics that make deployment strategies possible

---

# The Kubernetes Network Model

Kubernetes imposes fundamental rules on networking:

<v-clicks>
<div class="grid grid-cols-3 gap-4 mt-8">
  <Admonition color="blue" title="1. Unique Pod IPs" icon="mdi-ip-network" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Every Pod gets its own unique IP address. No port mapping madness.</p>
  </Admonition>
  <Admonition color="slate" title="2. Shared Pod IP" icon="mdi-share-variant" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Containers within a Pod share the same IP. They talk via localhost.</p>
  </Admonition>
  <Admonition color="green" title="3. Flat Network" icon="mdi-transit-connection-variant" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Pods can communicate with all other Pods without NAT (via CNI).</p>
  </Admonition>
</div>
</v-clicks>

<div v-click class="mt-8">
  <Admonition color="slate" title="The CNI Plugin" icon="mdi-plugin">
    <p class="text-xs text-slate-200 leading-relaxed italic">
      Container Network Interface (CNI) like <b>Calico, Flannel, or Cilium</b> creates an overlay network extending across your cluster nodes.
    </p>
  </Admonition>
</div>

---

# Kubernetes Services (The Internal Connectors)

A Service provides a single, constant IP address and DNS name for a group of Pods. It acts as a basic load balancer that tracks which Pods are healthy and ready to receive traffic.

---

# Exposing a Service

There are three primary ways to expose a Service:

<div class="mt-8 space-y-4">
  <Admonition color="slate" title="1. ClusterIP (Default)" icon="mdi-door-lock">
    <p class="text-[10px] text-slate-200">Exposes the service on an <b>internal IP</b>. It’s only reachable from within the cluster. Perfect for databases or internal microservices.</p>
  </Admonition>
  <Admonition color="slate" title="2. NodePort" icon="mdi-lan-connect">
    <p class="text-[10px] text-slate-200">Opens a specific port (30000-32767) on <b>every Node</b> (server) in the cluster. You can access the service via <code>&lt;Node-IP&gt;:&lt;NodePort&gt;</code>.</p>
  </Admonition>
  <Admonition color="slate" title="3. LoadBalancer" icon="mdi-bridge">
    <p class="text-[10px] text-slate-200">Integrates with your **cloud provider** (AWS, GCP, Azure) to spin up an external load balancer. Gives you a single <b>public IP address</b>.</p>
  </Admonition>
</div>

---

# Mental Bridge: Ports from Compose to K8s

How port mapping translates from what we learned in Day 1:

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="Docker Compose" icon="mdi-docker">
    <div class="bg-slate-900 p-2 rounded text-xs font-mono">
      ports:<br/>
      &nbsp;&nbsp;- "8080:80"
    </div>
    <p class="text-[10px] mt-2 text-slate-300 italic">"Bind host's 8080 to container's 80."</p>
  </Admonition>

  <Admonition color="slate" title="Kubernetes Service" icon="mdi-kubernetes">
    <div class="bg-slate-900 p-2 rounded text-xs font-mono text-slate-300">
      ports:<br/>
      &nbsp;&nbsp;- port: 8080<br/>
      &nbsp;&nbsp;&nbsp;&nbsp;targetPort: 80
    </div>
    <p class="text-[10px] mt-2 text-slate-300 italic">"The targetPort is the right side of the colon."</p>
  </Admonition>
</div>

---

# Demo 2: Minikube Networking

How do we actually view a NodePort service on our laptops?

<div class="mt-8 space-y-4 bg-slate-800 p-4 rounded border border-slate-700 text-sm">

**Instructor Demo:**
1. Create a quick deployment: `kubectl create deployment web --image=nginx`
2. Expose it via NodePort: `kubectl expose deployment web --type=NodePort --port=80`
3. View it in the browser: `minikube service web`

</div>

---

# The limits of Service Type: LoadBalancer

If `LoadBalancer` exposes apps to the internet, why isn't it enough?

<div class="mt-8 space-y-4">

❌ **Cost:** If you have 20 microservices and you make them all Type: LoadBalancer, you pay AWS/GCP for 20 expensive cloud load balancers.

❌ **Management:** You have 20 different public IP addresses to map in your DNS records.

❌ **No routing logic:** A Service LoadBalancer operates at Layer 4 (TCP/UDP). It cannot route based on URLs (`/api/v1` vs `/web`).

</div>

<div class="mt-6 text-center text-xl font-bold bg-slate-800 p-2 rounded text-slate-200">
  Solution: We need a Layer 7 router. We need INGRESS.
</div>

---

# Core Objects: The Pod

The smallest deployable unit in Kubernetes.

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: my-app
spec:
  containers:
    - name: app
      image: nginx:latest
      ports:
        - containerPort: 80
```

</div>
<div class="space-y-4">

<Admonition color="slate" title="What it is" icon="mdi-podium">
<p class="text-[10px]">Wraps one or more containers that share network and storage. Containers in a Pod talk to each other via <code>localhost</code>.</p>
</Admonition>

<Admonition color="rose" title="🚨 Production Rule" icon="mdi-alert-octagon">
<p class="text-[10px] font-bold">Never create bare Pods in production. If they die, they aren't restarted automatically. <b>Always use a Deployment.</b></p>
</Admonition>

</div>
</div>

---

# Core Objects: The Deployment

The "Boss" that manages your Pods.

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

```yaml {all|5|8-11|all}
apiVersion: apps/v1
kind: Deployment
metadata:
  name: my-app
spec:
  replicas: 3
  selector:
    matchLabels:
      app: my-app
  template:
    metadata:
      labels:
        app: my-app
    spec:
      containers:
        - name: app
          image: nginx:1.25
```

</div>
<div class="space-y-4">

<Admonition color="slate" title="Key Features" icon="mdi-robot">
<ul class="text-[9px] space-y-1">
<li><b>Self-Healing:</b> Restarts pods if they crash.</li>
<li><b>Scaling:</b> Scale from 1 to 100 with one command.</li>
<li><b>Rollouts:</b> Zero-downtime updates and easy rollbacks.</li>
</ul>
</Admonition>

<p class="text-[10px] text-slate-400 italic mt-4 text-center">"Declarative management: You define the desired state, K8s makes it happen."</p>

</div>
</div>

---

# Core Objects: The Service

A stable network endpoint. Pods are ephemeral; Services are permanent.

<div class="mt-8">

| Type | Use Case |
|------|----------|
| **ClusterIP** | **Internal Only.** Stable IP for pods to talk to each other. |
| **NodePort** | **Testing.** Exposes the service on a static port on every Node's IP. |
| **LoadBalancer** | **Production.** Creates a Cloud Load Balancer (AWS/GCP/Azure). |
| **ExternalName** | **Legacy.** DNS alias to a service outside the cluster. |

</div>

<div class="mt-8 bg-slate-900 p-4 border border-slate-700 rounded text-center">
  <code class="text-xs">kubectl expose deployment my-app --type=LoadBalancer --port=80</code>
</div>

---

# ConfigMap & Secret

Decoupling configuration from your application code.

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

### ConfigMap (Plain Text)
```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: app-config
data:
  DB_HOST: "postgres.local"
  DEBUG: "true"
```

</div>
<div>

### Secret (Base64)
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: app-secret
type: Opaque
data:
  # 'supersecret' in base64
  PASSWORD: c3VwZXJzZWNyZXQ=
```

</div>
</div>

<p class="text-[10px] text-center mt-6 text-slate-400">
  <b>Reminder:</b> K8s Secrets are NOT encrypted by default. Use SealedSecrets or External Secrets in production.
</p>

---
layout: section
---

# Section 2: Advanced Deployment Strategies

Every deployment decision is a risk management decision.

---

# Why Strategy Matters

In Kubernetes, a "deployment" isn't just about getting new code running — it's about managing **risk across four dimensions**: availability, correctness, performance, and rollback. 

Choosing the wrong strategy doesn't just cause downtime; it can **corrupt state**, split user sessions, or make rollback impossible without data loss.

<div v-click class="mt-8">
  <Admonition color="slate" title="The Core Tension" icon="mdi-scale-balance">
    <p class="text-xs">Every strategy balances <b>Speed of Delivery</b> vs. <b>Blast Radius of Failure</b>.</p>
  </Admonition>
</div>

---

# The Internal Model: 3 Planes

Before choosing a strategy, understand these independent planes:

<div class="grid grid-cols-3 gap-4 mt-8">
  <Admonition color="slate" title="1. The Pod Plane" icon="mdi-podium">
    <p class="text-[10px] text-slate-200">Which version (v1 or v2) is actually running. v2 can exist with <b>zero</b> user impact if no traffic reaches it.</p>
  </Admonition>
  <Admonition color="slate" title="2. The Traffic Plane" icon="mdi-router-wireless">
    <p class="text-[10px] text-slate-200">Managed by Service selectors, Ingress rules, or Mesh policies. Decoupled from Pod existence.</p>
  </Admonition>
  <Admonition color="slate" title="3. The State Plane" icon="mdi-database">
    <p class="text-[10px] text-slate-200">DBs, Caches, APIs. <b>The danger zone</b>. v2 schema changes can break v1 code still running.</p>
  </Admonition>
</div>

---

# Rolling Updates: The Hidden Danger

Every deployment decision is a risk management decision. K8s default strategy feels safe but carries a subtle risk: **v1 and v2 serve traffic simultaneously**.

<div class="mt-4 bg-slate-800 p-4 rounded border-l-4 border-slate-500">
  <p class="text-xs font-bold text-slate-300 uppercase tracking-widest mb-2">🔥 War Story: The "FastData Corp" Incident</p>
  <p class="text-[10px] text-slate-300 leading-tight">
    <b>Scenario:</b> A team renamed the <code>full_name</code> database column to <code>user_display_name</code> and shipped the code in a single rolling update.<br/>
    <b>Result:</b> For 5 minutes, 50% of traffic hit v1 pods (expecting the old name) and crashed, while the other 50% hit v2 and worked. The partial failure made it nightmare to debug because "it worked for some users."
  </p>
</div>

  </Admonition>
</div>

| Parameter | Role | Benefit |
|-----------|------|---------|
| `maxSurge` | **Velocity** | Faster rollout, but uses more resources. |
| `maxUnavailable` | **Capacity** | Protects server load, but slows down the "danger window". |

---

# Blue/Green: The Atomic Switch?

Every deployment decision is a risk management decision. Run two identical environments, then flip the switch.

<div class="grid grid-cols-2 gap-8 mt-6">
  <div class="text-xs space-y-4 text-slate-300">
    <h3 class="font-bold text-slate-400">The Implementation</h3>
    <p>1. Deploy v2 (Green) with same capacity as v1 (Blue).<br/>2. Verify v2 is healthy.<br/>3. Update Service selector <code>version: v2</code>.</p>
    <p class="italic text-slate-400 mt-2"> traffic cutover is atomic, but in-flight connections on Blue must be drained.</p>
  </div>
  <Admonition color="slate" title="Forward-Only Migrations" icon="mdi-undo-variant">
    <p class="text-[10px] text-slate-200 leading-relaxed">If Green writes a DB record in v2 format, Blue (rollback target) might not be able to read it. Use <b>Forward-Only migrations</b> for true safety.</p>
  </Admonition>
</div>

---

# Canary: Deployment as Experiment

Every deployment decision is a risk management decision. Treat deployment as a controlled experiment to falsify the hypothesis: *"v2 is as reliable as v1."*

<div class="grid grid-cols-2 gap-8 mt-6 text-xs">
  <Admonition color="blue" title="Analysis over Time" icon="mdi-clock-check">
    <ul class="list-disc ml-4 space-y-1 text-slate-300">
      <li><b>Sample Rate:</b> 10% for 5 mins = weak signal.</li>
      <li><b>Observation:</b> 10% for 2 hours = captures background jobs & edge cases.</li>
    </ul>
  </Admonition>
  <Admonition color="green" title="Beyond 5xx Rates" icon="mdi-chart-bell-curve">
    <ul class="list-disc ml-4 space-y-1 text-slate-200">
      <li>System: Latency p99, Memory leaks.</li>
      <li>Business: Checkout rate, session length.</li>
    </ul>
  </Admonition>
</div>

---

# A/B Testing vs. Canary

Similar technical machinery, but **success criteria** are fundamentally different.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="Canary (System Safety)" icon="mdi-security">
    <p class="text-[10px] text-slate-300 italic">"Is it safe to replace v1?"</p>
    <p class="text-[10px] mt-2">Probabilistic split (each request has 10% chance).</p>
  </Admonition>
  <Admonition color="blue" title="A/B Testing (Product Value)" icon="mdi-test-tube">
    <p class="text-[10px] text-slate-300 italic">"Does variant B produce better outcomes?"</p>
    <p class="text-[10px] mt-2"><b>Sticky routing</b> (user A always sees variant A) to avoid session contamination.</p>
  </Admonition>
</div>

---

# Shadow Deployment

Mirror traffic to v2, but discard the response. Users only see v1's result.

<div class="mt-8 space-y-4">
  <Admonition color="slate" title="Zero User Risk" icon="mdi-shield-check">
    <p class="text-xs">Observe v2 latency, error rate, and complexity under real production load with <b>zero blast radius</b>.</p>
  </Admonition>
  <Admonition color="slate" title="The Side-Effect Trap" icon="mdi-email-alert">
    <p class="text-xs">Impractical for transactional services. If v2 sends an email or charges a credit card, mirroring it means <b>double-charging</b> the user.</p>
    <p class="text-[10px] mt-2 font-bold text-slate-400 italic">Solution: Use feature flags to disable side effects in shadow mode.</p>
  </Admonition>
</div>

---

# The State Problem: Expand/Contract

<div class="grid grid-cols-2 gap-4 mb-4">
  <div class="bg-slate-800 p-2 rounded border border-slate-600">
    <p class="text-[9px] font-bold text-slate-400 uppercase">❌ The "Instant Crash" (Common Mistake)</p>
    <p class="text-[8px] text-slate-300"><code>ALTER TABLE users RENAME COLUMN name TO full_name;</code><br/>One deploy ships this DB change + v2 code. <b>Result:</b> Existing v1 pods crash immediately as "name" disappears.</p>
  </div>
  <div class="bg-slate-800 p-2 rounded border border-slate-600">
    <p class="text-[9px] font-bold text-slate-400 uppercase">✅ The "Expand & Contract" (The Right Way)</p>
    <p class="text-[8px] text-slate-300">Phase 1: Add new column. Phase 2: Backfill & Read new. Phase 3: Drop old. <b>Result:</b> Zero downtime, safe rollback always possible.</p>
  </div>
</div>

<Admonition color="slate" title="⚠️ Production Gotcha" icon="mdi-alert-circle" customTitle="text-sm font-bold">
  <p class="text-[10px] text-slate-200">This is <b>always 3 separate deployments</b>. Most production outages during schema migrations happen because teams rush all 3 phases into one deploy — where v1 and v2 coexist, a single-step rename means <b>instant downtime</b>.</p>
</Admonition>

<div class="grid grid-cols-3 gap-2 mt-3">
  <div class="bg-slate-800 p-3 rounded border border-slate-700">
    <h3 class="text-slate-300 font-bold text-xs uppercase">1. Expand</h3>
    <p class="text-[9px] text-slate-200 font-bold mt-1 inline-block bg-slate-700 px-1 rounded">Release A — Ship &amp; Wait</p>
    <p class="text-[9px] text-slate-300 mt-2 leading-relaxed">Add the new column <code>full_name</code> but <b>keep</b> <code>first_name</code>. Update app to write to <b>both</b> but read from <b>old</b>. Both v1 and v2 remain happy.</p>
  </div>
  <div class="bg-slate-800 p-3 rounded border border-slate-700">
    <h3 class="text-slate-300 font-bold text-xs uppercase">2. Migrate</h3>
    <p class="text-[9px] text-slate-200 font-bold mt-1 inline-block bg-slate-700 px-1 rounded">Release B — Flip Read</p>
    <p class="text-[9px] text-slate-300 mt-2 leading-relaxed">Flip the app logic: read from <code>full_name</code>. Run a background job to backfill old records. v1 data is still intact for safe rollback.</p>
  </div>
  <div class="bg-slate-800 p-3 rounded border border-slate-700">
    <h3 class="text-slate-300 font-bold text-xs uppercase">3. Contract</h3>
    <p class="text-[9px] text-slate-200 font-bold mt-1 inline-block bg-slate-700 px-1 rounded">Release C — Clean Up</p>
    <p class="text-[9px] text-slate-300 mt-2 leading-relaxed">Only <i>after</i> 100% of traffic has migrated: drop the old <code>first_name</code> column. The rename is now safely complete.</p>
  </div>
</div>

</div>

---

# Progressive Delivery

The unifying theory where deployment is a continuous expansion of exposure surface.

<div class="mt-8 bg-slate-800 p-4 border border-slate-700 rounded-lg">
  <p class="text-center font-bold text-slate-400 mb-4">The Pipeline Model</p>
  <div class="flex items-center justify-between text-[10px] text-slate-300 uppercase tracking-widest">
    <span>Internal</span>
    <mdi-chevron-right/>
    <span>Synthetic Canary</span>
    <mdi-chevron-right/>
    <span>1% Traffic</span>
    <mdi-chevron-right/>
    <span>10% Traffic</span>
    <mdi-chevron-right/>
    <span>100%</span>
  </div>
</div>

<p class="text-sm italic mt-8 text-center text-slate-400">Reframes deployment from human judgment to scientific observation.</p>

---

# Choosing a Strategy

| Decision Framework | Choose... |
|--------------------|-----------|
| **Instant Rollback (Seconds)** | Blue/Green |
| **Silent/Slow Failure Mode** | Long Canary or Shadow |
| **High Resource Constraints** | Rolling Update |
| **Strict Session Persistence** | Sticky Canary or A/B |

**The Modern Meta:** Blue/Green for cutover + Progressive Canary for exposure + Expand/Contract for DB.

---
layout: section
---

# Deep Dive: Kubernetes Architecture

Let's look closely at what actually powers the cluster.

---

# Cluster Architecture Overview

A high-level view of how the Control Plane and Worker Nodes interact.

<div class="mt-4 flex justify-center">
  <img src="/k8s_cluster_architecture.png" class="h-[400px] rounded shadow border border-slate-700" alt="Kubernetes Cluster Architecture" />
</div>

<div class="mt-4 text-[10px] text-center text-slate-400 italic">
  Note the central role of the <b>API Server</b> and the <b>Kubelet</b> agent on every node.
</div>

---

# The Control Plane: etcd

The ultimate brain and memory of the cluster.

<div class="grid grid-cols-2 gap-8 mt-8 text-sm">
<div>

### What is it?
- A strongly consistent, distributed key-value store.
- **Fact:** It is the *only* component in the entire Kubernetes cluster that holds state. Everything else is stateless.
- If you lose your `etcd` database, you lose your entire cluster. Backup your etcd!

</div>
<div>

### How it works (Raft Consensus)
- `etcd` requires a majority (**Quorum**) to agree on any state change.
- Formula: `(N / 2) + 1` = Quorum.
- **Critical Production Rule:** Always use an **odd number** of master nodes (3, 5, or 7).
- **The Gotcha:** If you have 2 nodes and lose 1, you lose Quorum (need 2). The cluster enters a "read-only" panic mode. 3 nodes allows 1 failure; 5 allows 2.

</div>
</div>

---

# The Control Plane: kube-scheduler (1/2)

How does K8s decide where your Pod goes?

<div class="mt-8 space-y-4">

When you apply a Deployment, the scheduler does a 2-step process for every new Pod:

1. **Filtering (Predicates):**
   - *Does Node 1 have enough RAM?* (Yes)
   - *Does Node 2 have a GPU attached?* (No -> Filtered out)
   - *Is Node 3 tainted against my Pod?* (Yes -> Filtered out)

</div>

---

# The Control Plane: kube-scheduler (2/2)

<div class="mt-8 space-y-4">

2. **Scoring (Priorities):**
   - The scheduler ranks the surviving nodes.
   - *Node 1 has 90% CPU free.* (Score: 10/10)
   - *Node 4 has 10% CPU free.* (Score: 1/10)

**Result:** The Pod is bound to Node 1.

</div>

---

# The Worker Node: kube-proxy

The networking muscle on every physical server.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### What it does
- `kube-proxy` watches the API server for new Services and Endpoints.
- When a new Service is created, `kube-proxy` writes network routing rules on that specific host OS.

</div>
<div>

### Under the Hood (iptables vs IPVS)
- **iptables (Legacy/Default):** Kube-proxy writes hundreds of linear firewall rules. If a packet hits a host, it checks every rule in order `O(n)`. Slow at huge scale (10,000+ Services).
- **IPVS (Modern):** Uses Linux kernel hash tables `O(1)`. Near instant routing regardless of cluster size.

</div>
</div>

---

# The Worker Node: kubelet

The "Node Brain" that executes the Control Plane's orders.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### What it does
- Receives a **PodSpec** (YAML) and ensures the containers described are running and healthy.
- It is the agent that talks directly to the **Container Runtime** (Docker/containerd).
- Reports node and pod status back to the API Server.

</div>
<div>

### Key Mechanism: The Sync Loop
- Kubelet doesn't just start containers; it constantly **watches** them.
- If a container crashes, the Kubelet sync loop sees the discrepancy and restarts it locally.
- *Fun Fact:* It can run "Static Pods" from a local file without an API server!

</div>
</div>

---
layout: section
---

# Deep Dive: Advanced K8s Workloads

Beyond just the standard `Deployment`.

---

# StatefulSets vs. Deployments

When database instances need their identity to survive reboots.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

<h3 class="text-blue-500">Deployments (Stateless)</h3>

- Pods get random hashes: `api-7gxb9`
- Pods are completely interchangeable.
- If a pod dies, the new pod has a random new name and a clean (empty) state.
- **Use case:** Web servers, APIs, background workers.

</div>
<div>

<h3 class="text-orange-500">StatefulSets (Stateful)</h3>

- Pods get sticky, sequential names: `db-0`, `db-1`, `db-2`.
- Pods start strictly in order (0, then 1, then 2).
- When `db-1` dies, the replacement is *guaranteed* to be named `db-1` and attached to the exact same persistent hard drive `db-1` had previously.
- **Use case:** PostgreSQL Clusters, Kafka, Elasticsearch, Redis.

</div>
</div>

---

# Understanding Pod Status

What exactly is happening when a Pod isn't `Running`?

<div class="grid grid-cols-3 gap-4 mt-8">
  <Admonition color="slate" title="1. Pending" icon="mdi-clock-outline">
    <p class="text-[10px] text-slate-200">The Scheduler hasn't found a home for the Pod yet (lack of resources, taints) OR the image is still being pulled from the registry.</p>
  </Admonition>
  <Admonition color="slate" title="2. Running" icon="mdi-play-circle-outline">
    <p class="text-[10px] text-slate-200">The Pod has been bound to a Node and at least one container is either starting or running. <b>Note:</b> "Running" does NOT mean "Healthy" (check Probes!).</p>
  </Admonition>
  <Admonition color="slate" title="3. Terminating" icon="mdi-stop-circle-outline">
    <p class="text-[10px] text-slate-200">The Pod is being gracefully shut down. It allows <code>preStop</code> hooks and SIGTERM signals to finish before the container is killed.</p>
  </Admonition>
</div>

---

# DaemonSets and Jobs

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

<h3 class="text-green-500">DaemonSets</h3>

- **Goal:** Ensure exactly *one* copy of a Pod runs on *every single physical Node* in the cluster.
- If you add a new Node to the cluster, a DaemonSet Pod is instantly scheduled there.
- **Use case:** 
  - Log collectors (FluentBit / DataDog)
  - Security Scanners (Trivy)
  - Networking agents (Calico CNI)

</div>
<div>

<h3 class="text-purple-500">Jobs & CronJobs</h3>

- **Deployments** run forever. If a Deployment pod exits with code 0 (success), K8s thinks it crashed and restarts it!
- **Jobs** are designed to run a task to completion one time, and then peacefully terminate.
- **CronJobs** run a Job on a specific schedule (e.g. `0 0 * * *` = Midnight daily).
- **Use case:** Database backups, nightly batch processing scripts.

</div>
</div>

---

# Deep Dive: Advanced Pod Features

Pods aren't just for running your simple web server.

<div class="grid grid-cols-2 gap-8 mt-8">
  <Admonition color="slate" title="1. initContainers" icon="mdi-clock-start">
    <p class="text-[10px] text-slate-200">Containers that run strictly <b>before</b> the main app starts.</p>
    <ul class="text-[9px] mt-2 space-y-1 text-slate-300">
      <li>Must exit with code 0.</li>
      <li><b>Use case:</b> Waiting for a database to be ready before booting the API.</li>
    </ul>
  </Admonition>

  <Admonition color="slate" title="2. Ephemeral Containers" icon="mdi-bug-play">
    <p class="text-[10px] text-slate-200">The ultimate debugging tool for "distroless" images.</p>
    <ul class="text-[9px] mt-2 space-y-1 text-slate-300">
      <li>Attach an Alpine container to a running Pod sideway.</li>
      <li>Allows debugging images that don't have a shell installed!</li>
    </ul>
  </Admonition>
</div>

---

# Guardrails: Limits, Requests & Quotas

<div class="text-center my-6">
  <p class="text-xl font-bold bg-slate-800 p-4 border-y border-slate-700 italic">
    "Assume every developer will accidentally write a memory leak."
  </p>
</div>

<div class="mt-6 space-y-3">
  <Admonition color="slate" title="1. Requests & Limits" icon="mdi-scale-balance" customTitle="text-sm">
    <p class="text-[10px] text-slate-200"><b>Requests:</b> Minimum resources guaranteed (used for scheduling). <b>Limits:</b> Absolute maximum allowed before the app is <b>OOMKilled</b>.</p>
  </Admonition>
  <Admonition color="slate" title="2. ResourceQuotas" icon="mdi-chart-areaspline" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Namespace-level limits. "The Dev Team cannot use more than 10 CPUs total."</p>
  </Admonition>
  <Admonition color="slate" title="3. LimitRanges" icon="mdi-form-select" customTitle="text-sm">
    <p class="text-[10px] text-slate-200">Auto-injecting default limits if a developer forgets to define them.</p>
  </Admonition>
</div>

---
layout: section
---

# Section 3: Networking, Architecture & Workloads

Supporting context for our deployment strategies

---

# Ingress (The Smart Gateway)

While a `LoadBalancer` service is great, it’s expensive and inefficient to spin up a new cloud load balancer for every single microservice you have. **Ingress solves this.**

<div class="mt-4">
  <Admonition color="slate" title="What is it?" icon="mdi-router">
    <p class="text-[10px] text-slate-200">
      An API object that manages external access to services, typically via HTTP and HTTPS. It sits "in front" of your services and acts as a <b>smart router.</b>
    </p>
  </Admonition>

<div class="grid grid-cols-2 gap-4 mt-6">
  <ul class="text-[10px] space-y-2 text-slate-200">
    <li><mdi-web class="text-blue-400 inline mr-2"/> <b>Host-based Routing:</b> <code>api.example.com</code> vs <code>web.example.com</code>.</li>
    <li><mdi-folder-network class="text-green-400 inline mr-2"/> <b>Path-based Routing:</b> <code>/v1</code> vs <code>/v2</code>.</li>
  </ul>
  <ul class="text-[10px] space-y-2 text-slate-200">
    <li><mdi-shield-lock class="text-rose-400 inline mr-2"/> <b>SSL/TLS Termination:</b> Handle certs in one place.</li>
    <li><mdi-cash-multiple class="text-amber-400 inline mr-2"/> <b>Efficiency:</b> 1 Load Balancer for dozens of services.</li>
  </ul>
</div>

---

# Service vs. Ingress: A Summary

<div class="mt-10">

| Feature | Service (LoadBalancer) | Ingress |
|---------|------------------------|---------|
| **Layer** | L4 (TCP/UDP) | L7 (HTTP/HTTPS) |
| **Cost** | 1 LB per Service (Costly) | 1 LB for Many Services |
| **Routing** | Simple (IP/Port) | Complex (Host, Path, Headers) |
| **Best For** | Non-web traffic | Microservices & Web Apps |

</div>

---

# How it works together

<div class="grid grid-cols-2 gap-8 mt-4">
<div class="text-sm">

### The Traffic Flow:
1. **Internet** → User makes a request.
2. **Load Balancer** → Cloud provider entry point.
3. **Ingress Controller** → The "Engine" (e.g. NGINX).
4. **Ingress Rule** → The "Logic" (your YAML).
5. **Service** → Internal stable endpoint.
6. **Pod** → Your application container.

</div>
<div>
  <img src="/k8s_ingress_flow.png" class="w-full rounded shadow" alt="K8s Ingress Architecture" />
</div>
</div>

<div class="mt-8 text-[11px] bg-slate-800 p-3 rounded border border-slate-700 italic text-slate-300">
  <b>Note:</b> The Ingress <b>Resource</b> is just the rules; the <b>Controller</b> (NGINX, Traefik, or Istio) is the engine that executes them.
</div>

---

# Demo 3: Ingress Routing

Let's route traffic based on a hostname.

<div class="mt-8 space-y-4 bg-slate-800 p-4 rounded border border-slate-700 text-sm">

**Instructor Demo:**
1. **Enable the addon:** `minikube addons enable ingress`
2. **Apply the Ingress:** Route `api.devops.local` to our `web` service.
3. **Local DNS hack:** Add `127.0.0.1 api.devops.local` to `/etc/hosts` (or use `minikube tunnel`).
4. **Test:** Open browser to `http://api.devops.local`.

</div>

---

# Host-based vs. Path-based Routing

```yaml {all|7-9|15-18|all}
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: main-ingress
  annotations:
    # This magically tells cert-manager to provision SSL
    cert-manager.io/cluster-issuer: "letsencrypt-prod"
spec:
  tls:
    - hosts:
        - api.devops.local
      secretName: api-tls-cert  # The SSL cert is saved here
  rules:
    # 1. Host-based: Route based on domain name
    - host: api.devops.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service: { name: api-service, port: { number: 8080 } }
```

---

# TLS & Cert-Manager

In 2024, everything must be HTTPS. Kubernetes makes this entirely automated.

<div class="grid grid-cols-2 gap-x-8 gap-y-4 mt-4 text-xs">

<div>

1. **Install:** Deploy **Cert-Manager** add-on into your cluster.
2. **Configure:** It watches Ingress YAML files for `tls` blocks.
3. **Connect:** When it sees a domain, it speaks to **Let's Encrypt**.
4. **Prove:** It performs ACME DNS or HTTP-01 ownership challenges.

</div>

<div>

5. **Issue:** In 10 seconds, it downloads a free SSL certificate.
6. **Store:** It saves that certificate as a K8s `Secret`.
7. **Deploy:** NGINX detects the Secret and enables HTTPS traffic.
8. **Renewal:** It automatically renews 30 days before expiration.

</div>

</div>

<div class="mt-8 text-center text-sm italic text-slate-400">
  "Zero human intervention required."
</div>

---

# The Future: The Gateway API

Ingress is great, but it has flaws. The K8s project is officially replacing `Ingress` with the **Gateway API**.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

<h3 class="text-slate-400">The Problem with Ingress</h3>

- Built specifically for HTTP routing only. TCP/UDP routing is a hack.
- Everything is crammed into one massive YAML file.
- Developers and Admins fight over who gets to edit the singular Ingress YAML.

</div>
<div>

<h3 class="text-slate-400">The Gateway API Solution</h3>

- **Role-Oriented:** Splits routing into distinct roles.
  - *Cluster Admin* deploys a `GatewayClass`.
  - *Platform Eng* deploys a `Gateway` (The actual load balancer).
  - *Developers* deploy `HTTPRoute` bindings.
- Fully supports modern routing: gRPC, headers, weighted traffic splitting (A/B testing out of the box).

</div>

---

# Auto-Scaling Kubernetes

Kubernetes can automatically scale your infrastructure dynamically based on real-time traffic. There are 3 distinct auto-scalers.

<div class="grid grid-cols-3 gap-4 mt-6">
<div class="bg-slate-800 p-4 border border-slate-700 rounded text-sm">

<h3 class="text-slate-300 font-bold mb-2">1. HPA (Horizontal Pod Autoscaler)</h3>
<p class="text-xs text-slate-300">Scales **OUT**. Watches CPU/Memory. If CPU > 80%, it increases replicas from 3 to 10.</p>
```yaml
minReplicas: 3
maxReplicas: 10
targetCPUUtilizationPercentage: 80
```
</div>
<div class="bg-slate-800 p-4 border border-slate-700 rounded text-sm">

<h3 class="text-slate-300 font-bold mb-2">2. VPA (Vertical Pod Autoscaler)</h3>
<p class="text-xs text-slate-300">Scales **UP**. It observes your app. If your JVM starts OOMCrashing, the VPA automatically restarts the Pod with a larger Memory Limit (e.g., 1024Mi instead of 512Mi).</p>
</div>
<div class="bg-slate-800 p-4 border border-slate-700 rounded text-sm">

<h3 class="text-slate-300 font-bold mb-2">3. CA (Cluster Autoscaler)</h3>
<p class="text-xs text-slate-300">Talks to the Cloud API (AWS/GCP). If the HPA creates 50 new Pods and your physical servers are full, the CA boots up 3 brand new physical EC2 instances to hold them.</p>
</div>
</div>

---

# Configuration Management

Do not hardcode configuration into your Docker Images!

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### ConfigMaps
- Unencrypted key-value pairs (`DEBUG_MODE=true`).
- Injected via **Environment Variables** (best for simple keys).
- Injected via **Mounted Volume Files** (best for complex `nginx.conf`).

### Secrets

<Admonition color="slate" title="🚨 CRITICAL: Secrets Are NOT Encrypted" icon="mdi-shield-alert" customTitle="text-sm font-bold">
  <p class="text-[10px] text-slate-200 uppercase tracking-wider font-bold">Secrets are <b>Base64 encoded — NOT encrypted.</b> Anyone with <code>kubectl get secret</code> can read them.</p>
  <div class="mt-2 bg-slate-950 p-2 rounded font-mono text-[9px] text-slate-300">
    # Anyone can decode your database password in one second:<br/>
    $ kubectl get secret db-pass -o jsonpath='{.data.password}' | base64 -d<br/>
    <span class="text-slate-400 font-bold">→ my-super-secret-password</span>
  </div>
  <p class="text-[9px] text-slate-400 font-bold mt-2">→ Avoid "Security by Obscurity". Use SealedSecrets or Vault.</p>
</Admonition>

- **Production Rule:** Never commit raw Secrets to Git. You <b>must</b> use encrypted storage (see next slide: SealedSecrets).

</div>
<div>

```yaml {all|3-5|7-9|all}
# Injecting into a Pod via envFrom
containers:
  - name: my-app
    image: my-app:v1
    envFrom:
      # Inject 50 keys at once!
      - configMapRef:
          name: app-config
      - secretRef:
          name: db-passwords
```

</div>
</div>

---

# GitOps Security: Storing Secrets

If everything in K8s is declarative YAML stored in Git, **where do you put the Database Password?** You cannot push plain text Secrets to GitHub!

<div class="mt-8 space-y-6">

<h3 class="text-slate-400 font-bold">Approach 1: SealedSecrets (Bitnami)</h3>
<p class="text-sm">You encrypt the secret *offline* using a public key provided by the cluster. You push the encrypted `SealedSecret.yaml` to GitHub. It is completely safe. Only the cluster has the private key to decrypt it into a normal `Secret` once it is pulled inside.</p>

<h3 class="text-slate-400 font-bold">Approach 2: External Secrets Operator</h3>
<p class="text-sm">You never store passwords in Git at all. You store them in AWS Secrets Manager or HashiCorp Vault. K8s runs an operator that constantly reaches out to AWS, downloads the real password, and injects it directly into K8s memory as a `Secret`.</p>

</div>

---

---
layout: section
---

# Section 4: Deep Dive — Advanced Concepts

Enabling Production-Grade Reliability

---

# Pod Lifecycle & Phases

Understanding exactly what happens when a Pod is created or terminated is critical for debugging.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### The Phases
| Phase | Meaning |
|-------|---------|
| **Pending** | Scheduled but waiting (pulling image, resource wait) |
| **Running** | At least one container is running |
| **Succeeded** | All containers exited with code 0 |
| **Failed** | At least one container exited non-zero |

</div>
<div>

### Container States
- **Waiting:** Pulling image or waiting on a dependency.
- **Running:** Executing.
- **Terminated:** Finished or crashed.

<Admonition color="blue" title="Init Containers" icon="mdi-clock-start">
<p class="text-[9px]">Run strictly <b>before</b> main containers. Perfect for DB migrations or waiting for dependencies.</p>
</Admonition>

</div>
</div>

---

# Probes: The Reliability Engine

Get these wrong, and you'll have bad deploys or phantom traffic.

<div class="grid grid-cols-3 gap-4 mt-6">
  <Admonition color="slate" title="1. Liveness" icon="mdi-heart-pulse">
    <p class="text-[9px]">Is the app alive? If it fails, K8s <b>restarts</b> the container. Used for fixing deadlocks.</p>
  </Admonition>
  <Admonition color="slate" title="2. Readiness" icon="mdi-traffic-light">
    <p class="text-[9px]">Is it ready for traffic? If it fails, the Pod is <b>removed</b> from the Service endpoints.</p>
  </Admonition>
  <Admonition color="slate" title="3. Startup" icon="mdi-power-cycle">
    <p class="text-[9px]">Is the app done starting? Disables other probes until success. Great for slow Java boots.</p>
  </Admonition>
</div>

<div class="mt-6 bg-slate-800 p-3 rounded border border-slate-700">
<p class="text-[10px] uppercase font-bold text-slate-400 mb-1">Common Pitfall</p>
<p class="text-[9px] text-slate-300">Using the same endpoint for Liveness and Readiness. If your DB is down, Readiness should fail (stop traffic), but Liveness should pass (don't restart!).</p>
</div>

---

# Resource Management (Advanced)

Without limits, one bad actor pod can starve an entire physical node.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### Requests (The Floor)
- Minimum resources guaranteed.
- **Used for SCHEDULING decisions.**
- If you request 2GB, the scheduler ensures the node has 2GB free.

</div>
<div>

### Limits (The Ceiling)
- Absolute maximum allowed.
- **Memory:** If exceeded, Pod is **OOMKilled**.
- **CPU:** If exceeded, Pod is **Throttled** (slows down).

</div>
</div>

<div class="mt-6">
  <Admonition color="slate" title="QoS Classes" icon="mdi-medal">
    <p class="text-[10px] text-slate-200">
      <b>Guaranteed:</b> requests == limits. Highest priority.<br/>
      <b>Burstable:</b> requests < limits. Middle priority.<br/>
      <b>BestEffort:</b> No requests or limits. First to be killed during node pressure.
    </p>
  </Admonition>
</div>

---

# Scheduling, Affinity & Taints

Precision control over where your workloads land.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### Affinity / Anti-Affinity
- **Node Affinity:** "Place these pods on nodes with SSDs."
- **Pod Anti-Affinity:** "Don't put two copies of the API on the same physical node" (High Availability).

</div>
<div>

### Taints & Tolerations
- **Taints:** Repel pods from nodes (e.g., reserve a node for GPUs).
- **Tolerations:** Allow specific pods to "tolerate" the taint and land there anyway.

</div>
</div>

<div class="mt-8 text-center text-xs italic text-slate-400">
  "NodeSelector is a toddler's toy; Affinity is a precision instrument."
</div>

---

# RBAC: Who can do what?

Role-Based Access Control is the security backbone.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### The Actors
- **Users:** Internal people or external systems.
- **ServiceAccounts:** Identity for **Pods** (e.g., ArgoCD).

</div>
<div>

### The Permissions
- **Role / ClusterRole:** WHAT can be done (get, list, watch, delete).
- **Binding:** WHO gets assigned to which Role.

</div>
</div>

<Admonition color="slate" title="Principle of Least Privilege" icon="mdi-shield-check">
<p class="text-[10px]">Never use the <code>cluster-admin</code> role for your applications. Create specific ServiceAccounts with only the <code>get</code> and <code>list</code> verbs they need.</p>
</Admonition>

---

# Networking Deep Dive

How Services and Pods communicate at scale.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### DNS in Kubernetes
Every Service gets a DNS entry automatically:
`<service>.<ns>.svc.cluster.local`

- **Shorthand:** Within the same namespace, just use `my-service:8080`.
- **CoreDNS:** The internal component that handles these lookups.

</div>
<div>

### Network Policies
🔒 **By default, all Pods can talk to all Pods.**
- Use `NetworkPolicy` to restrict traffic.
- Example: "Only the Frontend Pod can talk to the API Pod."
- **mTLS:** Provided by Service Meshes like Istio or Linkerd.

</div>
</div>

---

# Storage Deep Dive

Persistence is hard. K8s makes it reproducible.

<div class="grid grid-cols-2 gap-8 mt-4 text-xs">
<div>

### PV & PVC
- **PersistentVolume (PV):** The actual hard drive (provisioned by Admin).
- **Claim (PVC):** The developer's request for storage.
- **StorageClass:** Defines *how* storage is created (SSD, HDD, Cloud Disk).

</div>
<div>

### Access Modes
- **RWO:** One node reads/writes (Standard).
- **ROX:** Many nodes read (Shared cache).
- **RWX:** Many nodes read/write (NFS, EFS).

</div>
</div>

<Admonition color="slate" title="StatefulSets & Storage" icon="mdi-database">
<p class="text-[9px]">StatefulSet <code>volumeClaimTemplates</code> ensure that <code>db-0</code> always gets the exact same disk back if it restarts, even on a different physical server.</p>
</Admonition>

---

# Section 5: Debugging Kubernetes

The systematic troubleshooting workflow

---

# The Golden Troubleshooting Workflow

When an app is down, follow the traffic from the inside out:

<div class="grid grid-cols-2 gap-4 mt-4">
  <Admonition color="slate" title="1. Pod: The Heartbeat" icon="mdi-podium">
    <p class="text-[9px] text-slate-200 leading-tight"><code>kubectl get pods</code>. If status is NOT <code>Running</code>, run <code>describe pod</code> immediately. Check <b>Events</b> at the bottom—it's the cluster's diary of what went wrong.</p>
  </Admonition>
  <Admonition color="slate" title="2. Logs: The Evidence" icon="mdi-text-box-search">
    <p class="text-[9px] text-slate-200 leading-tight"><code>kubectl logs &lt;name&gt;</code>. If the Pod is crash-looping, use <code>--previous</code> to see why the <i>last</i> instance died (e.g., DB Connection Refused).</p>
  </Admonition>
  <Admonition color="slate" title="3. Service: The Target" icon="mdi-target-variant">
    <p class="text-[9px] text-slate-200 leading-tight"><code>kubectl describe svc</code>. Look at <b>Endpoints</b>. If it's <code>&lt;none&gt;</code>, your Service <b>Selector</b> doesn't match your Pod <b>Labels</b>. Traffic has nowhere to go.</p>
  </Admonition>
  <Admonition color="slate" title="4. Ingress: The Entry" icon="mdi-routes">
    <p class="text-[9px] text-slate-200 leading-tight">Check <code>describe ingress</code>. Common pitfalls: host typo (<code>api.devops.locl</code>) or wrong path prefix (<code>/api</code> vs <code>/api/</code>).</p>
  </Admonition>
  <Admonition color="slate" title="5. The Ghost: 502 Bad Gateway" icon="mdi-ghost">
    <p class="text-[9px] text-slate-200 leading-tight">The Pod is <code>Running</code>, but users get 502s. <b>Diagnosis:</b> Is the app listening on <code>0.0.0.0</code> (all interfaces) or just <code>127.0.0.1</code>? Is the <code>targetPort</code> correct?</p>
  </Admonition>
</div>

---

# The Golden Rule of Debugging

<div class="mt-8 bg-slate-900 p-8 rounded border border-slate-700 text-center">
  <p class="text-2xl font-bold text-slate-300 italic italic">"Is it a Connection Refused (App Level) or a Timeout (Network Level)?"</p>
  
  <div class="grid grid-cols-3 gap-4 mt-12 text-left">
    <div class="space-y-2">
      <p class="text-blue-400 font-bold uppercase text-xs">Label Mismatch</p>
      <p class="text-[10px] text-slate-400">Service Endpoints will be empty. Traffic has nowhere to go.</p>
    </div>
    <div class="space-y-2">
      <p class="text-orange-400 font-bold uppercase text-xs">Port Mismatch</p>
      <p class="text-[10px] text-slate-400">TargetPort doesn't match the app's listening port.</p>
    </div>
    <div class="space-y-2">
      <p class="text-slate-300 font-bold uppercase text-xs">Namespacing</p>
      <p class="text-[10px] text-slate-400">Trying to reach a Service in another namespace without the FQDN.</p>
    </div>
  </div>
</div>

---

# Diagnosing: What Does `describe pod` Actually Tell You?

<div class="grid grid-cols-2 gap-4 mt-4">
  <div>
    <p class="text-green-400 font-bold text-[9px] uppercase mb-1">✅ Healthy Pod</p>
    <div class="bg-slate-900 p-2 rounded font-mono text-[8px] text-green-300 leading-relaxed">
      Status: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Running<br/>
      Conditions:<br/>
      &nbsp;&nbsp;Ready: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;True<br/>
      &nbsp;&nbsp;ContainersReady: True<br/>
      Restart Count: 0<br/>
      <br/>
      Events:<br/>
      &nbsp;&nbsp;Normal Pulled &nbsp;Successfully pulled image<br/>
      &nbsp;&nbsp;Normal Started Container started successfully
    </div>
  </div>
  <div>
    <p class="text-rose-400 font-bold text-[9px] uppercase mb-1">❌ Unhealthy Pod (CrashLoopBackOff)</p>
    <div class="bg-slate-900 p-2 rounded font-mono text-[8px] text-rose-300 leading-relaxed">
      Status: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;CrashLoopBackOff<br/>
      Conditions:<br/>
      &nbsp;&nbsp;Ready: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;False<br/>
      &nbsp;&nbsp;ContainersReady: False<br/>
      Restart Count: <span class="text-rose-400 font-bold">8</span><br/>
      <br/>
      Events:<br/>
      &nbsp;&nbsp;Warning BackOff &nbsp;Back-off restarting failed container<br/>
      &nbsp;&nbsp;Warning Failed &nbsp;<span class="text-rose-400">Error: failed to create task: DB_URL not found</span>
    </div>
  </div>
</div>

<div class="grid grid-cols-3 gap-2 mt-4">
  <Admonition color="blue" title="Label Mismatch Symptom" icon="mdi-tag-off" customTitle="text-xs">
    <p class="text-[9px]"><code>kubectl describe svc</code> → Endpoints: <b class="text-rose-400">&lt;none&gt;</b>. Fix: align <code>selector</code> in Service with <code>labels</code> on Pod.</p>
  </Admonition>
  <Admonition color="orange" title="Port Mismatch Symptom" icon="mdi-connection" customTitle="text-xs">
    <p class="text-[9px]">Logs show <b class="text-rose-400">connection refused</b>. Fix: <code>targetPort</code> in Service must match the port your app <i>actually</i> listens on.</p>
  </Admonition>
  <Admonition color="slate" title="Namespace DNS Issue" icon="mdi-map-marker-off" customTitle="text-xs">
    <p class="text-[9px]">Logs show <b class="text-rose-400">NXDOMAIN</b>. Fix: cross-namespace calls need FQDN: <code>svc.namespace.svc.cluster.local</code></p>
  </Admonition>
</div>

---


# Common Error 1: CrashLoopBackOff

<div class="grid grid-cols-2 gap-8 mt-6">
  <Admonition color="slate" title="Symptoms" icon="mdi-history">
    <p class="text-[10px] text-slate-200">The pod starts, crashes immediately, and Kubernetes keeps trying to restart it infinitely.</p>
  </Admonition>
  
  <Admonition color="slate" title="Root Causes" icon="mdi-alert-circle">
    <ul class="text-[9px] space-y-1 text-slate-200">
      <li>Missing environment variables (DB_URL).</li>
      <li>Application fatal exceptions.</li>
      <li>Resource limits exceeded (OOMKilled).</li>
    </ul>
  </Admonition>
</div>

<div class="mt-6">
  <Admonition color="slate" title="How to debug?" icon="mdi-bug-check">
    <code class="text-[10px]">kubectl logs &lt;pod-name&gt; --previous</code>
    <p class="text-[10px] text-slate-400 mt-2 italic">Check the logs of the instance that just died!</p>
  </Admonition>
</div>

---

# Common Error 2: ImagePullBackOff

<div class="grid grid-cols-2 gap-8 mt-6">
  <Admonition color="slate" title="Symptoms" icon="mdi-image-off">
    <p class="text-[10px] text-slate-200">The pod stays in <code>ImagePullBackOff</code> or <code>ErrImagePull</code> state.</p>
  </Admonition>
  
  <Admonition color="slate" title="Root Causes" icon="mdi-alert">
    <ul class="text-[9px] space-y-1 text-slate-200">
      <li>Typo in image name/tag.</li>
      <li>Tag doesn't exist on Registry.</li>
      <li>Missing <b>ImagePullSecrets</b> for private repos.</li>
    </ul>
  </Admonition>
</div>

<div class="mt-6">
  <Admonition color="slate" title="How to debug?" icon="mdi-eye-check">
    <code class="text-[10px]">kubectl describe pod &lt;pod-name&gt;</code>
    <p class="text-[10px] text-slate-400 mt-2 italic">Look at the <b>Events</b> section at the bottom.</p>
  </Admonition>
</div>

---

# Essential Debugging (kubectl)

The standard tools for troubleshooting any Kubernetes cluster:

<div class="mt-6 text-sm">

| Command | Purpose |
|---------|---------|
| `kubectl get pods -w` | Watch pods change state in real-time |
| `kubectl logs -f <pod-name>` | Tail the application logs live |
| `kubectl describe pod <pod>` | View detailed state and recent events |
| `kubectl get events --sort-by=.metadata.creationTimestamp` | Cluster-wide recent events |
| `kubectl exec -it <pod-name> -- /bin/sh` | **CRITICAL:** SSH into a running container |
| `kubectl port-forward svc/<svc> 8080:80` | Access internal services locally |

</div>

---

# Essential Debugging (Minikube)

Specific tools for your local development environment:

<div class="mt-6 text-sm">

| Command | Purpose |
|---------|---------|
| `minikube dashboard` | Open the web-based K8s UI in your browser |
| `minikube service <name>` | Automatically open a NodePort service in browser |
| `minikube tunnel` | **CRITICAL:** Bridges `Type: LoadBalancer` to localhost |
| `minikube image load <img..>` | Sideload local images (bypass DockerHub/GHCR) |
| `minikube logs` | Debug the Minikube cluster/node itself |

</div>

---

# Demo Environment: Minikube

Now that our local cluster is running...

<div class="mt-6">
  <Admonition color="blue" title="Minikube Demo Checklist (Local vs. Cloud)" icon="mdi-checkbox-marked-circle-outline">
    <ul class="text-[10px] space-y-2 text-slate-200">
      <li><mdi-lan class="text-blue-400 inline"/> <b>Networking:</b> <code>Type: LoadBalancer</code> stays <code>&lt;pending&gt;</code> until you run <code>minikube tunnel</code> in a separate terminal.</li>
      <li><mdi-router-wireless class="text-green-400 inline"/> <b>Ingress:</b> Ensure the addon is enabled: <code>minikube addons enable ingress</code>.</li>
      <li><mdi-docker class="text-orange-400 inline"/> <b>Local Images:</b> Run <code>minikube image load &lt;image-name&gt;</code> instead of pushing to DockerHub/GHCR to prevent <code>ImagePullBackOff</code> errors during rapid testing.</li>
    </ul>
  </Admonition>
</div>

<div class="mt-6 space-y-2 text-left text-sm bg-slate-800 p-4 rounded border border-slate-700">

### Instructor Demo:
1. We will deploy intentionally "broken" YAML manifests (`CrashLoopBackOff`, `ImagePullBackOff`).
2. We will systematically use `kubectl` commands to find the root cause.
3. We will fix the YAML files and apply them to see the Pods turn into `Running`.

<p class="text-[10px] text-slate-400 italic mt-2">*Code is available in the `/demo/debugging` folder!*</p>

</div>

# Section 6: CI/CD, Helm & GitOps

From Manual Manifests to Automated Pipelines

---

# Deep Dive: Helm - The K8s Package Manager

Manifests get messy quickly. A typical app needs a Deployment, Service, Ingress, and Secret.

<div class="grid grid-cols-2 gap-8 mt-6">
  <Admonition color="slate" title="The Problem" icon="mdi-alert-box">
    <p class="text-[10px] text-slate-200">Hardcoding <code>image: v1</code> means manual edits every release. Sharing YAMLs with different configs is impossible.</p>
  </Admonition>

  <Admonition color="slate" title="The Helm Solution" icon="mdi-package-variant">
    <p class="text-[10px] text-slate-200">Packages YAMLs into a <b>Chart</b>. Uses Go-templating and <code>values.yaml</code> for dynamic config.</p>
  </Admonition>
</div>

<div class="mt-8">
  <Admonition color="slate" title="Example: 1-Line Postgres" icon="mdi-database-import">
    <div class="bg-slate-900 p-2 rounded text-xs font-mono text-slate-400">
      helm install my-db bitnami/postgresql --set auth.postgresPassword=secret
    </div>
  </Admonition>
</div>

---

# The CI/CD Pipeline

<div class="mt-4 flex justify-center">
  <img src="/cicd_pipeline_workflow.png" class="h-[400px] rounded shadow" alt="CI/CD Pipeline Architecture" />
</div>

---

# CI: The GitHub "Push" Model

How GitHub Actions continuously integrates your code:

<div class="mt-6 space-y-4 text-sm bg-slate-800 p-4 rounded border border-slate-700">

- <mdi-source-commit class="text-blue-400 inline"/> **Trigger:** Developer pushes code to GitHub.
- <mdi-robot class="text-green-400 inline"/> **Build:** GitHub Actions triggers a workflow (`.github/workflows/deploy.yml`).
- <mdi-docker class="text-orange-400 inline"/> **Containerize:** The runner builds the image and pushes it to GitHub Container Registry (`ghcr.io`).
- <mdi-file-edit class="text-purple-400 inline"/> **Update:** The workflow uses a "Manifest Update" step to change the image tag in your `deployment.yaml` and commits the change back to the repository.

</div>

<div class="mt-6">
  <Admonition color="slate" title="The Problem with Push CD" icon="mdi-alert">
    <p class="text-xs text-slate-200">
      If GitHub Actions also pushes directly to Kubernetes, you must give it highly privileged access to your production cluster. This is a massive security risk!
    </p>
  </Admonition>
</div>

---

# CD: The GitOps "Pull" Model

Tools like **ArgoCD** invert the deployment logic to be secure and declarative.

<div class="grid grid-cols-2 gap-4 mt-8">
  <Admonition color="slate" title="1. Source of Truth" icon="mdi-source-repository">
    <p class="text-[10px] text-slate-200 leading-relaxed">
      A separate GitHub repository holds your K8s manifests (or Helm charts). This is the only place authorized to define "Desired State".
    </p>
  </Admonition>

  <Admonition color="slate" title="2. ArgoCD" icon="mdi-robot">
    <p class="text-[10px] text-slate-200 leading-relaxed">
      Installed <b>inside</b> your Minikube/K8s cluster, it constantly watches that specific GitHub repository for changes.
    </p>
  </Admonition>
  
  <Admonition color="slate" title="3. Sync & Strategy" icon="mdi-sync">
    <p class="text-[10px] text-slate-200 leading-relaxed">
      When a manifest changes, ArgoCD pulls the new state. It doesn't just "apply"—with <b>Argo Rollouts</b>, it orchestrates Blue/Green or Canaries automatically.
    </p>
  </Admonition>
  
  <Admonition color="slate" title="Closing the Loop" icon="mdi-infinity">
    <p class="text-[10px] text-slate-200 leading-relaxed">
      GitOps is how we enforce our <b>Risk Management</b> decisions (strategies) at scale. It turns "I hope this rollout works" into "The system will verify and progress based on metrics."
    </p>
  </Admonition>
</div>

---
layout: section
---

# Hands-on Debugging Challenge
## The Broken E-Commerce App

Navigate to the `exercise/` folder and open the `README.md`.
You are the on-call DevOps engineer. Production is down. Good luck!

---

# The Big Picture: Day 1 & Day 2

<div class="grid grid-cols-2 gap-8 mt-4">
<div>

### Day 1: The Building Blocks
- **Docker:** Packaging apps into portable artifacts.
- **Images & Layers:** Efficiency via caching.
- **Containers:** Isolated execution environments.
- **Docker Compose:** Managing multi-container local environments.

</div>
<div>

### Day 2: The Orchestrator
- **Kubernetes:** Automating scaling and self-healing.
- **Networking (L4):** Services (ClusterIP, NodePort, LoadBalancer).
- **Ingress (L7):** Smart routing and SSL/TLS.
- **GitOps:** The future of declarative deployments.

</div>
</div>

<div class="mt-8 bg-slate-800 p-4 rounded border border-slate-700 text-center">
<p class="text-lg font-bold">"Automation is not about doing things fast, it's about doing things reliably."</p>
</div>
---

# Quick Reference: Object Cheat Sheet

| Object | Purpose | API Group |
|--------|---------|-----------|
| **Pod** | Basic unit of deployment | `core` |
| **Deployment** | Stateless workloads (Replication/Updates) | `apps` |
| **StatefulSet** | Stateful workloads (Stable IDs/Storage) | `apps` |
| **Service** | Stable network endpoint (L4) | `core` |
| **Ingress** | HTTP/HTTPS Routing (L7) | `networking.k8s.io` |
| **ConfigMap / Secret** | Configuration & Sensitive Data | `core` |
| **PV / PVC** | Persistent Storage & Claims | `core` |
| **HPA** | Horizontal Pod Autoscaling | `autoscaling` |
| **RBAC** | Roles & Permissions | `rbac.authorization.k8s.io` |

<div class="mt-4 text-center text-xs text-slate-400">
  For more details, check the <code>K8S_CHEATSHEET.md</code> in the project root.
</div>
