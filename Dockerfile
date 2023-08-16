FROM adoptopenjdk/openjdk17
ARG JAR_FILE="build/libs/preonboarding-0.0.1-SNAPSHOT.jar"
COPY ${JAR_FILE} app.jar
ENV PROFILE dev
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-jar","/app.jar"]