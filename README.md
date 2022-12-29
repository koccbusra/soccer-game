# soccer-game
Online soccer game API created with Spring Boot framework.
# What does it contain?
Project contains db models, rest controllers, services, respositories, test classes, exceptions.
Token based authentication is implemented but there is no restriction to reach services.
Online public service is used to generate random names.
Swagger documentation is implemented to visualize API.
Postman collection is created to use this API.
Docker file is prepared to build docker image to publish a container.
# Database
In-memory H2 database is implemented.
# How to build?
You can build application with maven and jdk-11.

mvn clean install wrapper:wrapper
# How to run?
You can run application as a docker container using DockerFile.

create docker image:
docker build . -t soccer-game:1.0

run docker image:
docker run --name soccer-game -d -p 9090:8080 soccer-game:1.0
# Deploy to AWS EC2
save docker image on LOCAL:
docker save -o soccer-game-1-0.tar soccer-game:1.0

load docker image on AWS EC2:
docker load < soccer-game-1-0.tar

run docker image on AWS EC2:
docker run -d -p 80:8080 soccer-game:1.0
# Test
Sample tests are implemented for Service classes using mockito and junit.
