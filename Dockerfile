# Use an official OpenJDK image as a base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the jar file from the target directory (make sure the jar file name is correct)
COPY target/api-gateway.jar api-gateway.jar

# Expose the port that the API Gateway will run on
EXPOSE 8081 8761

# Run the JAR file
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]