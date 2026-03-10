# Step 05: Multi-Stage Build (Java)

This step demonstrates how to build a production-ready, minimal Docker image using Multi-Stage Builds.

## Why Multi-Stage?
A standard Java build environment (JDK) is huge (>400MB). However, we only need a lightweight runtime (JRE) to run the application code (<100MB). 
Multi-stage builds allow us to build in one container and ship only the binary in another.

## Commands to Run

1. **Build the image**:
   ```bash
   cd todo-app
   docker build -t multi-stage-todo .
   ```

2. **Check the image size**:
   ```bash
   docker images | grep multi-stage-todo
   ```
   *Compare the result to standard build methods which keep the source code and build tools.*

3. **Verify security**:
   Try running `/bin/bash` or reaching for `mvn`. They won't exist in the final production image!
