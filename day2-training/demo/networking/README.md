# 🌐 Demo: Kubernetes Ingress and Networking

This demo shows how an Ingress resource routes traffic from the outside world into our Minikube cluster.

## Prerequisites
Ensure Minikube is running and the Ingress addon is enabled:
```bash
minikube start --driver=docker
minikube addons enable ingress
```

## Step 1: Deploy the App & Routing

Apply the Ingress demo YAML file which contains a Deployment, a ClusterIP Service, and an Ingress rule.

```bash
kubectl apply -f ingress-demo.yaml
```

Verify everything is running:
```bash
kubectl get pods
kubectl get svc
kubectl get ingress
```
*Wait until the Ingress gets an IP address assigned under the `ADDRESS` column.*

## Step 2: Accessing the App (Minikube Tunnel)

By default, the Minikube ingress controller is running inside the Minikube VM/Docker container. To route traffic from your laptop's browser into Minikube's network, we need to create a tunnel.

**Open a NEW terminal window and run:**
```bash
minikube tunnel
```
*(Leave this running in the background. It may prompt for your administrator password as it modifies your host routing table.)*

## Step 3: Test the Routing

Currently, the Ingress is mapped to a specific host (e.g., `api.devops.local`). 

We can fake this domain name using `curl` and setting the `Host` header to bypass adding it to our `/etc/hosts` file.
*(Note: Use `127.0.0.1` since `minikube tunnel` forwards to localhost).*

```bash
curl -H "Host: api.devops.local" http://127.0.0.1/
```

You should see a successful response from the backend application demonstrating that the Ingress controller correctly routed the HTTP request based on the domain name to the internal Service!

## Cleanup

1. Close the `minikube tunnel` terminal (Ctrl+C).
2. Delete the created resources:
   ```bash
   kubectl delete -f ingress-demo.yaml
   ```
