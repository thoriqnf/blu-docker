# Demo 4: Deploying and Inspecting a 3-Tier Application

This demo showcases a healthy 3-tier application running in Minikube. The goal is to demonstrate standard deployment and inspection commands.

## Prerequisites
1. Minikube is running (`minikube start`).
2. You have built and loaded the local images.

## Step 1: Prepare the Environment

Run the setup script to build the Docker images and load them directly into Minikube. This prevents `ImagePullBackOff` errors since we aren't pushing to DockerHub.

```bash
./setup-images.sh
```

## Step 2: Deploy the Application

Deploy all the Kubernetes manifests (Database, Backend, Frontend, and Ingress).

```bash
kubectl apply -f k8s/
```

## Step 3: Inspect the Status

Watch the pods come alive. The database will start, the backend will connect to it, and the frontend will become available.

```bash
# Check pod status
kubectl get pods -w

# Check services (notice NodePort vs ClusterIP)
kubectl get svc
```

## Step 4: Inspect Logs and Events

Demonstrate how to peek inside the running containers.

```bash
# View Backend Logs (should show DB initialized)
kubectl logs deployment/demo-backend

# Describe a Pod to see its events (Scheduled, Pulled, Started)
kubectl describe pod -l app=demo-frontend
```

## Step 5: Access the Application

There are three ways to demonstrate access:

### Method A: Port Forwarding (Direct to Frontend)
```bash
kubectl port-forward service/demo-frontend 8080:80
# Open http://localhost:8080 in your browser
```

### Method B: Minikube Service (NodePort)
```bash
minikube service demo-frontend
# This will automatically open a browser window pointing to the generated NodePort.
```

### Method C: Minikube tunnel (LoadBalancer/Ingress)
If you have the Ingress addon enabled (`minikube addons enable ingress`):
```bash
# In an empty terminal:
minikube tunnel

# Then map 3tier.local to 127.0.0.1 in your /etc/hosts file
# Open http://3tier.local in your browser
```

## Clean Up

When finished with the demo:
```bash
kubectl delete -f k8s/
```
