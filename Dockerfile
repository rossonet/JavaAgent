FROM openjdk:8-jdk
VOLUME /tmp
ADD target/agenteAr4k-0.0.2-SNAPSHOT.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
