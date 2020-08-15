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
 - Navigate to http://localhost:8080 - This is the home page for the Tempo app.
 - Click register to create an account, and login to start creating goals!
 
## Functions:
 - Currently functional pages include: /, /create, /tags, /tags/create
 - Additionally the /users tag is used to create a test user as mentioned in the running instructions above.
   - Some pages may process as errors if the user is not first created.
 - Current functionality includes goal and tag creation, read, update, and delete (CRUD) functionality.

## Release Notes:
 - the route at "/" is the main landing page of the Tempo application.
 - the route "/users" creates allows administrative users to manage users.
 - the route "/goals" allows users to manage their personal goals.
 - the route "/tags" allows users to manage tags.
 - the database is loaded in memory and is connected to our system.
