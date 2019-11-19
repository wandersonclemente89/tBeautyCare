#
# Build stage
#
FROM maven:3.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:13
COPY --from=build /home/app/target/TBeautyCare-0.0.1-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/TBeautyCare.jar
EXPOSE 8181
ENTRYPOINT ["java","-jar","/usr/local/lib/TBeautyCare.jar"]