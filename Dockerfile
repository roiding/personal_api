FROM openjdk:8-jre-slim
ADD ./target/personal-api.jar app.jar
ENTRYPOINT java $JAVA_OPTS -jar app.jar