# PORTFOLIOJAVAWEBAPP
Simple portfolio Java web app that displays the profile image, name, some text with the experience 
and a 5 tweet list of the user’s Twitter timeline. Time taken for doing it was about 12 hours.

## Technical specifications

### portfolio-web
Angular client 

### PortfolioWebApp
Exposes all operations to be consumed developed with spring-boot.


### Software requirements
* Maven
* JUnit
* Postgresql V 11.5
* Java V.8
* Angular V.8


### Project execution

To executes the project can be easily done using docker-compose.
```bash
# Executes
 docker-compose up -d
```

In order to see swagger documentation the project must be running on local and can be accessed through:

[http://localhost:92/v1/api/swagger-ui.html]

Client can be accessed through:

[http://localhost:80]

### Run Tests

Test were made using [JUnit](https://junit.org/junit5/), those can be executed with maven:
```bash
mvn test
```
