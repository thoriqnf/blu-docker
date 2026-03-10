# Step 06: Persistence (Volumes & Bind Mounts)

In this step, we learn how to keep data safe even after a container is destroyed.

## Types of Mounts
1. **Bind Mount**: Map a folder on your computer directly to the container (best for live coding).
2. **Named Volume**: Managed by Docker (best for databases).

## Commands to Run

### 1. Bind Mount (Development)
We will map our current folder so that changes to `index.html` on our Macbook reflect immediately in Nginx.

```bash
docker run -d -p 8081:80 -v $(pwd):/usr/share/nginx/html --name dev-server nginx
```
*Now, modify the `index.html` in this folder and refresh [http://localhost:8081](http://localhost:8081).*

### 2. Named Volume (Persistence)
We will create a volume that survives container deletion.

```bash
# Create a volume
docker volume create my-data

# Run a container with the volume
docker run -d --name data-container -v my-data:/data alpine sleep 1000

# Write some data
docker exec data-container sh -c "echo 'Persisted data' > /data/myfile.txt"

# Remove the container
docker stop data-container && docker rm data-container

# RUN A NEW CONTAINER - your data is still there!
docker run --rm -v my-data:/data alpine cat /data/myfile.txt
```
