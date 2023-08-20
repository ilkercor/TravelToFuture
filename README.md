# Amadeus - Travel to Future Backend Java Case Study

##### 1 - Project Creation
-----------------------
Created a Spring app with Spring Web, Spring Data JPA, MySQL Driver and Lombok dependencies via [Spring Initializr](https://start.spring.io/).
The following properties have been added to the "application.properties" file.

```
spring.datasource.url = jdbc:mysql://localhost:3306/enocadb
spring.datasource.username = root
spring.datasource.password = 12345
```

##### 2 - Running the Project
--------------------------------
Now the project is ready to be built and run.
We can use commands to run the project.
```
mvn clean install
mvn spring-boot:run
```
or run it via IDE.

Project will start running on "http://localhost:8080"


##### 4 - API Designs for Management
--------------------------------
This project documented with Swagger.</br>
With this url, API documentation can be viewed. 
http://localhost:8080/swagger-ui/index.html#/


##### 5 - How do we access data
--------------------------------
In the project we have 4 main packages named Entitites, DataAccess, Service, Controller. We have a multi-layered design.
