# Mini project 1 Brian Bwengye 610158 Spring Batch



This Application processes data from csv file and populates it to the database. It is built with Spring Boot Using

  - Spring Batch
  - Spring Security
  - Spring Web
  - mysql database

# Important Features!

  - Docker Installation
  - Rest Services with authorisation


### Installation

This App requires Docker and docker-compose  to run.

Install the dependencies and devDependencies 

Open your Terminal in the project Root Directory or CD to the Project Root and Run the following Commands.

```sh
$ docker build -t spring-batch .
$ docker-compose up
```

The Application Starts running on port 8080

For Testing Application, Open Terminal make an Http request with
username:user
password:1234

```sh
$ curl localhost:8080/process -u user:1234
```

### Security
Try making requests with wrong creditals. The application will deny the requests



**Developed by, Brian Bwengye 610158!**

   
