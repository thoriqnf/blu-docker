# Kubernetes (K8s) — Everything You Need to Know

Kubernetes is an open-source container orchestration platform that automates deploying, scaling, and managing containerized applications.

## Architecture
Kubernetes follows a **control plane + worker nodes** model.

### Control Plane components:
| Component | Role |
|-----------|------|
| **API Server** | The front door — everything talks to it via REST |
| **etcd** | Distributed key-value store — the cluster's source of truth |
| **Scheduler** | Decides which node a Pod runs on |
| **Controller Manager** | Runs control loops (desired state → actual state) |

### Node components:
| Component | Role |
|-----------|------|
| **kubelet** | Agent on each node, ensures containers are running |
| **kube-proxy** | Handles network rules and routing |
| **Container Runtime** | Actually runs containers (containerd, CRI-O) |

## Core Objects
- **Pod**: Smallest deployable unit. Wraps one or more containers.
- **Deployment**: Manages a ReplicaSet of Pods. Handles updates and scaling.
- **Service**: Stable network endpoint for Pods (ClusterIP, NodePort, LoadBalancer).
- **ConfigMap & Secret**: Decouple config from container images.
- **Ingress**: HTTP/HTTPS routing into the cluster.
- **PV & PVC**: Storage that survives Pod restarts.

## Workload Types
| Kind | Use Case |
|------|----------|
| **Deployment** | Stateless apps (web servers, APIs) |
| **StatefulSet** | Stateful apps (databases, Kafka) |
| **DaemonSet** | Run one Pod per node (logs, monitoring) |
| **Job / CronJob** | One-off or scheduled tasks |

## Essential kubectl Commands
```bash
# Resources
kubectl get pods,svc,deployments -n <ns>
kubectl describe pod <pod-name>
kubectl logs <pod-name> -f
kubectl exec -it <pod-name> -- bash

# Apply/Delete
kubectl apply -f manifest.yaml
kubectl delete -f manifest.yaml
```

## Advanced Concepts
- **Probes**: Liveness (restart), Readiness (traffic), Startup (slow boot).
- **Resources**: Requests (guaranteed) vs Limits (ceiling).
- **Scheduling**: NodeSelector, Affinity/Anti-Affinity, Taints & Tolerations.
- **RBAC**: Roles, ClusterRoles, RoleBindings, ClusterRoleBindings.
- **GitOps**: Git as the single source of truth for cluster state.
