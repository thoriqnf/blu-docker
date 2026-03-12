w#!/bin/bash

# Exit on any error
set -e

echo "======================================"
echo "🚀 Setting up 3-Tier App Demo Images  "
echo "======================================"

echo "[1/4] Ensuring Minikube is running..."
if ! minikube status > /dev/null 2>&1; then
    echo "Minikube is not running. Starting minikube..."
    minikube start --driver=docker
fi

echo "[2/3] Setting Docker environment to Minikube..."
eval $(minikube docker-env)

echo "[3/3] Building Images inside Minikube..."
docker build -t demo-backend:v1 ./app/backend
docker build -t demo-frontend:v1 ./app/frontend

echo "======================================"
echo "✅ Setup Complete!"
echo "You can now run 'kubectl apply -f k8s/'"
echo "======================================"
