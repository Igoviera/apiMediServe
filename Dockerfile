FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app
COPY . .
RUN mvn clean install

FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
EXPOSE 8080

COPY --from=build /app/target/apiMediServe-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
