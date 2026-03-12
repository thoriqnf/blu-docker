# Demo 2: Kubernetes Networking Deep Dive

This demo explores the three primary service types in Kubernetes: **ClusterIP**, **NodePort**, and **LoadBalancer**.

## Learning Objectives
- Differentiating between internal and external access.
- Understanding how `NodePort` mapping works.
- Simulating a cloud `LoadBalancer` using `minikube tunnel`.

## Step-by-Step Guide

### 1. Preparations
Ensure your cluster is running and navigate to this directory:
```bash
minikube start --driver=docker
cd demo/02-networking
```

### 2. Deploy the Base Components
First, apply the ConfigMap (the UI) and the ClusterIP service (internal only):
```bash
kubectl apply -f k8s/configmap.yaml
kubectl apply -f k8s/01-clusterip.yaml
```

### 3. Scenario A: Internal Communication (ClusterIP)
Try to access the service from your host machine. It will fail because ClusterIP is only for internal cluster traffic.
```bash
# This will NOT work from your browser or host terminal
# curl $(kubectl get svc clusterip-service -o jsonpath='{.spec.clusterIP}')
```

**How to test?** Run a temporary pod *inside* the cluster (ensure you use quotes for the command):
```bash
kubectl run test-pod --image=alpine --restart=Never -it -- sh -c "apk add curl && curl http://clusterip-service"
```

### 4. Scenario B: External Access (NodePort)
Expose the application to your host machine via a static port:
```bash
kubectl apply -f k8s/02-nodeport.yaml
minikube service nodeport-service
```
Select the **127.0.0.1** URL to see the page.

### 5. Scenario C: Cloud Simulation (LoadBalancer)
In a real cloud provider (AWS/GCP), this would provision a physical Load Balancer. In Minikube, we use a tunnel:
```bash
kubectl apply -f k8s/03-loadbalancer.yaml

# In a NEW terminal window, run:
minikube tunnel
```

> [!NOTE]
> **Why the password?** `minikube tunnel` needs `sudo` privileges because it is trying to bind to port **80** on your host machine to simulate a real load balancer IP. Please enter your macOS password when prompted.

Once the tunnel is active, check the external IP:
```bash
kubectl get svc loadbalancer-service
# It should change from <pending> to 127.0.0.1
```
Open `http://127.0.0.1` in your browser.

## Summary Table

| Service Type | Scope | Reachability |
|--------------|-------|--------------|
| **ClusterIP** | Internal | Within Cluster only |
| **NodePort** | External | `<NodeIP>:30081` |
| **LoadBalancer** | External | Dedicated External IP |
