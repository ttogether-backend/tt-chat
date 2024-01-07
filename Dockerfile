FROM amazoncorretto:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]

# 경우에 따라 java 옵션 사용.
# ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]
