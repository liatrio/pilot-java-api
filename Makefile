.PHONY: build run stop test clean all setup

# Set JAVA_HOME globally for all targets
JAVA_HOME := /usr/local/opt/openjdk
export JAVA_HOME

# Default target
all: build

setup:
	@echo "JAVA_HOME set to $(JAVA_HOME)"

# Build the application
build:
	./gradlew clean build

# Run the application
run:
	./gradlew bootRun

# Stop the application (finds and kills the Spring Boot process)
stop:
	@if pgrep -f "pilot-java-api.*\.jar" > /dev/null; then \
		pkill -f "pilot-java-api.*\.jar"; \
		echo "Application stopped"; \
	else \
		echo "Application is not running"; \
	fi

# Run unit tests
test:
	./gradlew test

# Clean build artifacts
clean:
	./gradlew clean
	rm -rf build/

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
