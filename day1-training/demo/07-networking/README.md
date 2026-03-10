# Step 07: Docker Networking Drivers

By default, Docker containers are isolated. We use "Drivers" to connect them.

## Key Networking Concepts
1. **Bridge (Default)**: Creates a private internal network. Perfect for most use cases (like App + DB).
2. **Host**: Removes isolation between container and host. High performance but messy.
3. **None**: No network access (use for batch jobs).

## Commands to Run

### 1. Create a custom network
Best practice is creating your own network for each project.
```bash
docker network create my-custom-network
```

### 2. Connect containers
Run a "server" container and a "client" container on the same network.
```bash
# Server
docker run -d --name my-server --network my-custom-network nginx

# Client (testing connection)
docker run --rm --network my-custom-network alpine ping -c 3 my-server
```
*Notice that we can ping `my-server` by its NAME instead of its IP! This is Docker's built-in DNS.*

### 3. Cleanup
```bash
docker stop my-server && docker network rm my-custom-network
```
