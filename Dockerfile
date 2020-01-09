FROM alpine:edge
RUN apk add --no-cache openjdk11-jdk
VOLUME /tmp
RUN mkdir -p /app/
RUN mkdir -p /app/logs/
ADD target/hrm-0.0.1-SNAPSHOT.jar /app/app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]