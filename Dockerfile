# Use Eclipse Temurin JDK 17 (official recommended image)
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY target/stms-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]