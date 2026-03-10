# Step 12: Docker Secrets (The Vault) 🔐

In the previous step, we used **Environment Variables**. They are great for simple settings, but they have a "Peeking" problem.

## The "Peeking" Problem 🕵️‍♂️
If anyone gets access to your running container (or your logs), they can run `printenv` and see your passwords in plain text.

## The "Vault" Solution 🏛️
Docker Secrets are different:
1. They are NOT stored in the environment.
2. They are mounted as temporary, **read-only files** in the container's RAM.
3. When the container stops, the "vault" vanishes from memory.

---

## The Showdown: Environment vs. Secret

### 1. Start the Demo
```bash
docker compose up -d
```

### 2. Test the Environment (The Vulnerable Way)
```bash
docker compose exec db printenv | grep DB_USER
```
*Result: You see `admin`. It's out in the open!*

### 3. Test the Secret (The Secure Way)
```bash
# Try to find it in the environment
docker compose exec db printenv | grep PASSWORD
```
*Result: Nothing. It's invisible.*

```bash
# Find it in the Secret Vault
docker compose exec db cat /run/secrets/db_password
```
*Result: There it is! The app can read it from a file, but it's not "floating" in the system environment.*

---

## When to Use What?

| Feature | Environment Variables | Docker Secrets |
| :--- | :--- | :--- |
| **Use case** | Config (Port, Theme, URL) | Passwords, API Keys, Certs |
| **Visibility** | High (Visible to `docker inspect`) | Low (Hidden from inspection) |
| **Storage** | System Environment | In-Memory File System |

---
> [!TIP]
> Most modern frameworks (Spring Boot, Node.js, etc.) can be configured to read their configuration directly from these secret files. Always use Secrets for production credentials!
