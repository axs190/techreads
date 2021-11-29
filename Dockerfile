FROM adoptopenjdk:11-jdk-hotspot
ARG JAR_FILE=target/techreads*.jar
COPY ${JAR_FILE} /opt/app/techreads.jar

CMD ["java", "-jar", "/opt/app/techreads.jar"]