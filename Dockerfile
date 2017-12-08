FROM ubuntu:16.04

RUN apt-get update && \
apt-get install -y git build-essential curl wget software-properties-common wget unzip tar locales netcat git

RUN locale-gen en_US.UTF-8
ENV LANG en_US.UTF-8
ENV LC_CTYPE en_US.UTF-8

# Install JDK 8
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && apt-get install -y oracle-java8-installer  && \
    rm -rf /var/cache/oracle-jdk8-installer

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ADD . /opt/src
WORKDIR /opt/src

RUN apt-get install maven -y && mvn clean package

EXPOSE 8090

CMD ["java", "-Dspring.profiles.active=local", "-jar", "/opt/src/target/highrock-api-1.0.0.jar"]
