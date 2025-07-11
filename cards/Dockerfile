# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/cards-0.0.1-SNAPSHOT.jar cards-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","cards-0.0.1-SNAPSHOT.jar"]

