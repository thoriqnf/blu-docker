# Step 08: Docker Compose Basic (App + DB)

Docker Compose is the "Magic Button" for developers. It allows you to start multiple containers with a single command.

## Key Concepts
- **Services**: The different containers in your application (e.g., `api`, `db`).
- **Networking**: Compose automatically creates a shared network for your services.
- **Environment Variables**: Best way to pass configuration to your containers.

## Commands to Run

1. **Start the stack**:
   ```bash
   docker compose up -d
   ```

2. **Verify it's working**:
   - `curl http://localhost:8080` (The API)
   - `docker ps` (Check both containers)

3. **Check logs**:
   ```bash
   docker compose logs -f api
   ```

4. **Stop the stack**:
   ```bash
   docker compose down
   ```
