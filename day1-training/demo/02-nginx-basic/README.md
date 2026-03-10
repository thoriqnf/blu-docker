# Step 02: Nginx Basic (CLI Flags)

In this step, we demonstrate how to run a web server in the background and map ports so we can access it from our host machine.

## Key Flags
- `-d`: **Detached** mode (runs the container in the background).
- `-p`: **Publish** port (`host_port:container_port`).
- `--name`: Assign a custom **name** to the container.
- `--rm`: **Remove** the container automatically when it stops.

## Commands to Run

1. **Run Nginx in detached mode with port mapping**:
   ```bash
   docker run -d -p 8080:80 --name my-web-server nginx
   ```

2. **Verify it's running**:
   - Open your browser at [http://localhost:8080](http://localhost:8080)
   - Or use curl: `curl http://localhost:8080`

3. **Check container status**:
   ```bash
   docker ps
   ```

4. **Stop and remove the container**:
   ```bash
   docker stop my-web-server
   docker rm my-web-server
   ```
