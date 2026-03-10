# Step 04: Dockerfile Optimization

This step demonstrates how Layer Caching works and how to use `.dockerignore`.

## Optimization Concept
Docker builds images layer by layer. If an instruction (like `COPY . .`) changes, all subsequent layers must be rebuilt.

**Strategy**: Put frequently changing files (source code) at the bottom, and stable files (dependencies) at the top.

## Commands to Run

1. **Build the image (First time)**:
   ```bash
   docker build -t optimized-node-app .
   ```
   *Notice the time it takes for "Installing dependencies".*

2. **Modify the code**:
   ```bash
   echo "console.log('Modified!');" >> app.js
   ```

3. **Build again**:
   ```bash
   docker build -t optimized-node-app .
   ```
   *Notice it is much faster now! Docker reused the cached "Installing dependencies" layer.*

4. **Verify `.dockerignore`**:
   Notice that `node_modules` or `.git` are NOT copied into the image.
