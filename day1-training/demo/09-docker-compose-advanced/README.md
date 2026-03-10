# Step 09: Docker Compose Advanced (Production Readiness)

In production, just starting a container isn't enough. We need to ensure it's actually **ready** to handle traffic and that it doesn't crash the entire server by using too many resources.

## Advanced Features

### 1. Healthchecks: "Ready" vs. "Started"
Normally, `depends_on` only waits for a container to **start**. But a database like PostgreSQL might take 5-10 seconds to actually be **ready** to accept connections.
- **The Problem**: Your app starts, tries to connect immediately, and crashes because the DB is still booting up.
- **The Solution**: We use a `healthcheck` (running `pg_isready`). The App will now wait until the DB is officially "Healthy" before it even tries to start.

### 2. Guard Rails: Resource Limits
Without limits, a single "leaky" container could eat all the RAM on your server, crashing every other app.
- **Limits**: We set a hard cap (e.g., 512MB RAM). Docker will not let the container go above this.
- **Reservations**: We tell Docker to "reserve" a certain amount of power for this specific app.

### 3. Self-Healing: Restart Policies
If your application crashes due to a temporary bug or low memory, `restart: always` tells Docker to pull it back up immediately. This is the first line of defense for a 24/7 service.

## Commands to Run

1. **Start the stack**:
   ```bash
   docker compose up -d
   ```

2. **Watch the "Health" status**:
   ```bash
   # Run this repeatedly for 10 seconds
   docker compose ps
   ```
   *Notice how the status changes from `starting` to `healthy`.*

3. **Check the Guard Rails**:
   ```bash
   docker stats
   ```
   *Verify that the `MEM LIMIT` is exactly what we defined in the `docker-compose.yml`.*

## Cleanup
```bash
docker compose down
```
