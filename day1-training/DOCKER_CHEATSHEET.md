# 🐳 Docker Cheat Sheet (Day 1)

## 🏁 Core CLI Commands

### Basic Operations
- `docker run <image>` — Run a container from an image.
- `docker run -d <image>` — Run in **detached** mode (background).
- `docker run -p 8080:80 <image>` — Map **host port** to **container port**.
- `docker ps` — List running containers.
- `docker ps -a` — List **all** containers (including stopped).
- `docker stop <id/name>` — Stop a running container.
- `docker rm <id/name>` — Remove a container.

### Debugging & Inspection
- `docker logs -f <id/name>` — **Follow** live logs.
- `docker exec -it <id/name> sh` — Open an **interactive shell** inside a container.
- `docker inspect <id/name>` — View detailed configuration (JSON).
- `docker stats` — Show real-time **resource usage** (CPU/RAM).

---

## 🏗️ Dockerfile Reference

| Instruction | Purpose |
|-------------|---------|
| `FROM` | Base image (e.g., `node:18-alpine`) |
| `WORKDIR` | Set internal working directory |
| `COPY` | Move files from host to image |
| `RUN` | Execute command during build (creates a layer) |
| `ENV` | Set environment variables |
| `EXPOSE` | Document the port application uses |
| `CMD` | Default command when container starts (overridable) |
| `ENTRYPOINT` | Main command (harder to override) |

---

## 🛠️ Docker Compose

- `docker compose up -d` — Start all services in the background.
- `docker compose down` — Stop and remove all services.
- `docker compose down -v` — Stop and remove **volumes** too.
- `docker compose logs -f <service>` — Follow logs for a specific service.
- `docker compose logs -f` — Follow logs for **all** services.

---

## 🚀 Optimization & Best Practices

1. **Layer Caching**: Put items that change rarely (e.g., `package.json`) at the TOP.
2. **.dockerignore**: Exclude `node_modules`, `.git`, and large binaries.
3. **Security**: Always run as a **non-root user** using `USER appuser`.
4. **Multi-Stage Build**: Build in one image, ship the binary in another (Stage 2).
5. **Memory Limits**: Always set limits in Compose:
   ```yaml
   deploy:
     resources:
       limits:
         memory: 512M
   ```
