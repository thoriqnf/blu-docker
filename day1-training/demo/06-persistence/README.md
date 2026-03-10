# Step 06: Persistence (Volumes & Bind Mounts)

In this step, we learn how to keep data safe even after a container is destroyed. By default, anything written inside a container is **lost** when the container is removed.

## Two Ways to Save Data

### 1. Bind Mounts (Host -> Container)
This "maps" a folder on your Mac directly into the container.
- **Best for**: Live coding. You change a file on your laptop, and the container sees it instantly.
- **Try it**:
  ```bash
  docker run -d -p 8081:80 -v $(pwd):/usr/share/nginx/html --name dev-server nginx
  ```
  *Now, change `index.html` on your Mac and refresh [http://localhost:8081](http://localhost:8081). The change is instant!*

### 2. Named Volumes (Docker Managed)
This is a dedicated storage space managed by Docker.
- **Best for**: Databases (PostgreSQL, MySQL). It’s faster and more secure than bind mounts.
- **Evidence Flow**: Let's prove the data survives!

#### Stage A: Create Data
```bash
# 1. Create a volume
docker volume create my-bank-vault

# 2. Start a container and write a "Secret Message"
docker run --name temp-writer -v my-bank-vault:/data alpine sh -c "echo 'This is evidence that data lives on!' > /data/evidence.txt"
```

#### Stage B: Destroy Everything
```bash
# 3. Kill and remove the container completely
docker rm -f temp-writer
```
*Wait... where is the data? The container is gone!*

#### Stage C: The Evidence (The Reveal)
```bash
# 4. Start a COMPLETELY NEW container and read from the same volume
docker run --rm -v my-bank-vault:/data alpine cat /data/evidence.txt
```
**Result**: The message is still there. The volume outlives the container!

## Visual Mapping
```text
[ Container (Volatile) ]  <-- Can be deleted anytime
        |
        +--- [ Named Volume (Persistent) ]  <-- Lives on your disk safely
```

## Cleanup
Volumes don't disappear automatically. You must remove them explicitly.
```bash
docker stop dev-server
docker rm dev-server
docker volume rm my-bank-vault
```
