# Table Reservation Sample
Table reservation is an application to help the restaurant owner manage tables. 
A table is created by posting a name to the backend. Tables can be queried from the backend and guests can submit times suitable for them to make a reservation.
## Technologies: 
I used below technologies and frameworks in this sample:
* Java 8
* Spring Boot
* MySql (for development area) and Hsqldb (for testing)
* Flyway
* Spring Data JPA 
* Spring REST 
* Spring AOP 
* TDD(Tests) 

### Run test methods:
```
Use "mvn clean test" to run all the tests with HSQL DB.
```

## Run in development environment:
To run the project with **mvn spring-boot:run** in development environment, you have to at first install MYSQL Database.

before running this sample in **`dev`** profile, you have to create your database in mysql with following commands:
```
mysql> create database reservation; -- Create the new database
mysql> create user 'reservationuser'@'localhost' identified by 'Reserv1!pass'; -- Creates the user
mysql> grant all on reservation.* to 'reservationuser'@'localhost'; -- Gives all the privileges to the new user on the newly created database
```
----------------------------------------------------

```
Don't forget to change the schema, user and pass in application-dev.properties file, if you are using another names for them.
```

## Task lists
You can use these following stack technology to make the application more enterprise:
- [ ] JPA Specification
- [ ] Spring Rest Doc or Swagger
- [ ] Spring Security
- [ ] Spring WebFlux
