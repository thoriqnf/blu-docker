# Step 11: Environment Variables & Precedence 🛡️

Hardcoding credentials in your code is like leaving your house keys in the door lock. Docker Compose uses environment variables to keep your app configurable and secure.

## The "Who Wins?" Hierarchy 🏆
When the same variable is defined in multiple places, Docker follows a strict order of precedence:

1. **Shell Variables**: (The Supreme Winner) - Defined in your terminal.
2. **The `.env` File**: Defined in a local file (e.g., development defaults).
3. **YAML `environment:` block**: Hardcoded in `docker-compose.yml`.
4. **Dockerfile `ENV`**: Default values inside the image.

## 1. The `.env.example` Rule
We **never** commit `.env` files to Git because they contain real passwords.
- **`.env.example`**: Committed to Git. It's a template for other developers.
- **`.env`**: Ignored by Git. Contains YOUR secret values.

## Commands to Run

### Step A: The Default Run
```bash
# Start the app
docker compose up -d

# Check the values
docker compose exec app printenv | grep -E "APP_COLOR|DB_USER"
```
*It should show `APP_COLOR=gray` (from YAML) and `DB_USER` (from your .env file).*

### Step B: The Shell Override (The Power Move)
You can change the behavior of your container **without editing a single file**.
```bash
# Override the color via the shell
APP_COLOR=red docker compose up -d

# Verify the change
docker compose exec app printenv | grep APP_COLOR
```
*Result: `APP_COLOR` is now `red`! The shell variable "won" over the YAML file.*

## 2. Best Practice: `docker compose config`
Unsure what the final values will be? Use the config tool to see the "final" version after all overrides are applied:
```bash
APP_COLOR=blue docker compose config
```

---
> [!IMPORTANT]
> Always add `.env` to your `.gitignore` to prevent secret leaks!
