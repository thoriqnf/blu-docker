# Step 10: K8s Basics (Pods & Deployments)

Kubernetes orchestrates containers at scale. Instead of managing individual containers, we define our **Desired State** in a manifest and K8s makes it happen.

## Key Concepts
- **Pod**: The smallest unit in K8s (usually contains 1 container).
- **Deployment**: Manages Pods. Handles replicas, self-healing, and rolling updates.
- **Namespace**: A virtual cluster inside your K8s cluster (to keep projects separate).

## Commands to Run

1. **Create the Namespace**:
   ```bash
   kubectl apply -f namespace.yaml
   ```

2. **Deploy the application**:
   ```bash
   kubectl apply -f deployment.yaml
   ```

3. **Check the status**:
   ```bash
   kubectl get pods -n training
   ```

4. **Test Self-Healing**:
   Delete one of the pods manually and watch K8s recreate it immediately!
   ```bash
   kubectl delete pod <pod_name> -n training
   ```
