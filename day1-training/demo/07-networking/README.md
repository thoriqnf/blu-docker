# Step 07: Docker Networking & DNS

By default, Docker containers are isolated. We use **Networks** to allow them to talk to each other securely and easily.

## The "Neighborhood" Analogy
Think of a Docker Network as a private, gated neighborhood:
- **Isolation**: People outside the neighborhood can't see who is inside.
- **Service Discovery**: Everyone in the neighborhood knows each other by **name**, not just house number.

## Key Networking Concepts

### 1. The "DNS Magic"
In a custom network, Docker provides a built-in DNS server. This means:
- You don't need to know that `my-server` has the IP `172.18.0.2`.
- You can just use the name `my-server` in your code/commands.

### 2. Custom Bridge vs. Default Bridge
| Feature | Default Bridge | Custom Network (Recommended) |
| :--- | :--- | :--- |
| **Isolation** | Shared by all containers | Private to your project |
| **DNS Discovery** | ❌ None (must use IPs) | ✅ Automatic (use names!) |

## Commands to Run

### 1. Create your "Neighborhood"
```bash
docker network create my-custom-network
```

### 2. Connect the "Neighbors"
We will run a server and then use a client to "shout" at it by name.

```bash
# Start the Server
docker run -d --name my-server --network my-custom-network nginx

# Start the Client and PING by name
docker run --rm --network my-custom-network alpine ping -c 3 my-server
```
*Notice: `ping my-server` works perfectly because of Docker's built-in DNS!*

## Cleanup
```bash
docker stop my-server
docker network rm my-custom-network
```
