# 🐞 Demo: Debugging Kubernetes

This demo walks through common Kubernetes pod failures and how to troubleshoot them using `kubectl`.

## Prerequisites
Ensure Minikube is running:
```bash
minikube start --driver=docker
```

## Scenario 1: ImagePullBackOff

1. **Deploy the broken pod**:
   ```bash
   kubectl apply -f 01-imagepullbackoff.yaml
   ```

2. **Observe the failure**:
   ```bash
   kubectl get pods
   ```
   *Notice the status is `ErrImagePull` or `ImagePullBackOff`.*

3. **Investigate**:
   ```bash
   kubectl describe pod bad-image-pod
   ```
   *Scroll down to the `Events` section. You will see an error indicating the image could not be found. Check the YAML: the image name has a typo (`nginxx` instead of `nginx`).*

4. **Cleanup**:
   ```bash
   kubectl delete -f 01-imagepullbackoff.yaml
   ```

## Scenario 2: CrashLoopBackOff

1. **Deploy the broken pod**:
   ```bash
   kubectl apply -f 02-crashloopbackoff.yaml
   ```

2. **Observe the failure**:
   ```bash
   kubectl get pods -w
   ```
   *Watch the pod cycle through `ContainerCreating` -> `Error` -> `CrashLoopBackOff`.*

3. **Investigate the logs**:
   ```bash
   kubectl logs crash-pod
   ```
   *The application logs will reveal the fatal error causing the crash (e.g., missing environment variable, database connection refused, or exiting immediately).*

4. **Detailed inspection** (If logs are empty or pod is OOMKilled):
   ```bash
   kubectl describe pod crash-pod
   ```

5. **Cleanup**:
   ```bash
   kubectl delete -f 02-crashloopbackoff.yaml
   ```

## Scenario 3: Pending PVC

1. **Deploy the broken pod and PVC**:
   ```bash
   kubectl apply -f 03-pending-pvc.yaml
   ```

2. **Observe the failure**:
   ```bash
   kubectl get pods
   kubectl get pvc
   ```
   *The pod remains in `Pending` state. The PVC also stays `Pending`.*

3. **Investigate**:
   ```bash
   kubectl describe pod pending-pod
   kubectl describe pvc my-missing-pvc
   ```
   *The events will show that the pod cannot be scheduled because it requires a PersistentVolumeClaim that hasn't been satisfied (perhaps requesting a StorageClass that doesn't exist in Minikube).*

4. **Cleanup**:
   ```bash
   kubectl delete -f 03-pending-pvc.yaml
   ```
