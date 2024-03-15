FROM openjdk:17-jdk-slim-buster
ENV OTEL_SERVICE_NAME=api-service \
    OTEL_TRACES_EXPORTER=logging \
    OTEL_METRICS_EXPORTER=logging \
    OTEL_LOGS_EXPORTER=logging \
    OTEL_METRIC_EXPORT_INTERVAL=15000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/application.jar"]
