#!/bin/bash

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

echo "[2/4] Building Backend Image locally..."
cd app/backend
docker build -t demo-backend:v1 .
cd ../..

echo "[3/4] Building Frontend Image locally..."
cd app/frontend
docker build -t demo-frontend:v1 .
cd ../..

echo "[4/4] Loading Images into Minikube (this may take a minute)..."
minikube image load demo-backend:v1
minikube image load demo-frontend:v1

echo "======================================"
echo "✅ Setup Complete!"
echo "You can now run 'kubectl apply -f k8s/'"
echo "======================================"
