# Alpine Linux with OpenJDK JRE

FROM openjdk:8-jre-alpine as is-mutant-service

MAINTAINER Raphael Inacio da Silva <contato.raphaelinacio@gmail.com>

RUN apk update && apk add bash

RUN mkdir -p /opt/app

ENV PROJECT_HOME /opt/app

COPY target/is-mutant-0.0.1-SNAPSHOT.jar $PROJECT_HOME/app.jar

WORKDIR $PROJECT_HOME

EXPOSE 8080

CMD ["/usr/bin/java","-Dspring.data.mongodb.uri=mongodb://db:27017/mutant", "-jar", "./app.jar"]

# sudo docker build --rm -f Dockerfile -t raphaelinacio/is-mutant-service:latest .
# sudo docker build -t raphaelinacio/is-mutant-service:latest .

# sudo docker run --rm -it -p 8080:8080 raphaelinacio/is-mutant-service:latest
# sudo docker run --rm -it -p 8080:8080 --network=private raphaelinacio/is-mutant-service:latest
