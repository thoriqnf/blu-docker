# 🚨 INCIDENT RESPONSE: The Broken E-Commerce App

## Time: 60 minutes

## Scenario
You are the on-call DevOps engineer. At 2:00 AM, PagerDuty goes off.
The staging environment for the company's new E-Commerce website is completely down.
The developers swear "it works on my machine", but it's failing in the Minikube staging cluster.

Your job is to investigate, find the root causes, and edit the YAML files to fix them.

## Setup
First, deploy the broken application to your cluster. Ensure Minikube is running with the Docker driver.

```bash
minikube start --driver=docker
cd broken-app
kubectl apply -f .
```

---

## 🕵️‍♂️ Your Tasks

### Task 1: Fix the Pods (Deployment)
The application won't even start.
1. Run `kubectl get pods`. What state are they in?
2. Use `kubectl describe pod <pod-name>` or `kubectl logs <pod-name>` to figure out why.
3. Make the necessary fix in `broken-app/deployment.yaml`.
4. Run `kubectl apply -f broken-app/deployment.yaml` again until the pods are `Running`.

### Task 2: Fix the Internal Network (Service)
The pods are running, but other internal services can't reach them!
1. Check the service: `kubectl get svc my-store-service`
2. Describe it: `kubectl describe svc my-store-service`. Notice anything weird about the `Endpoints`?
3. Compare the Service's `selector` with the Deployment's pod `labels`.
4. Fix the mismatch in `broken-app/service.yaml` and apply.

### Task 3: Fix the External Routing (Ingress)
Internal network works, but external customers still get a generic 404 or 502 error on `store.local`.
1. Ensure the Ingress controller is enabled in Minikube: `minikube addons enable ingress`
2. Look at `broken-app/ingress.yaml`.
3. Find the two routing mistakes:
   - Is it pointing to the right Service name?
   - Is it pointing to the correct Service port? (Check `service.yaml` for the port number!)
4. Fix `ingress.yaml` and apply.
5. In a new terminal run: `minikube tunnel` (or add to `/etc/hosts`).
6. Test with: `curl -H "Host: store.local" http://localhost`

### Task 4: The Recovery (Bonus - GitOps)
Now that you fixed the staging cluster manually, let's automate it for production.
1. Install ArgoCD (follow instructions from Demo 4).
2. Create an ArgoCD Application that points to your fixed code.
3. Delete the deployment manually via `kubectl` and watch it "Self-Heal"!

---

## 🎯 Success Criteria
- [ ] `minikube status` confirms cluster is healthy.
- [ ] `kubectl get pods` shows 2 pods in `Running` state.
- [ ] `kubectl describe svc my-store-service` shows actual Pod IPs under `Endpoints`.
- [ ] The Ingress correctly routes `store.local` to the `my-store-service` on port 80.
- [ ] **Bonus**: ArgoCD is installed and managing the application status.

## 💡 Hints
- Pods are crashing because they don't have the right environment variable to connect to their (mock) database. It expects `DB_PASSWORD` but the YAML has a typo!
- Service selectors are case-sensitive and must match Pod labels exactly.
- Ingress `service.port.number` must match the `port:` declared in the Service YAML (not the `targetPort`!).

## 🆘 Need help?
If you're completely stuck, you can peek at the `/solution` folder. But try relying purely on `kubectl` commands first!
