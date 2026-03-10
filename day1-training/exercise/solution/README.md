# Exercise Solution: Level 5

This folder contains the "Gold Standard" solution for the Notes API exercise. It implements all 12 concepts covered in the Day 1 training.

## Key Features
- **Level 2**: Multi-stage build (Maven build -> JRE run).
- **Level 3**: Isolated bridge network and persistent PostgreSQL volume.
- **Level 4**: Healthcheck-aware orchestration and resource limits (512MB RAM).
- **Level 5**: Secure password management using **Docker Secrets**.

## How to Run

### 1. Prepare Secrets
Because secrets are read from files, ensure you have a `db_password.txt` in the root of the exercise folder (one level up):
```bash
echo "mysupersecretpassword" > ../db_password.txt
```

### 2. Launch the Stack
```bash
docker compose up -d
```

### 3. Verify Levels
- **Check Health**: `docker compose ps` (Wait until `app` is `healthy`).
- **Check Security**: `docker compose exec app printenv | grep PASSWORD` (Should be empty).
- **Check Limits**: `docker stats` (Verify the 512MB limit).
- **Check API**: `curl http://localhost:8081/api/notes`.

## Cleanup
```bash
docker compose down -v
```
