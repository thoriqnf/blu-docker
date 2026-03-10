# Step 03: Simple Dockerfile

In this step, we build our own image containing a simple Node.js application.

## Core Dockerfile Instructions
- `FROM`: Sets the base image (the starting point).
- `WORKDIR`: Sets the internal directory for subsequent commands.
- `COPY`: Moves files from the host (your laptop) to the image.
- `CMD`: The default command to run when starting the container.

## Commands to Run

1. **Build the image**:
   ```bash
   docker build -t simple-node-app .
   ```

2. **Run the container**:
   ```bash
   docker run -p 3000:3000 simple-node-app
   ```

3. **Verify**:
   - `curl http://localhost:3000`
