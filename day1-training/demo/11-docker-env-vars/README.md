# Step 11: Docker Compose Environment Variables

Never hardcode database URLs or passwords in your images! Docker Compose gives you two main ways to inject configuration: `environment` and `env_file`.

## Commands to Run

1. **Start the stack in the background:**
   ```bash
   docker compose up -d
   ```

2. **Verify the Environment Variables:**
   Let's see what environment variables were actually passed into the container:
   ```bash
   # Check the web server (uses the environment: block in yaml)
   docker compose exec web printenv | grep APP_

   # Check the worker (uses the .env file)
   docker compose exec worker printenv | grep DB_
   ```

3. **Clean up:**
   ```bash
   docker compose down
   ```
