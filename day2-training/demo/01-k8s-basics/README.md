# Demo 1: Kubernetes Fundamentals & Minikube Setup

This demo walks through the basic workflow of containerizing a simple application and deploying it to a local Kubernetes cluster using Minikube.

## Learning Objectives
- Understanding the `Deployment` and `Service` objects.
- Learning how to use `kubectl` to manage resources.
- Exposing applications locally using `minikube service`.

## Prerequisites
- Minikube installed (`brew install minikube`)
- Kubectl installed (`brew install kubectl`)
- Docker Desktop or Colima running (if using the docker driver)

## Step-by-Step Guide

### 1. Start Minikube
Ensure your local cluster is running:
```bash
minikube start --driver=docker
```

### 2. (Optional) Build the Image
Normally, you would build and push your image. In Minikube, you can build directly into the cluster's registry:
```bash
# Point your shell to minikube's docker daemon
eval $(minikube docker-env)

# Build the image
cd app
docker build -t demo-app:v1 .
```
*Note: For this demo, the provided YAML uses `nginx:alpine` by default for simplicity.*

### 3. Apply Kubernetes Manifests
Navigate to the `k8s` directory and apply the configurations:
```bash
kubectl apply -f configmap.yaml
kubectl apply -f deployment.yaml
kubectl apply -f service.yaml
```

### 4. Verify the Deployment
Check if the pods are running:
```bash
kubectl get pods
kubectl get service demo-app-service
```

### 5. Access the Application
> [!IMPORTANT]
> Since you are using the **Docker driver on macOS**, you cannot access the IP `192.168.49.2` directly from your browser. You MUST use the Minikube tunnel.

Run this command to open the application in your browser:
```bash
minikube service demo-app-service
```

If it doesn't open automatically, look for the **127.0.0.1** URL in the terminal output (e.g., `http://127.0.0.1:61561`).

## Clean Up
To remove the resources created in this demo:
```bash
kubectl delete -f deployment.yaml
kubectl delete -f service.yaml
```
