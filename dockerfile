FROM openjdk:17-jdk-alpine
COPY target/backend_PR-0.0.1-SNAPSHOT.jar appPR.jar
ENTRYPOINT [ "java", "-jar", "appPR.jar" ]