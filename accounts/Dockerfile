# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","accounts-0.0.1-SNAPSHOT.jar"]

