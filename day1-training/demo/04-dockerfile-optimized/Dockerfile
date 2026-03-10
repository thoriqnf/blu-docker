# 1. Base Image
FROM node:18-alpine

# 2. Set Working Directory
WORKDIR /app

# 3. Optimized Layer Caching
# We copy package.json first and run install.
# This way, if only our source code changes, Docker will skip 'npm install'.
COPY package.json .
RUN echo "Installing dependencies..." && sleep 2 # Simulating npm install

# 4. Copy Application Code (this changes frequently)
COPY app.js .

# 5. Command to run
CMD ["node", "app.js"]
