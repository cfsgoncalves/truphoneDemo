## Title

Challenge project for Truphone company

## Technologies Used

- Programming Language: Java (Spring Boot Framework)
- DB: PostgreSQL
- Migrations: Flyway
- Database Acess: JPA/Hibernate
- Unit testing: JUnit
- Logs: slf4j (Simple Logging Facade)
- Containerization: Docker Compose

## How to run the project

- Run `docker-compose up --build` to start the POSTGRESQL database
- Run `mvn clean install` to run the migrations to the database
- Run the project

## Main Features
- [x] POST REQUEST to add Device
- [x] GET Request to get device by identifier
- [x] GET Request to list all devices
- [x] PUT Request to update the devices
- [x] DELETE Request to delete a device from the db
- [x] Get Request to search device by brand
- [ ] Input validation

## Support features
- [x] Unit testing (some)
- [x] Integration Testing (some)
- [ ] End-to-end testing
- [x] Test Database separate from production database
- [x] Usage of migration service to support parallel development
- [x] Dockerize applications and databases (docker-compose)
- [ ] Run tests on the CI
