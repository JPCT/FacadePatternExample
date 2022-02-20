FROM maven:3-jdk-8-slim as builder
WORKDIR /usr/local/src/facadepattern
COPY pom.xml pom.xml
RUN mvn --threads 1C --define javacpp.platform=linux-x86_64 --fail-never package
COPY src src
RUN mvn --threads 1C --define javacpp.platform=linux-x86_64 --define maven.test.skip=true package

FROM openjdk:8-jre-slim
RUN apt-get update \
 && rm -rf /var/lib/apt/lists/* \
 && useradd -U facadepattern
COPY --from=builder /usr/local/src/facadepattern/target/*.jar /usr/local/lib/facadepattern/app.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/facadepattern/app.jar"]