---
theme: neversink
colorSchema: dark
highlighter: shiki
lineNumbers: true
fonts:
  sans: Inter
  serif: Inter
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

<div class="mt-10">
  <img src="/k8s-networking.png" class="h-40 rounded shadow mx-auto inline-block" />
  <img src="/cicd-pipeline.png" class="h-40 rounded shadow mx-auto inline-block ml-4" />
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
<ol class="mt-4 space-y-2 list-decimal ml-6">
  <li><b>Kubernetes Networking Deep Dive</b></li>
  <li><b>Ingress & Service Exposure</b></li>
  <li><b>Debugging Kubernetes Workflow</b></li>
  <li><b>Practice: Deploy & Debug (Minikube/K3s)</b>
    <ul class="ml-6 list-disc text-gray-400"><li><em>Hands-on: Finding and fixing intentional cluster bugs</em></li></ul>
  </li>
  <li><b>CI/CD Integration with K8s</b></li>
  <li><b>Hands-on Debugging Challenge</b>
    <ul class="ml-6 list-disc text-gray-400"><li><em>Fix the broken E-Commerce App!</em></li></ul>
  </li>
</ol>
</v-clicks>

---
layout: section
---

# ☕ Intermezzo: The Orchestration Wars

Why did Kubernetes win?

---

# The Orchestration Wars (2014-2017)

Before Kubernetes was the undisputed king, there was a fierce battle for how to orchestrate containers.

- **Docker Swarm:**
  - Built by Docker. Very easy to use. "Just like Docker Compose but for servers."
  - **Why it lost:** Struggled with complex enterprise networking and stateful storage at scale.
- **Apache Mesos (Marathon):**
  - "The Datacenter OS". Extremely powerful, ran Twitter and Airbnb.
  - **Why it lost:** Incredibly difficult to install and manage. Required a PhD in distributed systems.
