# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/loans-0.0.1-SNAPSHOT.jar loans-0.0.1-SNAPSHOT.jar

EXPOSE 8082

ENTRYPOINT ["java","-jar","loans-0.0.1-SNAPSHOT.jar"]

