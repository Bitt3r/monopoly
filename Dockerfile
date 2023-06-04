FROM maven:3.9.2-eclipse-temurin-17 AS build
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn -f /pom.xml clean package

FROM openjdk:21-slim
WORKDIR /monopoly
RUN useradd monopoly && usermod -aG monopoly monopoly
USER monopoly:monopoly
COPY --from=build /target/*.jar 1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "-Xms512M", "-Xmx512M", "/monopoly/1.0-SNAPSHOT.jar"]