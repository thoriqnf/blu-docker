# Step 03: Simple Dockerfile

In this step, we build our own image containing a simple Node.js application. This moves us from "using other people's images" to "creating our own."

## Core Instructions

- `FROM`: Sets the base image (the foundation).
- `WORKDIR`: Sets the working directory *inside* the container.
- `COPY`: Moves files from your laptop to the image.
- `CMD`: The default command to run when the container starts.

## Commands to Run

1. **Build the image**:
   ```bash
   docker build -t simple-node-app .
   ```
   *Note: The `.` at the end tells Docker to look in the **current directory** for the Dockerfile and files to copy.*

2. **Run the container**:
   ```bash
   docker run -p 3000:3000 --name my-node-app simple-node-app
   ```

3. **Verify**:
   Access [http://localhost:3000](http://localhost:3000) or use `curl http://localhost:3000`.

## Key Concepts

### Build vs. Run
- **`docker build`**: Creates a new **Image** (blueprint) from your source code. You only do this when your code changes.
- **`docker run`**: Starts a **Container** (instance) from that image.

### File Copying
The `COPY . .` command in the Dockerfile is what actually puts your `app.js` inside the image. This is how your code "travels" from your computer into the container world.

## Cleanup
```bash
docker stop my-node-app
docker rm my-node-app
```
