FROM openjdk:11
ADD target/docker-spring-kafka.jar docker-spring-kafka.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "docker-spring-kafka.jar"]
