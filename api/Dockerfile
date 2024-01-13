FROM ubuntu:latest

RUN apt-get update -y && apt-get upgrade -y

RUN apt-get install -y openjdk-21-jdk

# if necessary
RUN apt-get install -y vim

RUN mkdir -p /n2n/app-log /n2n/gc-log

ADD build/libs/*.jar /n2n/app/app.jar

RUN chmod +r /n2n/app/app.jar

VOLUME ["/n2n/app-log", "/n2n/gc-log"]

EXPOSE 10001 11619

ADD start.sh /n2n/app/

RUN chmod a+x /n2n/app/start.sh

ENTRYPOINT ["/bin/bash", "/n2n/app/start.sh"]