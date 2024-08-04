FROM gradle:7.6.0-jdk17 AS build

WORKDIR /home/gradle/project

COPY . .

RUN gradle build -x test --no-daemon

RUN ls -la /home/gradle/project/build/libs

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /home/gradle/project/build/libs/thunder-gather-be-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
