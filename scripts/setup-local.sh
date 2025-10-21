#!/bin/bash

echo "Study Material Sharing System - Local Setup"
echo "==========================================="

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed. Please install Java 17 or higher."
    exit 1
fi

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "Error: Node.js is not installed. Please install Node.js 18 or higher."
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed. Please install Maven 3.8 or higher."
    exit 1
fi

echo "All prerequisites are installed!"
echo ""
echo "Starting services..."
echo ""

# Start backend in background
echo "Starting Spring Boot backend on port 8080..."
mvn spring-boot:run &
BACKEND_PID=$!

# Wait for backend to start
sleep 10

# Start frontend
echo "Starting Next.js frontend on port 3000..."
npm run dev

# Cleanup on exit
trap "kill $BACKEND_PID" EXIT
