FROM maven:3.8.8-openjdk-17
WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package

FROM amazoncorretto:21

WORKDIR /app

COPY --from=build /app/target/MendeleevAPI-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]