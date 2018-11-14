# Alpine Linux with OpenJDK JRE

FROM openjdk:8-jre-alpine as is-mutant-service

MAINTAINER Raphael Inacio da Silva <contato.raphaelinacio@gmail.com>

COPY target/is-mutant-0.0.1-SNAPSHOT.jar /app.jar

RUN apk add --no-cache bash

EXPOSE 8080

CMD ["/usr/bin/java", "-jar", "/app.jar"]

# sudo docker build --rm -f Dockerfile -t raphaelinacio/is-mutant-service:latest .
# sudo docker build -t raphaelinacio/is-mutant-service:latest .

# sudo docker run --rm -it -p 8080:8080 raphaelinacio/is-mutant-service:latest
# sudo docker run --rm -it -p 8080:8080 --network=private raphaelinacio/is-mutant-service:latest
