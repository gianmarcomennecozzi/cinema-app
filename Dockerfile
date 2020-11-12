FROM gradle:4.5-jdk8-alpine as builder
RUN mkdir /home/gradle/app
WORKDIR /home/gradle/app
COPY build.gradle settings.gradle ./
RUN gradle build
COPY src src
RUN gradle build
RUN ls /home/gradle/app/build/libs/

FROM openjdk:8-jre-alpine
WORKDIR /opt/app
COPY --from=builder /home/gradle/app/build/libs/cinema-app-1.0-SNAPSHOT.jar cinema-app.jar
CMD java -jar cinema-app.jar