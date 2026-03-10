# Step 02: Nginx Basic (CLI Flags)

In this step, we demonstrate how to run a web server in the background and map ports so we can access it from our host machine.

## Key Flags
- `-d`: **Detached** mode (runs the container in the background).
- `-p`: **Publish** port (`host:container`).
- `--name`: Assign a custom **name** to the container.
- `--rm`: **Remove** the container automatically when it is stopped.

## Commands to Run

1. **Run Nginx in detached mode**:
   ```bash
   docker run -d -p 8080:80 --name my-web-server nginx
   ```

## How it Works

### Port Mapping (`8080:80`)
Think of this as a tunnel from your machine to the container:
- **`8080` (Host)**: The port YOU use on your MacBook browser. 
- **`80` (Container)**: The port NGINX is listening on *inside* the container.
- **Why?** Containers are isolated by default. Without `-p`, you wouldn't be able to reach the web server.

### Detached vs. Foreground
- `hello-world` is a **Task**: It does one thing and finishes.
- `nginx` is a **Service**: It's meant to stay alive to handle requests.
- Use `-d` for services so you can keep using your terminal while they run.

### Verification & Interaction
- **View logs**: `docker logs my-web-server`
- **Interactive shell**: `docker exec -it my-web-server bash`

## Troubleshooting: Common Errors

### ❌ Error: "Conflict. The container name is already in use"
**What happened?** You tried to run the same command twice with the same `--name`.
**Why?** Docker names must be unique. Even if a container is "Stopped," the name is still reserved.
**The Fix:**
1. Stop the old one: `docker stop my-web-server`
2. Remove it: `docker rm my-web-server`
3. *Alternative:* Use `--rm` in your run command to auto-cleanup next time!

## Cleanup
```bash
docker stop my-web-server
docker rm my-web-server
```
