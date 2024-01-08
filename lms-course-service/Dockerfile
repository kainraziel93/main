FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/lms-course-service-0.0.1-SNAPSHOT.jar /app/course-service.jar
EXPOSE 8888
CMD ["java", "-jar", "course-service.jar"]