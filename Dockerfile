FROM openjdk:8-jre-slim
ADD ./target/personal-api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]