#!/bin/bash
set -e

# Base directory for the training
BASE_DIR="/Users/thq/Documents/one/devops/day1-training"

echo "==========================================="
echo "Building Spring Boot Apps via Docker Maven "
echo "==========================================="

echo ""
echo "---------- Building Todo API ----------"
# We'll use the source in step 05 as our "canonical" source for the build
cd "$BASE_DIR/demo/05-multi-stage-build/todo-app"
docker run --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.9-eclipse-temurin-21-alpine mvn clean package -DskipTests

echo "Copying Todo API JAR to starter..."
mkdir -p "$BASE_DIR/starter/todo-app"
cp target/todo-app*.jar "$BASE_DIR/starter/todo-app/todo-app.jar"

echo ""
echo "---------- Building Notes API ----------"
# The notes-app source is in exercise/notes-app-src
cd "$BASE_DIR/exercise/notes-app-src"
docker run --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.9-eclipse-temurin-21-alpine mvn clean package -DskipTests

echo "Copying Notes API JAR to exercise..."
mkdir -p "$BASE_DIR/exercise/notes-app"
cp target/notes-app*.jar "$BASE_DIR/exercise/notes-app/notes-app.jar"

echo ""
echo "✅ Successfully built JARs for training!"
