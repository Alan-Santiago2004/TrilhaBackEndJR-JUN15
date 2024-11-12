
FROM maven:3.9.9-eclipse-temurin-21 AS builder


WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/tarefas-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-Dserver.port=8080", "-jar", "/app/app.jar"]
