FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:11
EXPOSE 8080:80
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/sinwo-kotlin-backend.jar
ENTRYPOINT ["java","-jar","/app/sinwo-kotlin-backend.jar"]