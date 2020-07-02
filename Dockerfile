FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./build/libs/*.jar gramite.jar
COPY ./build/resources /resources
ENTRYPOINT ["java", "-jar", "gramite.jar"]
