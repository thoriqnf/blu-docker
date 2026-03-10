# Step 08: Docker Compose Basic (App + DB)

Docker Compose is the "Conductor" of the Docker world. It allows you to manage multiple containers together as a single application.

## The "Conductor" Analogy
Think of your containers as individual musicians (Drums, Guitar, Piano).
- **Without Compose**: You have to tell each musician when to start, what to play, and where to sit (this is what we did in Step 05 and 07).
- **With Compose**: You provide a "Sheet Music" (`docker-compose.yml`) and the Conductor makes sure everyone plays together perfectly with one command.

## Why Use Compose?

### 1. From 10 Commands to 1
Instead of manually creating networks, starting the database with environment variables, and then starting the app, Compose does it all for you. 
It replaces:
- `docker network create ...`
- `docker run -d --name db ...`
- `docker run -d --name app ...`

With just: `docker compose up -d`

### 2. Automatic Service Discovery
In the `docker-compose.yml`, notice the service names `api` and `db`. 
Compose automatically creates a network and sets up DNS so the `api` can find the database simply by using the name `db`.

## Commands to Run

1. **Start the stack**:
   This builds the image and starts both containers in the background.
   ```bash
   docker compose up -d
   ```

2. **Verify the status**:
   ```bash
   docker compose ps
   ```

3. **Check logs**:
   Follow the logs of just the API to see it connecting to the DB.
   ```bash
   docker compose logs -f api
   ```

4. **Stop and Clean up**:
   This stops the containers AND removes the internal network.
   ```bash
   docker compose down
   ```

## Key Concept: `up` vs `start`
- **`up`**: Create/Update and start containers (The "Magic Button").
- **`down`**: Stop and remove containers/networks.
