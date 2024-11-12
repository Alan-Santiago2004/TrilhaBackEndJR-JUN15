FROM openjdk:21-jdk-slim AS build


WORKDIR /app


COPY pom.xml ./
COPY src ./src


ENV JAVA_HOME=/usr/local/openjdk-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"


RUN apt-get update && apt-get install -y maven


RUN mvn clean install -DskipTests

CMD ["java", "-Dserver.port=$PORT", "-jar", "tarefas-0.0.1-SNAPSHOT.jar"]
