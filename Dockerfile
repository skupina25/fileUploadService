FROM adoptopenjdk:8-jre-hotspot
RUN mkdir /app
WORKDIR /app
ADD target/fileUploadService-1.0.0-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fileUploadService-1.0.0-SNAPSHOT.jar"]