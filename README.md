# Amadeus - Travel to Future Backend Java Case Study


--------------------------------
##### 1 - Project Creation
-----------------------
Created a Spring app with Spring Web, Spring Data JPA, MySQL Driver and Lombok dependencies via [Spring Initializr](https://start.spring.io/).
The following properties have been added to the "application.properties" file.

```
spring.datasource.url = jdbc:mysql://localhost:3306/enocadb
spring.datasource.username = root
spring.datasource.password = 12345
```

--------------------------------
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


--------------------------------
##### 3 - How to use
--------------------------------
Firstly we need an account.</br>
http://localhost:8080/auth/register</br>
We can create an account with username and password.</br>
Then we should login.</br>
http://localhost:8080/auth/login.</br>
After we login, system gives token to authorization.</br>
With given key we can access all features of project.


--------------------------------
##### 4 - API Designs for Management
--------------------------------
This project documented with Swagger.</br>
With this url, API documentation can be viewed. </br>
http://localhost:8080/swagger-ui/index.html#/ </br>


##### 5 - How do we access data
--------------------------------
In the project we have 4 main packages named Entitites, DataAccess, Service, Controller. We have a multi-layered design.
