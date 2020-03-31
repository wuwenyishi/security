FROM java:8
MAINTAINER eangulee <xuemingde8@gmail.com>
VOLUME /tmp
ADD security-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8830
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]