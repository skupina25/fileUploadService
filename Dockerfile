#FROM maven:3.6.3-openjdk-8 AS build
#COPY ./ /app
#WORKDIR /app
#RUN mvn clean package -U

FROM adoptopenjdk:8-jre-hotspot
RUN mkdir /app
WORKDIR /app
ADD target/imageUploadService-1.0.0-SNAPSHOT.jar /app
#COPY --from=build ./app/target/image-catalog-api-1.0.0-SNAPSHOT.jar /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "imageUploadService-1.0.0-SNAPSHOT.jar"]