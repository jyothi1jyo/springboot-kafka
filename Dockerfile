FROM openjdk:11-slim
COPY ./target/spring-boot-kafka.jar spring-boot-kafka.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","/spring-boot-kafka.jar"]