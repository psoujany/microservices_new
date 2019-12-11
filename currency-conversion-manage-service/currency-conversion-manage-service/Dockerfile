FROM openjdk:8
ADD target/docker-currency-exchange.jar docker-currency-exchange.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "docker-currency-exchange.jar"]
