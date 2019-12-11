FROM openjdk:8
ADD target/docker-currency-convert.jar docker-currency-convert.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "docker-currency-convert.jar"]
