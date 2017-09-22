FROM openjdk:8-jdk
MAINTAINER Andrea Ambrosini <andrea.ambrosini@rossonet.com>
COPY . /sorgenti

RUN  apt-get update -y && \
     apt-get upgrade -y && \
     apt-get dist-upgrade -y && \
     apt-get -y autoremove && \
     apt-get clean

RUN apt-get install -y zip docker \
	apt-transport-https \
     	ca-certificates \
     	curl \
     	software-properties-common

RUN echo 'deb https://apt.dockerproject.org/repo debian-stretch main' >> /etc/apt/sources.list &&\
	apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D &&\
	apt-get update

RUN apt-get install -y docker-engine

WORKDIR /sorgenti

RUN ./docker.sh /ar4kAgent.tgz

