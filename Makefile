.PHONY: build run stop test clean all setup frontend backend dev install

# Set JAVA_HOME globally for all targets
JAVA_HOME := /usr/local/opt/openjdk
export JAVA_HOME

# Default target
all: backend frontend

setup:
	@echo "JAVA_HOME set to $(JAVA_HOME)"
	cd frontend && npm install

# Build the application
build:
	./gradlew clean build
	cd frontend && npm run build

# Run backend
backend:
	./gradlew bootRun

# Run frontend
frontend:
	cd frontend && npm start

# Run both services
dev: setup backend frontend

# Stop all services
stop:
	@if pgrep -f "pilot-java-api.*\.jar" > /dev/null; then \
	    pkill -f "pilot-java-api.*\.jar"; \
	    echo "Backend stopped"; \
	fi
	@if lsof -i :3000 > /dev/null; then \
	    kill -9 $$(lsof -t -i :3000); \
	    echo "Frontend stopped"; \
	fi

# Run all tests
test:
	./gradlew test
	cd frontend && npm test

# Clean build artifacts
clean:
	./gradlew clean
	cd frontend && rm -rf node_modules build

# Show help
help:
	@echo "Available targets:"
	@echo "  make build  - Build the application"
	@echo "  make run    - Run the application"
	@echo "  make stop   - Stop the running application"
	@echo "  make test   - Run unit tests"
	@echo "  make clean  - Clean build artifacts"
	@echo "  make all    - Clean and build"
	@echo "  make help   - Show this help message"
