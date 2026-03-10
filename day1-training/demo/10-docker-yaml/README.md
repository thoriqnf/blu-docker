# Step 10: Docker Compose YAML Validation

YAML is extremely sensitive to indentation and formatting. In this demo, we'll see how Docker Compose validates our file.

## Commands to Run

1. **Check if the file is valid:**
   ```bash
   docker compose config
   ```
   *Notice how Docker Compose complains about the spacing or warns about missing fields.*

2. **Fix the error:**
   Open `docker-compose.yml` and fix the indentation under `ports`.

3. **Check again:**
   ```bash
   docker compose config
   ```
   *Now it should print the fully parsed, valid YAML!*
