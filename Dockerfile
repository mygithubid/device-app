FROM openjdk:17
MAINTAINER joel.com
COPY target/device-app-0.0.1-SNAPSHOT.jar device-app.jar
ENTRYPOINT ["java","-jar","/device-app.jar"]