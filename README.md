# Dummy Portal (Spring Boot + MySQL + BCrypt)

## Run
1. Install JDK 17+, Maven (or use VS Code Maven extension), MySQL.
2. Edit src/main/resources/application.properties and set your MySQL password.
3. Run: mvn spring-boot:run (or run DummyportalApplication in VS Code).
4. Open requests.http in VS Code (REST Client extension) and send the requests in order.

## Endpoints
- POST /api/branches      create branch {name, code}
- GET  /api/branches      list branches
- POST /api/auth/signup   {fullName, email, password, branchId}
- POST /api/auth/login    {email, password}

Passwords are stored as BCrypt hashes in users.password.
