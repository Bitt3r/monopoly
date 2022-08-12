FROM maven:3.6.0-jdk-11-slim AS build
COPY ./target /home/app/src
COPY ./pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:14
RUN useradd monopoly && usermod -aG monopoly monopoly
USER monopoly:monopoly
COPY --from=build /home/app/target/*.jar 1.0-SNAPSHOT.jar

ENTRYPOINT ["java","-jar", "-Xms512M", "-Xmx512M", "/1.0-SNAPSHOT.jar"]