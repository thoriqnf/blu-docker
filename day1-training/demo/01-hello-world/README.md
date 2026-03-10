# Step 01: Docker Hello World

This is the simplest possible test to verify that Docker is installed correctly and can pull images from Docker Hub.

## Concept
- **Image**: A read-only template (like a snapshot).
- **Container**: A live, running instance of an image.

## Commands to Run

1. **Run the hello-world container**:
   ```bash
   docker run hello-world
   ```

## What happens?
1. The Docker client contacts the Docker daemon.
2. The Docker daemon pulls the "hello-world" image from Docker Hub (if not already local).
3. The Docker daemon creates a new container from that image which runs the executable that produces the output you see.
4. The Docker daemon streams that output to the Docker client, which sends it to your terminal.
