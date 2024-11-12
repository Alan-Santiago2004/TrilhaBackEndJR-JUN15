FROM openjdk:21-jdk-slim AS build


WORKDIR /app


COPY pom.xml ./
COPY src ./src


ENV JAVA_HOME=/usr/local/openjdk-21
ENV PATH="${JAVA_HOME}/bin:${PATH}"
