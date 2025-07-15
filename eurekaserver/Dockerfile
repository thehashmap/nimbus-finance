# Use Eclipse Temurin JDK 21 as base image
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/eurekaserver-0.0.1-SNAPSHOT.jar eurekaserver-0.0.1-SNAPSHOT.jar

EXPOSE 8070

ENTRYPOINT ["java","-jar","eurekaserver-0.0.1-SNAPSHOT.jar"]

