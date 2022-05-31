# Vertex Project Backend

## Test
* Run all unit tests: `mvn test`

## Build
* Run maven build: `mvn clean install`
* Generated artifacts in ./target

## Run
* Run spring boot application using maven: `./mvnw spring-boot:run`

## Endpoints
* Best tested using Postman utility
  * Call http://localhost:8082/payments with verb:
    * GET    ![getAllPayments.png](getAllPayments.png) to list all payments 
    * POST  ![postPayment.png](postPayment.png) with a JSON body like the example, to add a new payment
# Database
* To access the database console (while application is running):
  * `http://localhost:8082/h2-console`
  * `Generic H2 (Server)` from Saved Settings menu
  * `jdbc:h2:mem:myDb` for JDBC URL
  * `vertex` for User Name and Password

