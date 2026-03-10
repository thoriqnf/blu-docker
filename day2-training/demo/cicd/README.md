# 🚀 Demo: CI/CD Pipelines with K8s

This folder contains example configuration files for traditional CI/CD platforms deploying to Kubernetes.

These are **reference files**—you do not run them with `kubectl apply`. Instead, you would normally place these in your repository's root directory so that GitHub or GitLab can execute them automatically when code is pushed.

## GitHub Actions (`.github/workflows/`)

GitHub Actions workflows are YAML files stored in `.github/workflows/`. They define automated processes for CI (building/testing) and CD (deploying).

Examine the provided workflow file. You'll notice the key steps involved in a "Push" deployment model:
1. **Checkout Code**: Pulls the source code.
2. **Setup Tools**: Installs Docker, AWS CLI/GCP Cloud SDK, or `kubectl`.
3. **Build & Push Image**: Builds the Dockerfile and pushes it to a registry (Docker Hub, ECR, GCR) with a tag (usually the Git SHA).
4. **Deploy to K8s**: Authenticates against the external Kubernetes API and runs `kubectl set image` or `kubectl apply`.

*Security Note: This model requires providing highly privileged Kubernetes credentials to the external CI runner.*

## GitLab CI (`.gitlab-ci.yml`)

GitLab CI uses a single `.gitlab-ci.yml` file in the root of the repository to define jobs and stages (e.g., `build`, `test`, `deploy`).

Similar to GitHub Actions, observe the stages:
- **Build Stage**: Uses Docker-in-Docker (`dind`) or tools like Kaniko to build the container image and push it to the GitLab Container Registry.
- **Deploy Stage**: Uses a lightweight Alpine image with `kubectl` installed to update the deployment manifesto on the target cluster.

## Connecting with the "Pull" Model (GitOps)

As discussed in the slides, modern K8s deployments avoid granting CI tools direct access to the cluster.

If we were using GitOps (e.g., ArgoCD):
1. The CI pipeline (GitHub/GitLab) stops after the `Build & Push Image` step.
2. An additional step updates a separate "Manifest Repository" by bumping the image tag in a `values.yaml` or `deployment.yaml` file.
3. ArgoCD (running *inside* the cluster) detects the Git commit and pulls the changes, applying them internally.
