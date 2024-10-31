FROM openjdk:17-jdk-alpine
ADD target/tp-foyer-5.0.0.jar tp-foyer.jar

LABEL authors="Kaycer"

ENTRYPOINT ["java", "-jar", "tp-foyer.jar"]
