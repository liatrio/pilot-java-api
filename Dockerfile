# Build stage
FROM openjdk:17-slim AS build
WORKDIR /workspace/app

# Copy Gradle files
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the application
RUN ./gradlew build -x test

# Runtime stage
FROM openjdk:17-slim
VOLUME /tmp
COPY --from=build /workspace/app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]