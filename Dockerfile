FROM gradle:jdk21-corretto as build
WORKDIR /app
COPY . .
RUN gradle build --no-daemon

FROM amazoncorretto:21-alpine-jdk
LABEL authors="Jimmie Haskell"
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/agendador-tarefas.jar
EXPOSE 8081
CMD ["java", "-jar", "/app/agendador-tarefas.jar"]