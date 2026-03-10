# Step 11: K8s Networking (Services)

A Deployment creates Pods, but their IP addresses are temporary and change constantly. A **Service** provides a stable IP and DNS name to access those pods.

## Types of Services
- **ClusterIP**: Internal only (default).
- **NodePort**: Accessible from outside the cluster via a physical port (30000-32767).
- **LoadBalancer**: Standard for Cloud providers (AWS, GCP).

## Commands to Run

1. **Expose the deployment**:
   ```bash
   kubectl apply -f service.yaml
   ```

2. **Find the service details**:
   ```bash
   kubectl get svc -n training
   ```

3. **Access the application**:
   - If using Minikube: `minikube service todo-app-service -n training`
   - Otherwise, find your Node IP and browse to the NodePort (e.g., `http://<node_ip>:30080`).
