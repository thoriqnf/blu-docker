# Step 09: Docker Compose Advanced (Health & Limits)

In production, just starting a container isn't enough. We need to ensure it's healthy and doesn't consume all our server's resources.

## Advanced Features
1. **Healthchecks**: Wait for the database to be *ready*, not just *started*.
2. **Resource Limits**: Limit CPU and RAM to prevent "leaky" containers from crashing the host.
3. **Restart Policy**: Automatically restart if the app crashes.

## Commands to Run

1. **Start the stack**:
   ```bash
   docker compose up -d
   ```

2. **Check health status**:
   ```bash
   docker ps
   ```
   *Notice the "(healthy)" status for the database.*

3. **Verify resource limits**:
   ```bash
   docker stats
   ```
   *Notice the memory limit is exactly 512MB.*
