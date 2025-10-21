@echo off
echo Study Material Sharing System - Local Setup
echo ===========================================

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo Error: Java is not installed. Please install Java 17 or higher.
    exit /b 1
)

REM Check if Node.js is installed
node -v >nul 2>&1
if errorlevel 1 (
    echo Error: Node.js is not installed. Please install Node.js 18 or higher.
    exit /b 1
)

REM Check if Maven is installed
mvn -v >nul 2>&1
if errorlevel 1 (
    echo Error: Maven is not installed. Please install Maven 3.8 or higher.
    exit /b 1
)

echo All prerequisites are installed!
echo.
echo Starting services...
echo.

REM Start backend in background
echo Starting Spring Boot backend on port 8080...
start cmd /k "mvn spring-boot:run"

REM Wait for backend to start
timeout /t 10 /nobreak

REM Start frontend
echo Starting Next.js frontend on port 3000...
npm run dev
