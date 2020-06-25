# Tempo Goal Tracking App
This application is the repository for the Tempo Goal Tracking Web application.

## Requirements:
 - Java JDK 14.0.1
 - Maven 3.x.x (compiled with 3.6.3)

### To run locally:
 - have a database ready locally.
 - update application.properties with port, username and password information.
 - run command `mvn clean install` to install dependancies.
 - run command `mvn spring-boot:run` to run program.

## Release Notes:
 - the route at "/" shows a demo of a possible implementation
 - the route at "/goals" shows all goals currently in the system
 - the route at "/goals/create" allows a user to create and submit a goal
 - the database is loaded in memory and is connected to our system
 - models are done
 - repositories are done
 - thymeleaf, flyway, jdbc, and spring security are all configured
