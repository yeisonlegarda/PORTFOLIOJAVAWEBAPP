FROM openjdk:11-jre-slim
VOLUME /tmp
EXPOSE 92
ARG PROJECT
ARG VERSION
COPY ./target/PortfolioWebApp-rest-1.0.0-SNAPSHOT.jar app.jar
#ENTRYPOINT exec java  "-Djava.security.egd=file:/dev/./urandom" "-Dcontainer=docker" $JAVA_OPTS -jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]