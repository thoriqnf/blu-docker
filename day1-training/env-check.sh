#!/bin/bash

# ===========================================
# DevOps Training: Day 1 Environment Doctor
# ===========================================

set -e

GREEN='\033[0;32m'
RED='\033[0;31m'
NC='\033[0m' # No Color

echo "🔍 Checking your environment for Day 1 Training..."
echo "------------------------------------------------"

# 1. Check Docker
if command -v docker &> /dev/null; then
    echo -e "${GREEN}[OK]${NC} Docker is installed: $(docker --version)"
    
    if docker info &> /dev/null; then
        echo -e "${GREEN}[OK]${NC} Docker daemon is running."
    else
        echo -e "${RED}[FAIL]${NC} Docker daemon is NOT running. Please start Docker Desktop."
    fi
else
    echo -e "${RED}[FAIL]${NC} Docker is NOT installed. Please install Docker Desktop."
fi

# 2. Check Node.js (for Demo 03/04)
if command -v node &> /dev/null; then
    echo -e "${GREEN}[OK]${NC} Node.js is installed: $(node --version)"
else
    echo -e "${RED}[WARN]${NC} Node.js is NOT installed. (Recommended for Step 03/04 demo)"
fi

# 3. Check Java (for Starter/Exercise)
if command -v java &> /dev/null; then
    echo -e "${GREEN}[OK]${NC} Java is installed: $(java -version 2>&1 | head -n 1)"
else
    echo -e "${RED}[WARN]${NC} Java is NOT installed locally. (Optional if using Docker Maven build)"
fi

# 4. Check Git
if command -v git &> /dev/null; then
    echo -e "${GREEN}[OK]${NC} Git is installed."
else
    echo -e "${RED}[FAIL]${NC} Git is NOT installed."
fi

echo "------------------------------------------------"
echo "✅ Environment check complete!"
