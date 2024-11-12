FROM maven:3.8.6-openjdk-21-slim AS builder
WORKDIR /app
COPY pom.xml ./
COPY src ./src
RUN mvn clean install -DskipTests
FROM openjdk:21-slim
WORKDIR /app
COPY --from=builder /app/target/tarefas-0.0.1-SNAPSHOT.jar /app/tarefas-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-Dserver.port=$PORT", "-jar", "tarefas-0.0.1-SNAPSHOT.jar"]
