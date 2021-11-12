# Simple API Test

A simple program meant to learn Java and Spring Boot. The user database can be manipulated through the API endpoints.

## Installation

A PostgreSQL server is required to set up the database. Once the server is set up and running, navigate to:
```sh
src\main\resources
```
and
```sh
src\test\resources
```
And edit both **application.properties** files to your PostgreSQL server name and their respective username and password.

The user database is then automatically populated by a config file in the project when you run the program.

## Start
The application can then be run by running the **DemoApplicaton** located in:
```sh
src\main\java\demo
```
## Testing
Tests can be run with the **DemoApplicationTests** located in:
```sh
src\test\java\demo
```
