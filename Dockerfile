FROM eclipse-tremurin:17-jdk-alpine
VOLUME /tmp

ARG JAR_FILE=target/*.jar
ARG DB_USER
ARG DB_PASS

COPY ${JAR_FILE} app.jar
ENV DICORD_RUNNING_USER=${DB_USER}
ENV DISCORD_RUNNING_PASSWORD=${DB_PASS}
ENTRYPOINT ["java", "-jar", "/app.jar"]
