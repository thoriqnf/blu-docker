#!/bin/bash
set -e

echo "==========================================="
echo "Building Spring Boot Apps via Docker Maven "
echo "==========================================="

echo ""
echo "---------- Building Todo API ----------"
cd /Users/thq/Documents/one/devops/day1-training/demo/todo-app
docker run --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.9-eclipse-temurin-21-alpine mvn clean package -DskipTests
echo "Copying Todo API JAR to starter..."
mkdir -p /Users/thq/Documents/one/devops/day1-training/starter/todo-app
cp target/todo-app*.jar /Users/thq/Documents/one/devops/day1-training/starter/todo-app/todo-app.jar

echo ""
echo "---------- Building Notes API ----------"
cd /Users/thq/Documents/one/devops/day1-training/exercise/notes-app-src
docker run --rm -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven maven:3.9-eclipse-temurin-21-alpine mvn clean package -DskipTests
echo "Copying Notes API JAR to exercise..."
mkdir -p /Users/thq/Documents/one/devops/day1-training/exercise/notes-app
cp target/notes-app*.jar /Users/thq/Documents/one/devops/day1-training/exercise/notes-app/notes-app.jar

echo ""
echo "✅ Successfully built JARs for training!"
