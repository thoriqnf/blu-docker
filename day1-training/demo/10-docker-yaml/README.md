# Step 10: The YAML Debugging Challenge 🧩

YAML is the heart of Docker Compose, but it's also the #1 cause of "it doesn't work" for beginners. It is extremely sensitive to spaces, tabs, and spelling.

In this demo, the `docker-compose.yml` is **horribly broken**. Your mission is to find and fix all 4 hidden errors.

## Your Diagnostic Tool: `docker compose config`
This is your best friend. It parses your YAML and tells you exactly what Docker "sees". If the file is invalid, it will tell you why.

## The Challenge: Find the 4 "Ghosts"

### 1. The Syntax Ghost (Indentation)
- **Run**: `docker compose config`
- **Error**: It should complain about `services.app.ports` mapping.
- **Fix**: YAML lists `-` must be properly indented under their parent. Fix the spacing for `ports`.

### 2. The Network Ghost (Undefined Resource)
- **Run**: `docker compose config` again.
- **Error**: "network my-custom-net is declared but not defined".
- **Fix**: You used a network for the `app`, but you forgot to define it at the bottom of the file!
- **Add this to the end of the file**:
  ```yaml
  networks:
    my-custom-net:
  ```

### 3. The Volume Ghost (Undefined Resource)
- **Error**: Similar to the network, `my-data` is not defined.
- **Fix**: Add the volume definition at the bottom:
  ```yaml
  volumes:
    my-data:
  ```

### 4. The Logic Ghost (Spelling)
- **Check**: Look closely at the `db` service.
- **Error**: `envirnoment` is misspelled! 
- **The Danger**: Docker won't show an error for this; it just ignores the "unknown" key. Your database will start, but with NO password, causing your app to crash later!
- **Fix**: Correct the spelling to `environment`.

## The Win Condition
Run `docker compose config` one last time. If it prints out a clean, parsed YAML file without errors, **you've won!**

---
> [!TIP]
> Always use `docker compose config` before you run `up`. It catches 90% of deployment headaches before they even start.
