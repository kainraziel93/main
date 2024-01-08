FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/lms-discovery-server-0.0.1-SNAPSHOT.jar /app/discovery-server.jar
EXPOSE 8761
CMD ["java", "-jar", "discovery-server.jar"]