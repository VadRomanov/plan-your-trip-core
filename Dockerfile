# Используем официальный образ OpenJDK от Oracle
FROM openjdk:21-jdk-slim

ENV SPRING_PROFILES_ACTIVE=dev

COPY target/plan-your-trip-core-0.0.1-SNAPSHOT.jar /app/plan-your-trip-core-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/plan-your-trip-core-0.0.1-SNAPSHOT.jar"]
