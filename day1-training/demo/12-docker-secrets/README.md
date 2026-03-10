# Step 12: Docker Secrets

Environment variables are great for configuration, but they are NOT secure enough for TLS certificates or sensitive passwords (anyone can run `printenv`). Docker Secrets mount sensitive data as read-only files in memory, never exposing them in the environment.

## Commands to Run

1. **Start the Database:**
   ```bash
   docker compose up -d
   ```

2. **Where is the secret?**
   It's NOT an environment variable! Check it:
   ```bash
   docker compose exec db printenv | grep PASSWORD
   ```
   *(No output!)*

3. **Read the secret file:**
   The secret exists only as an in-memory file at `/run/secrets/`. Let's read it:
   ```bash
   docker compose exec db cat /run/secrets/db_password
   ```

4. **Clean up:**
   ```bash
   docker compose down
   ```
