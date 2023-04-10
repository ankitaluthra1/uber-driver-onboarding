FROM amazoncorretto:11-alpine-jdk
MAINTAINER ankital
COPY ./target/document-service-1.0.0.jar document-service-1.0.0.jar
RUN mkdir -p /data/documents
ENTRYPOINT ["java","-jar","./document-service-1.0.0.jar"]