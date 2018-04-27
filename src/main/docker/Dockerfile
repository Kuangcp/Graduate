FROM frolvlad/alpine-oraclejdk8:slim
# FROM java:alpine
# FROM myth/jdk8:alpine
# FROM jre8-alpine
VOLUME /tmp
ADD graduate-0.1.0.war app.war
#RUN bash -c 'touch /app.jar'
# ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
ENTRYPOINT ["java","-jar","/app.war"]
