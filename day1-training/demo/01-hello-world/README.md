# Step 01: Docker Hello World

This is the simplest possible test to verify that Docker is installed correctly and can pull images from Docker Hub.

## Concept
- **Image**: A read-only template (like a snapshot or a class in OOP).
- **Container**: A live, running instance of an image (like an object in OOP).

## Commands to Run

1. **Run the hello-world container**:
   ```bash
   docker run hello-world
   ```

## Detailed Breakdown

When you run `docker run hello-world`, several things happen behind the scenes:

### 1. Terminal Output Explanation
If this is your first time running it, you'll see:
- `Unable to find image 'hello-world:latest' locally`: Docker checked your computer first but didn't find the "blueprint".
- `latest: Pulling from library/hello-world`: Docker went to **Docker Hub** (the public registry) to download the image.
- `Digest: sha256:...`: A unique fingerprint to ensure the image hasn't been tampered with.
- `Status: Downloaded newer image...`: The image is now stored on your disk for future use.

### 2. The Container Lifecycle
The `docker run` command actually combines two actions:
1. **Create**: Docker takes the image and creates a writable layer on top of it.
2. **Start**: Docker starts the main process inside that container.

In this specific case, the `hello-world` process prints the message and then **immediately exits**.

## Post-Run Verification
Even though the container "finished," it left traces. Use these commands to see what happened:

1. **Check your images**:
   ```bash
   docker images
   ```
   *You should see `hello-world` listed here.*

2. **Check all containers (including stopped ones)**:
   ```bash
   docker ps -a
   ```
   *You'll see a container with a random name (like `happy_einstein`) and status `Exited (0)`.*

3. **Inspect the container metadata**:
   ```bash
   docker inspect <CONTAINER_ID_OR_NAME>
   ```

## Bridging the Gap
In a real-world scenario (like a web server):
- You wouldn't use `hello-world`. You'd use `nginx`, `node`, or `python`.
- You would add `-d` (detached mode) to keep it running in the background.
- You would add `-p 80:80` to map your computer's port to the container's port.

**Example of a "Real" run:**
```bash
docker run -d -p 8080:80 nginx
```
