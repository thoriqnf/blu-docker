# Preparation Guide

## 1. Core Tooling

### 🐳 Docker
Docker is the foundation for everything we do in Day 1.
- **Mac / Windows:** Install [Docker Desktop](https://www.docker.com/products/docker-desktop/).
    - *Windows Tip:* Ensure **WSL2** is enabled and Docker is configured to use the WSL2 backend.
    - *Mac Tip:* If you have an M1/M2/M3 chip, Docker Desktop for Apple Silicon is required.
- **Linux:** Install `docker-ce` and `docker-compose-plugin` via your package manager.
    - *Linux Tip:* Run `sudo usermod -aG docker $USER` and restart your session to run docker without `sudo`.

### ☸️ Kubernetes (Minikube)
We will use Minikube as our local cluster for Day 2.
1. **Install kubectl:** The Kubernetes command-line tool. ([Instructions](https://kubernetes.io/docs/tasks/tools/))
2. **Install Minikube:** [Download here](https://minikube.sigs.k8s.io/docs/start/).
    - We recommend using the **docker driver** for consistency.

### 📦 Optional but Recommended
- **Helm:** Kubernetes package manager. ([Install](https://helm.sh/docs/intro/install/))
- **VS Code Extensions:**
    - `Docker` (by Microsoft)
    - `Kubernetes` (by Microsoft)
    - `YAML` (by Red Hat)

---

## 2. Pre-pulling Docker Images
To save time and bandwidth during the training, please pull these images onto your machine in advance.

Run the following command in your terminal:

```bash
# Day 1 Essentials
docker pull hello-world
docker pull alpine:latest
docker pull ubuntu:latest
docker pull nginx:latest
docker pull busybox:latest

# Application Runtimes
docker pull node:20-alpine
docker pull eclipse-temurin:21-jdk-alpine
docker pull eclipse-temurin:21-jre-alpine

# Databases & Infrastructure
docker pull postgres:16-alpine
docker pull redis:7-alpine
```

---

## 3. Environment Check
Run these commands to verify your setup:

```bash
# Check Docker
docker --version
docker run --rm hello-world

# Check Kubernetes (Minikube)
minikube version
minikube start --driver=docker
kubectl get nodes
minikube stop

# Check Tools
kubectl version --client
helm version
```

---

## 🚀 Troubleshooting
- **Network Issues:** If you are behind a corporate proxy, ensure Docker Desktop is configured with your proxy settings.
- **Virtualization:** Ensure Virtualization is enabled in your BIOS/UEFI settings (common for Windows/Linux users).
- **Disk Space:** Ensure you have at least **10GB** of free space for images and the Minikube virtual disk.

> [!IMPORTANT]
> If `minikube start` fails, try `minikube delete` and then start again. If you're on a Mac with Apple Silicon, the `--driver=docker` is usually the most stable choice.
