# Demo 3: Ingress Routing (Layer 7)

Ingress is the smart gateway for your cluster. Instead of simple port-forwarding, it allows you to route traffic based on **Hostnames** and **Paths**.

## Learning Objectives
- Enabling the NGINX Ingress Controller.
- Creating an `Ingress` resource with host-based rules.
- Understanding how Ingress differs from a LoadBalancer service.

## Step-by-Step Guide

### 1. Enable Ingress Addon
Ingress requires a **Controller** (the engine). Minikube provides NGINX as a modular addon:
```bash
minikube addons enable ingress
```
*Wait ~30 seconds for the NGINX pods to start in the `ingress-nginx` namespace.*

### 2. Deploy the Application
Apply the manifests to create the Pods and internal Service:
```bash
cd demo/03-ingress-routing
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/ingress.yaml
```

### 3. Verify the Setup
Check if the Ingress has been assigned an IP (it might take a minute):
```bash
kubectl get ingress demo-ingress
```

### 4. Local DNS Configuration
Since `api.devops.local` isn't a real public domain, you need to tell your computer to resolve it to Minikube.

> [!IMPORTANT]
> **MacOS / Docker Desktop Users:** Direct Minikube IPs (e.g., `192.168.49.2`) are often unreachable from the host browser. You **must** use `minikube tunnel` and map the domain to `127.0.0.1`.

---

**Option A: The Automated Way (Recommended for MacOS)**
1. In a new terminal, run:
   ```bash
   minikube tunnel
   ```
2. While the tunnel is running, add this line to your `/etc/hosts` file:
   ```text
   127.0.0.1 api.devops.local
   ```
*The tunnel bridges the Ingress controller to your local machine.*

---

**Option B: The Manual Way (Linux/Direct VM)**
1. Get your Minikube IP: `minikube ip` (e.g., `192.168.49.2`).
2. Add this line to your `/etc/hosts` file:
   ```text
   192.168.49.2 api.devops.local
   ```

---

**Option C: The Windows Way**
1. In a **new Administrator terminal** (PowerShell or CMD), run:
   ```powershell
   minikube tunnel
   ```
2. Keep the tunnel running. Open `Notepad` as **Administrator** and edit:
   `C:\Windows\System32\drivers\etc\hosts`
3. Add this line at the bottom:
   ```text
   127.0.0.1 api.devops.local
   ```
*Windows requires elevated privileges to edit the hosts file and run the tunnel networking.*

### 5. Access the Domain
Open your browser and visit:
[http://api.devops.local](http://api.devops.local)

If you are using `minikube tunnel`, you can also try:
`curl -H "Host: api.devops.local" http://localhost`

## Summary
Ingress allows you to use **one** Load Balancer to serve **dozens** of services, differentiated by the requested URL or Domain.
