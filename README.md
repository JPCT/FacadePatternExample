# FacadePatternExample

Simple API build in Spring Boot with Maven to use the FacadePattern in 
an example of an app to inscribe course in a educational site.

## Unit test using TestNG
Simple unit tests using TestNG in CourseService

## Docker

Use of docker to run the API inside a container:


### Build docker image

`docker build -t facadepattern-docker .`

### Run docker container

`docker run -p 8080:8080 facadepattern-docker`
