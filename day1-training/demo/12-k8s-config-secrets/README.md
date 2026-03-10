# Step 12: ConfigMaps & Secrets

Applications need configuration. We should never hardcode database hosts or passwords in our images.

## Core Concepts
- **ConfigMap**: For non-sensitive data (e.g., `DB_HOST`, `APP_ENV`).
- **Secret**: For sensitive data (e.g., `DB_PASSWORD`). Encoded in base64.

## Commands to Run

1. **Create the configuration**:
   ```bash
   kubectl apply -f configmap.yaml
   kubectl apply -f secret.yaml
   ```

2. **Inject into your Deployment**:
   Normally, you would modify your `deployment.yaml` to reference these via `envFrom` or `valueFrom`.

3. **Verify the values inside the Pod**:
   ```bash
   kubectl exec -it <pod_name> -n training -- printenv | grep DB_
   ```
