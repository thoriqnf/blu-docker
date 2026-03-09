---
theme: seriph
background: https://images.unsplash.com/photo-1558494949-ef010cbdcc31?q=80&w=2034&auto=format&fit=crop
class: text-center
highlighter: shiki
lineNumbers: false
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

1. **Kubernetes Networking Deep Dive**
2. **Ingress & Service Exposure**
3. **Debugging Kubernetes Workflow**
4. **Practice: Deploy & Debug (Minikube/K3s)**
   - *Hands-on: Finding and fixing intentional cluster bugs*
5. **CI/CD Integration with K8s**
6. **Hands-on Debugging Challenge**
   - *Fix the broken E-Commerce App!*

</v-clicks>

---
layout: center
class: text-center
---

# Section 1: Kubernetes Networking Deep Dive

How do containers actually talk to each other?

---

# The Kubernetes Network Model

Kubernetes imposes fundamental rules on networking:

<v-clicks>

1. **Every Pod gets its own unique IP address.**
   - No need to map container ports to host ports like in Docker Compose.
2. **Containers within a Pod share the same IP (and network namespace).**
   - They communicate via `localhost`.
3. **Pods can communicate with all other Pods without NAT.**
   - Even if they are on completely different physical servers (Worker Nodes).

</v-clicks>

<div v-click class="mt-6 p-4 bg-gray-100 rounded text-black text-sm">
  <strong>How does this work?</strong> <br/>
  It's handled by a CNI plugin (Container Network Interface) like Calico, Flannel, or Cilium which creates an overlay network extending across your cluster.
</div>

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
layout: center
class: text-center
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
spec:
  rules:
    # 1. Host-based: Route based on domain name
    - host: api.devops.local
      http:
        paths:
          - path: /
            pathType: Prefix
            backend:
              service: { name: api-service, port: { number: 8080 } }
    
    # 2. Path-based: Route based on URL sub-path
    - host: store.devops.local
      http:
        paths:
          - path: /cart
            pathType: Prefix
            backend:
              service: { name: cart-service, port: { number: 80 } }
```

---
layout: center
class: text-center
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
layout: center
class: text-center
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
layout: center
class: text-center
---

# Section 5: CI/CD Integration with K8s

How code gets from a commit to a running Pod

---

# The CI/CD Pipeline

<div class="mt-4">
  <img src="/cicd-pipeline.png" class="w-full h-auto rounded shadow" alt="CI/CD Pipeline Architecture" />
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
    <p class="text-xs text-gray-500 mt-2">If someone manually runs <code>kubectl delete deployment</code>, ArgoCD immediately recreates it because the Git repo says it should exist.</p>
  </div>
</div>
</div>

---
layout: center
class: text-center
---

# Hands-on Debugging Challenge
## The Broken E-Commerce App

Navigate to the `exercise/` folder and open the `README.md`.
You are the on-call DevOps engineer. Production is down. Good luck!
