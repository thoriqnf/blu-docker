# Step 04: Dockerfile Optimization

In this step, we learn how to make our builds "smarter" and faster using **Layer Caching** and `.dockerignore`.

## Why Optimization Matters?
In the previous step, every time you changed a single line of code, Docker had to rebuild everything. In a real project with 500MB of dependencies, that's a lot of wasted time.

## Key Concept: Layer Caching

Docker images are built like a stack of pancakes (layers). 
- If a layer hasn't changed, Docker reuses the "cached" version from your disk.
- If a layer changes (e.g., your code changes), Docker throws away that layer **and every layer above it**.

### The Rule: "Order Matters"
Look at the `Dockerfile`:
1. We **COPY package.json** first.
2. We **RUN install** (simulated).
3. We **COPY app.js** (the actual code) last.

**Why?** Because your source code changes 100 times a day, but your dependencies (package.json) might only change once a week. By putting the code at the bottom, we ensure that changing code doesn't force a re-install of all dependencies.

## Commands to Run

1. **Build the image (First time)**:
   ```bash
   docker build -t optimized-node-app .
   ```
   *Notice the "Installing dependencies..." step takes about 2 seconds.*

2. **Modify your code**:
   ```bash
   echo "console.log('Modified!');" >> app.js
   ```

3. **Build again**:
   ```bash
   docker build -t optimized-node-app .
   ```
   *Look at the output! You'll see `CACHED` next to the dependency steps. The build is now near-instant.*

## The `.dockerignore` File
Just like `.gitignore`, this tells Docker which files **NOT** to send to the build daemon.
- **Why?** It keeps your images small and prevents sensitive files (like `.env` or `.git`) from being accidentally baked into your image.
- **Try it**: Check the `.dockerignore` file in this folder to see what's being excluded.

## Cleanup
```bash
docker rmi optimized-node-app
```
