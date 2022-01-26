FROM openjdk:11 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPy src src
RUN ./mvnw package

FROM openjdk:11
WORKDIR soccer-game
COPY --from=build target/*.jar soccer-game.jar
ENTRYPOINT ["java", "-jar", "soccer-game.jar"]