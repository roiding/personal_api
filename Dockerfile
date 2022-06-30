FROM openjdk:8-jre-slim
ADD target/person-api.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]