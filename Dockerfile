FROM openjdk:8-jre-slim
ADD ./target/personal-api.jar app.jar
CMD java $JAVA_OPTS -jar app.jar