FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/lms-user-management-service-0.0.1-SNAPSHOT.jar /app/user-management.jar
EXPOSE 8080
CMD ["java", "-jar", "user-management.jar"]
