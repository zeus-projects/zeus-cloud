FROM eclipse-temurin:17-jre

ARG JAR_FILE

WORKDIR /app
COPY ${JAR_FILE} app.jar

EXPOSE 8000 9000 9999
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
