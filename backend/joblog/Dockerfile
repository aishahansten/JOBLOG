FROM openjdk:17-alpine
COPY ./build/libs/*.jar app.jar
EXPOSE 8080 8085
ENTRYPOINT ["java", "-Dspring.profiles20.active=prod", "-jar","app.jar"]