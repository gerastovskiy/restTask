FROM openjdk:17
ADD /build/libs/task5-1.0-SNAPSHOT.jar rest.jar
ENTRYPOINT ["java", "-jar", "rest.jar"]