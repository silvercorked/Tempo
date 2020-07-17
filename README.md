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
 - Navigate to http://localhost:8080/users - This will create the test user required for further goal and tag creation.
 - Navigate to http://loaclhost:8080 - This will allow full access to the completed aspects of the Tempo App
 
## Functions:
 - Currently functional pages include: /, /create, /tags, /tags/create
 - Additionally the /users tag is used to create a test user as mentioned in the running instructions above.
   - Some pages may process as errors if the user is not first created.
 - Current functionality includes goal and tag creation, read, update, and delete (CRUD) functionality.

## Release Notes:
 - the route at "/" shows a demo of a possible implementation.
 - the route at "/goals" shows all goals currently in the system.
 - the route at "/goals/create" allows a user to create and submit a goal.
 - the database is loaded in memory and is connected to our system.
 - models are done.
 - repositories are done.
 - thymeleaf, flyway, jdbc, and spring security are all configured.
