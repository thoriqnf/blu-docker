# 🚀 Final Challenge: Deploy the Todo API to Kubernetes

## Time: 90 minutes

## Overview
In Day 1, you successfully containerized the Todo API using Docker and Docker Compose.
For your final Day 2 challenge, you will take that same application and deploy it to a production-like Kubernetes cluster.

## Pre-requisites
- Minikube running (`minikube start --driver=docker`)
- Ingress addon enabled (`minikube addons enable ingress`)
- The `todo-app:1.0.0` Docker image built locally (from Day 1). 

> [!WARNING]
> **CRITICAL MINIKUBE QUIRK**: Minikube runs inside its own isolated VM/Container. It **cannot** see the Docker images you built on your laptop!
> Before applying your YAMLs, you must tell your terminal to talk to Minikube's internal Docker daemon:
> ```bash
> eval $(minikube docker-env)
> ```
> *Then* run `docker build -t todo-app:1.0.0 .` inside the folder where your code lives. 
> 
> *Alternatively*, if you just want to test K8s manifests without rebuilding the app, switch your deployment image to a generic public image like `nginx:alpine`!

## What's Included
```
starter/
├── manifests/            ← Your task: complete the TODOs in these files
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── ingress.yaml
│   └── configmap.yaml
├── solution/             ← Don't peek! (unless you're stuck 😄)
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── ingress.yaml
│   └── configmap.yaml
└── README.md             ← You are here!
```

---

## Your Tasks

### Task 1: Create the ConfigMap (10 min)
We need to decouple our configuration from the Pod.
1. Open `manifests/configmap.yaml`
2. Complete the YAML to create a ConfigMap named `todo-config`.
3. Add a key `APP_ENVIRONMENT` with the value `kubernetes-prod`.

### Task 2: Write the Deployment (30 min)
1. Open `manifests/deployment.yaml`.
2. Define a Deployment named `todo-api-deployment` with **3 replicas**.
3. Use the `nginx:alpine` image (or your `todo-app:1.0.0` if available in Minikube).
4. Set container port to `80`.
5. **Bonus**: Inject the `todo-config` ConfigMap into the container as environment variables using `envFrom`.

### Task 3: Expose with a Service (15 min)
The pods need a stable internal endpoint.
1. Open `manifests/service.yaml`.
2. Create a `ClusterIP` service named `todo-api-service`.
3. Route incoming port `80` to the target port `80`.
4. Ensure the `selector` matches the labels on your Deployment pods!

### Task 4: External Access via Ingress (20 min)
Internal routing works, now let the outside world in.
1. Open `manifests/ingress.yaml`.
2. Create an Ingress named `todo-api-ingress`.
3. Set the routing rule so that traffic going to the host `todo.local` is routed to the `todo-api-service` on port `80`.

### Task 5: Testing the Full Flow (15 min)
1. Apply the manifests:
   ```bash
   kubectl apply -f manifests/configmap.yaml
   kubectl apply -f manifests/deployment.yaml
   kubectl apply -f manifests/service.yaml
   kubectl apply -f manifests/ingress.yaml
   ```
2. Check the status:
   ```bash
   kubectl get all
   kubectl get ingress
   ```
3. Start the tunnel in a separate terminal: `minikube tunnel`
4. Test the API:
   ```bash
   curl -H "Host: todo.local" http://127.0.0.1
   ```

---

## 🎯 Success Criteria
- [ ] `kubectl get deployments` shows 3/3 ready replicas.
- [ ] `kubectl get configmaps` shows your configmap.
- [ ] `kubectl get svc todo-api-service` shows a ClusterIP.
- [ ] `curl -H "Host: todo.local" http://127.0.0.1` returns the Nginx default page (or the Todo API response).

## 💡 Hints
- To ensure minikube can see your local docker images when testing custom apps, always run `eval $(minikube docker-env)` *before* you run `docker build`.
- Remember to use `targetPort` in the Service for the port the container is actually listening on.

## ⚠️ Common Mistakes
- Typos in the `selector:` labels. If the pods are running but `curl` times out, check `kubectl describe svc todo-api-service` — if `Endpoints: <none>`, your labels are mismatched!
- Forgetting to start `minikube tunnel`.
