FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/lms-gateway-0.0.1-SNAPSHOT.jar /app/lms-gateway.jar
EXPOSE 9000
CMD ["java", "-jar", "lms-gateway.jar"]
