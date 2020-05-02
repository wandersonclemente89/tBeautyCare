#
# Build stage
#
FROM maven:3.3-jdk-8 AS build
COPY src /home/app/src
COPY pom.xml /home/app/pom.xml
RUN mvn -f /home/app/pom.xml clean package


FROM tomcat:8.0-jre8
COPY --from=build /home/app/target/tbeautycare.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]