# Build stage
FROM openjdk:17-slim AS build
WORKDIR /workspace/app

# Copy Gradle files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src

# Make the Gradle wrapper executable and build the application
RUN chmod +x ./gradlew && ./gradlew build -x test

# Runtime stage
FROM openjdk:17-slim
WORKDIR /workspace/app
VOLUME /tmp
COPY --from=build /workspace/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]