- **Kubernetes (Borg's Child):**
  - Open-sourced by Google. Based on 15 years of running Google Search (Borg).
  - **Why it won:** Highly extensible API, declarative "Desired State" architecture, and massive community backing (CNCF).

---
layout: section
---

# Recap: The "Restaurant" Analogy

How do all the Kubernetes pieces actually fit together?

---

# Kubernetes is a Massive Restaurant

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

### 1. The Container (The Chef)
The actual person doing the work (computing/serving). They only know how to cook their specific recipe (Node, Java, Python).

### 2. The Pod (The Kitchen Station)
The physical space the Chef works in. It provides the stove, the cutting board, and the local network. *If the stove breaks, the Chef is dead.*

### 3. The ReplicaSet (The Manager)
The manager's only job is to ensure exactly *three* Chefs are always cooking. If a Chef quits (crashes), the Manager instantly hires a new one to replace them.

</div>
<div>

### 4. The Deployment (The Owner)
The entity that tells the Manager what to do. "We are upgrading from Chef v1 to Chef v2." The Owner handles the smooth transition (Rolling Updates) without closing the restaurant.

### 5. The Service (The Waiter)
Customers don't talk to Chefs directly. Customers tell the Waiter what they want, and the Waiter perfectly distributes the orders to whichever Chef in the back is currently free.

### 6. The Ingress (The Hostess)
Stands at the front door (`myapp.com`). Checks your reservation and directs you to the correct dining room (e.g. `/api` goes to the API Waiter, `/web` goes to the Frontend Waiter).

</div>
</div>

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

<div class="mt-6 p-4 bg-gray-800 rounded border-l-4 border-blue-500 text-sm">
  <strong>The Controller Loop:</strong> Kubernetes is constantly reading the <code>spec</code> (what you want) and comparing it to the <code>status</code> (what actually exists right now in reality). If they don't match, K8s takes action to fix reality so it matches the spec!
</div>

---

# Deep Dive: Advanced Pod Features

Pods aren't just for running your simple web server.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### 1. initContainers
- Containers that run strictly *before* the main app starts.
- They must run to completion and exit with code 0.
- **Use case:** Your API needs the database to be ready. The `initContainer` constantly pings the DB. Once the DB pings back, the `initContainer` exits, and your API finally boots perfectly.

</div>
<div>

### 2. Ephemeral Containers (Debug)
- The ultimate debugging tool. 
- You want to `exec` into a Pod, but it's built from `scratch` and doesn't even have a shell (`/bin/sh`) installed!
- **Solution:** You dynamically attach an "Ephemeral" Alpine container to the running Pod sideways. It joins the pod's network namespace, allowing you to debug an un-debuggable image in live production!

</div>
</div>

---

# Guardrails: Limits, Requests & Quotas

Assume every developer will accidentally write a memory leak.

<div class="space-y-4 mt-6">

- **Requests:** The *minimum* amount of RAM/CPU the Pod needs. (e.g. `256Mi`). The Scheduler uses this to find a physical Node with at least 256MB of free space.
- **Limits:** The *absolute maximum* the Pod is allowed to use. (e.g. `512Mi`). If the app tries to use 513MB, the Linux kernel instantly assassinates the Pod (`OOMKilled`).

</div>

<div class="grid grid-cols-2 gap-4 mt-6">
<div class="bg-gray-800 p-4 border border-gray-700 rounded block">

<h3 class="text-blue-400">ResourceQuotas</h3>
<p class="text-sm mt-2">Applied to a Namespace. "The entire Dev Team namespace can only use a maximum of 10 CPUs total across all their apps combined."</p>

</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded block">

<h3 class="text-green-400">LimitRanges</h3>
<p class="text-sm mt-2">Default values. "If a developer forgets to put a Memory Limit in their YAML, automatically inject a limit of 512Mi so they don't crash the server."</p>

</div>
</div>

---
layout: section
---

# Project Setup: From Zero to K8s

How to launch your code

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
layout: section
---

# Section 1: Kubernetes Networking Deep Dive

How do containers actually talk to each other?

---

# The Kubernetes Network Model

Kubernetes imposes fundamental rules on networking:

1. **Every Pod gets its own unique IP address.**
   - No need to map container ports to host ports like in Docker Compose.
2. **Containers within a Pod share the same IP (and network namespace).**
   - They communicate via `localhost`.
3. **Pods can communicate with all other Pods without NAT.**
   - Even if they are on completely different physical servers (Worker Nodes).

<v-clicks>
<div class="mt-6 p-4 bg-gray-100 rounded text-black text-sm">
  <strong>How does this work?</strong> <br/>
  It's handled by a CNI plugin (Container Network Interface) like Calico, Flannel, or Cilium which creates an overlay network extending across your cluster.
</div>
</v-clicks>

---

# Recap: Services

Because Pods come and go (IPs change), we use **Services** for stable endpoints.

<div class="grid grid-cols-3 gap-4 mt-8">
<div>
  <h3 class="text-blue-500 text-lg font-bold">1. ClusterIP (Default)</h3>
  <p class="text-sm">Exposes the Service on a cluster-internal IP.</p>
  <p class="text-xs text-gray-500">Use case: Backend database that should ONLY be reached by your API.</p>
</div>
<div>
  <h3 class="text-green-500 text-lg font-bold">2. NodePort</h3>
  <p class="text-sm">Exposes the Service on each Node's IP at a static port (30000-32767).</p>
  <p class="text-xs text-gray-500">Use case: Simple testing, or when integrating with legacy external load balancers.</p>
</div>
<div>
  <h3 class="text-orange-500 text-lg font-bold">3. LoadBalancer</h3>
  <p class="text-sm">Provisions a Cloud provider's load balancer (AWS ELB, GCP LB).</p>
  <p class="text-xs text-gray-500">Use case: Exposing a production application directly to the public internet.</p>
</div>
</div>

---

# The limits of Service Type: LoadBalancer

If `LoadBalancer` exposes apps to the internet, why isn't it enough?

<div class="mt-8 space-y-4">

❌ **Cost:** If you have 20 microservices and you make them all Type: LoadBalancer, you pay AWS/GCP for 20 expensive cloud load balancers.

❌ **Management:** You have 20 different public IP addresses to map in your DNS records.

❌ **No routing logic:** A Service LoadBalancer operates at Layer 4 (TCP/UDP). It cannot route based on URLs (`/api/v1` vs `/web`).

</div>

<div class="mt-6 text-center text-xl font-bold bg-green-100 p-2 rounded text-green-900">
  Solution: We need a Layer 7 router. We need INGRESS.
</div>

---
layout: section
---

# Deep Dive: Kubernetes Architecture

Let's look closely at what actually powers the cluster.

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
- `etcd` requires a majority (Quorum) to agree on a state change.
- `(N / 2) + 1` = Quorum.
- If you have 3 Master Nodes, you can lose 1 and survive.
- If you have 2 Master Nodes and lose 1, your entire cluster enters read-only lock down! (Never use an even number of Masters).

</div>
</div>

---

# The Control Plane: kube-scheduler

How does K8s decide where your Pod goes?

<div class="mt-8 space-y-4">

When you apply a Deployment, the scheduler does a 2-step process for every new Pod:

1. **Filtering (Predicates):**
   - *Does Node 1 have enough RAM?* (Yes)
   - *Does Node 2 have a GPU attached?* (No -> Filtered out)
   - *Is Node 3 tainted against my Pod?* (Yes -> Filtered out)

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
layout: section
---

# Section 2: Ingress & Service Exposure

Smart routing for the modern web

---

# What is an Ingress?

We use two distinct resources in Kubernetes: The **Ingress Resource** and the **Ingress Controller**.

<div class="grid grid-cols-2 gap-8 mt-8">
<div>

### 1. Ingress Resource (The Rules)
- A YAML file defining routing rules.
- "If traffic goes to `api.myapp.com`, send it to the `api-service`."
- It is just a configuration object. By itself, it does nothing!

</div>
<div>

### 2. Ingress Controller (The Engine)
- The actual server (like NGINX, Traefik, or HAProxy) running in your cluster.
- It watches the K8s API for new Ingress Resources.
- When you apply an Ingress rule, the Controller updates its proxy configuration to route the traffic.

</div>
</div>

---

# How Ingress Routes Traffic

<div class="grid grid-cols-2 gap-4">
<div>

Let's trace a user request from the browser to your Pod:

1. **External DNS:** User visits `api.myapp.com`. Resolves to the Cloud LoadBalancer IP.
2. **Cloud LB:** Forwards traffic to the **Ingress Controller** Service on port 80/443.
3. **Ingress Controller:** NGINX evaluates the HTTP Request Header (`Host: api.myapp.com`).
4. **Internal Routing:** NGINX routes traffic to the targeted internal **ClusterIP Service**.
5. **Pod:** Request reaches your application.

</div>
<div>
  <img src="/k8s-ingress.png" class="w-full rounded shadow" alt="K8s Ingress Architecture" />
</div>
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

<div class="mt-6 flex flex-col space-y-4">
  
1. You install **Cert-Manager** (a popular open-source K8s add-on) into your cluster.
2. Cert-Manager watches your Ingress YAML files for `tls` blocks.
3. When it sees a new domain, Cert-Manager automatically speaks to **Let's Encrypt**.
4. It performs the ACME DNS or HTTP-01 challenge to prove you own the domain.
5. In 10 seconds, it downloads a free SSL certificate.
6. It saves that certificate as a K8s `Secret`.
7. Your Ingress Controller (NGINX) immediately detects the Secret and enables green-padlock HTTPS traffic.
8. Cert-Manager automatically renews the certificate 30 days before it expires.

*Zero human intervention required.*
</div>

---

# The Future: The Gateway API

Ingress is great, but it has flaws. The K8s project is officially replacing `Ingress` with the **Gateway API**.

<div class="grid grid-cols-2 gap-8 mt-6">
<div>

<h3 class="text-red-500">The Problem with Ingress</h3>

- Built specifically for HTTP routing only. TCP/UDP routing is a hack.
- Everything is crammed into one massive YAML file.
- Developers and Admins fight over who gets to edit the singular Ingress YAML.

</div>
<div>

<h3 class="text-green-500">The Gateway API Solution</h3>

- **Role-Oriented:** Splits routing into distinct roles.
  - *Cluster Admin* deploys a `GatewayClass`.
  - *Platform Eng* deploys a `Gateway` (The actual load balancer).
  - *Developers* deploy `HTTPRoute` bindings.
- Fully supports modern routing: gRPC, headers, weighted traffic splitting (A/B testing out of the box).

</div>
</div>

---

# Auto-Scaling Kubernetes

Kubernetes can automatically scale your infrastructure dynamically based on real-time traffic. There are 3 distinct auto-scalers.

<div class="grid grid-cols-3 gap-4 mt-6">
<div class="bg-gray-800 p-4 border border-gray-700 rounded text-sm">

<h3 class="text-blue-400 font-bold mb-2">1. HPA (Horizontal Pod Autoscaler)</h3>
<p class="text-xs text-gray-300">Scales **OUT**. Watches CPU/Memory. If CPU > 80%, it increases replicas from 3 to 10.</p>
```yaml
minReplicas: 3
maxReplicas: 10
targetCPUUtilizationPercentage: 80
```
</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded text-sm">

<h3 class="text-green-400 font-bold mb-2">2. VPA (Vertical Pod Autoscaler)</h3>
<p class="text-xs text-gray-300">Scales **UP**. It observes your app. If your JVM starts OOMCrashing, the VPA automatically restarts the Pod with a larger Memory Limit (e.g., 1024Mi instead of 512Mi).</p>
</div>
<div class="bg-gray-800 p-4 border border-gray-700 rounded text-sm">

<h3 class="text-purple-400 font-bold mb-2">3. CA (Cluster Autoscaler)</h3>
<p class="text-xs text-gray-300">Talks to the Cloud API (AWS/GCP). If the HPA creates 50 new Pods and your physical servers are full, the CA boots up 3 brand new physical EC2 instances to hold them.</p>
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
- Base64 encoded (NOT encrypted) key-value pairs.
- Same injection methods as ConfigMaps.
- Easily intercepted by anyone who runs `kubectl get secrets`.

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

<h3 class="text-blue-500 font-bold">Approach 1: SealedSecrets (Bitnami)</h3>
<p class="text-sm">You encrypt the secret *offline* using a public key provided by the cluster. You push the encrypted `SealedSecret.yaml` to GitHub. It is completely safe. Only the cluster has the private key to decrypt it into a normal `Secret` once it is pulled inside.</p>

<h3 class="text-green-500 font-bold">Approach 2: External Secrets Operator</h3>
<p class="text-sm">You never store passwords in Git at all. You store them in AWS Secrets Manager or HashiCorp Vault. K8s runs an operator that constantly reaches out to AWS, downloads the real password, and injects it directly into K8s memory as a `Secret`.</p>

</div>

---

# Advanced Deployment Strategies

"Rolling Updates" means replacing old pods with new pods one by one. But what if the new code immediately breaks?

<div class="grid grid-cols-2 gap-8 mt-8 text-sm">
<div>

<h3 class="text-blue-400 font-bold">Blue/Green Deployment</h3>
<p class="mt-2 text-gray-300">You run two identical production environments simultaneously.</p>
<ul class="mt-2 text-xs space-y-1">
  <li>**Blue:** Running v1. Currently receiving 100% of live user traffic.</li>
  <li>**Green:** Running v2. Receiving 0% of traffic. You test it secretly.</li>
</ul>
<p class="mt-2 text-gray-300">When ready, you flip the Service/Ingress router instantly. Green now gets 100% of traffic. Rollback is just flipping a switch.</p>

</div>
<div>

<h3 class="text-orange-400 font-bold">Canary Deployment</h3>
<p class="mt-2 text-gray-300">You deploy v2, but only send **5% of live traffic** to it. You observe metrics (error rates, latency).</p>
<ul class="mt-2 text-xs space-y-1">
  <li>If error rate spikes to 20%, you automatically abort and roll back.</li>
  <li>If solid for 10 minutes, escalate to 25%, 50%, 100%.</li>
</ul>
<p class="mt-2 text-gray-300">Often managed automatically by tools like **ArgoCD** or **Flagger**.</p>

</div>
</div>

---
layout: section
---

# Section 3: Debugging Kubernetes

The systematic troubleshooting workflow

---

# The Golden Troubleshooting Workflow

When an app is down, follow the traffic from the inside out:

<br/>

<div class="space-y-4">

1️⃣ **Is the Pod running?** (`kubectl get pods`)
  - No? Describe it. Logs won't help if it never started.

2️⃣ **Are the application logs clean?** (`kubectl logs <pod-name>`)
  - The pod might be "Running" but throwing 500 exceptions internally.

3️⃣ **Is the Service targeting the Pod correctly?** (`kubectl describe service`)
  - Check the `Endpoints`. If Endpoints is `<none>`, your Service labels don't match your Pod labels!

4️⃣ **Is the Ingress routing to the Service correctly?**
  - Check TLS config, hostnames, and paths.

</div>

---

# Common Error 1: CrashLoopBackOff

<div class="grid grid-cols-2 gap-8">
<div>

**Symptom:** The pod starts, crashes immediately, and Kubernetes keeps trying to restart it infinitely.

**Root Causes:**
- Application fatal error (e.g. Missing database connection string)
- Misconfigured command/entrypoint
- Out of Memory (OOMKilled) forcing a restart

</div>
<div>

**How to debug:**

1. Check the logs of the *previous* crashed container instance:
```bash
kubectl logs <pod-name> --previous
```

2. Describe it to look for `OOMKilled` reasons:
```bash
kubectl describe pod <pod-name>
```

</div>
</div>

---

# Common Error 2: ImagePullBackOff

<div class="grid grid-cols-2 gap-8">
<div>

**Symptom:** The pod status says `ImagePullBackOff` or `ErrImagePull`.

**Root Causes:**
- Typo in the image name or tag.
- The image tag doesn't exist on DockerHub/Registry.
- The cluster doesn't have credentials (ImagePullSecrets) to access a private registry.

</div>
<div>

**How to debug:**

1. Describe the pod. Look at the `Events` section at the very bottom:
```bash
kubectl describe pod <pod-name>
```

You will clearly see an event saying:
`Failed to pull image "my-app:v2": rpc error... not found`

</div>
</div>

---

# Essential Debugging Commands

The commands every DevOps engineer uses daily:

<div class="mt-6 text-sm">

| Command | Purpose |
|---------|---------|
| `kubectl get pods -w` | Watch pods change state in real-time |
| `kubectl logs -f <pod-name>` | Tail the application logs live |
| `kubectl logs <pod-name> -c <container>` | Read logs when a Pod has multi-containers |
| `kubectl describe pod <pod>` | View detailed state and recent events |
| `kubectl get events --sort-by=.metadata.creationTimestamp` | Cluster-wide recent events |
| `kubectl exec -it <pod-name> -- /bin/sh` | **CRITICAL:** SSH into a running container for live debugging |
| `kubectl port-forward svc/<my-svc> 8080:80` | Access an internal service locally without Ingress |

</div>

---
layout: section
---

# Section 4: Practice - Deploy & Debug
Live Demonstration on Minikube / K3s

---

# How to Setup Minikube

Before we debug, we need a local cluster. Minikube provisions a single-node K8s cluster inside a VM or Docker container on your laptop.

<div class="grid grid-cols-2 gap-8 mt-8 text-left">
<div>

### Installation (Mac/Linux)

```bash
# macOS (Homebrew)
$ brew install minikube

# Start the cluster (using docker driver)
$ minikube start --driver=docker
```

</div>
<div>

### Verification

Check your cluster status:
```bash
$ minikube status
```

Verify your node is ready:
```bash
$ kubectl get nodes
NAME       STATUS   ROLES           AGE   VERSION
minikube   Ready    control-plane   1m    v1.28.3
```

</div>
</div>

---

# Demo Environment: Minikube

Now that our local cluster is running...

<div class="mt-8 space-y-4 text-left">

### Instructor Demo:
1. We will deploy intentionally "broken" YAML manifests (`CrashLoopBackOff`, `ImagePullBackOff`).
2. We will systematically use `kubectl` commands to find the root cause.
3. We will fix the YAML files and apply them to see the Pods turn into `Running`.

*Code is available in the `/demo/debugging` folder!*

</div>

---
layout: section
---

# Deep Dive: Helm - The Kubernetes Package Manager

Manifests get messy quickly. A typical app needs a Deployment, Service, Ingress, ConfigMap, and Secret.

<div class="grid grid-cols-2 gap-6 mt-6">
<div>

**The Raw YAML Problem:**
- Hardcoding `myapp:v1.0.0` in your `deployment.yaml` means you have to edit the file every time you release code.
- How do you share your app with another team who wants a different database password?

**The Helm Solution:**
- Helm packages your YAMLs into a **Chart**.
- It uses Go-templating: `image: {{ .Values.image.repository }}:{{ .Values.image.tag }}`
- You provide a `values.yaml` file to dynamically configure the app.

</div>
<div>

**Example Usage:**

```bash
# Add the official Bitnami database repo
$ helm repo add bitnami https://charts.bitnami.com/bitnami

# Install a Production-ready Postgres Cluster in 1 line
$ helm install my-db bitnami/postgresql \
    --set auth.postgresPassword=secret \
    --set architecture=replication
```

</div>
</div>

---

# The CI/CD Pipeline

<div class="mt-4 flex justify-center">
  <img src="/cicd-pipeline.png" class="h-[400px] rounded shadow" alt="CI/CD Pipeline Architecture" />
</div>

---

# Traditional CD: The "Push" Model

How CI tools like GitHub Actions / GitLab CI deploy to Kubernetes:

<div class="mt-6 text-sm">

**The Process:**
1. Code is merged. Runner builds Docker image `my-app:git-a1b2c3d`.
2. Runner pushes image to Docker Registry.
3. Runner authenticates to your K8s cluster API.
4. Runner executes `kubectl set image deployment/my-app api=my-app:git-a1b2c3d`
   *(or it modifies the `deployment.yaml` and runs `kubectl apply -f`)*.

**The Downside:**
- You have to give your CI tool (outside the cluster) highly privileged access into your production cluster. (Security risk!)

</div>

---

# Next-Gen CD: The GitOps "Pull" Model

Tools like **ArgoCD** or **FluxCD** invert the deployment logic.

<div class="grid grid-cols-2 gap-6 mt-6">
<div>

**The Push Model (Bad):**
`GitHub` pushes changes OUT to `Kubernetes`.

<br/>

**The Pull Model / GitOps (Great!):**
An agent runs *inside* Kubernetes. It constantly watches your Git repository.
When you commit a new YAML manifest to Git, ArgoCD pulls the change and applies it internally.

K8s pulls from Git. Git never talks to K8s.

</div>
<div>
  <div class="bg-gray-100 p-4 rounded text-center h-full flex flex-col justify-center text-black">
    <h3 class="font-bold">Git is the Single Source of Truth</h3>
    <p class="text-xs text-gray-500 mt-2">If someone manually runs <code>kubectl delete deployment</code>, ArgoCD instantly recreates it because the Git repo says it should exist. (Self-Healing Reconciliation).</p>
  </div>
</div>
</div>

---

# ArgoCD Application Deep Dive

How do you tell ArgoCD what to watch? You use a CRD (Custom Resource Definition) called an `Application`.

```yaml {all|5-7|9-11|13-16|all}
apiVersion: argoproj.io/v1alpha1
kind: Application
metadata:
  name: my-store-frontend
  namespace: argocd
spec:
  project: default
  source:
    # 1. The GitHub Repo containing your Helm Chart or YAML
    repoURL: 'https://github.com/my-org/store-manifests.git'
    path: 'frontend'
    targetRevision: HEAD
  destination:
    # 2. Deploy it locally into this cluster
    server: 'https://kubernetes.default.svc'
    namespace: store-prod
  syncPolicy:
    # 3. Automatically sync if Git changes. Auto-prune stuff removed from Git.
    automated:
      prune: true
      selfHeal: true
```

---
layout: section
---

# Hands-on Debugging Challenge
## The Broken E-Commerce App

Navigate to the `exercise/` folder and open the `README.md`.
You are the on-call DevOps engineer. Production is down. Good luck!
