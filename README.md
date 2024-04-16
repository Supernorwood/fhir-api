**Project Name**

FHIR CRUD APIs using Java Springboot

**Description**

This project implements a RESTful API using Java Spring Boot and a FHIR library to manage Patient and Observation resources according to the HL7 FHIR standard. The API provides functionalities for CRUD (Create, Read, Update, Delete) operations on these resources.

**Prerequisites**

* Java Development Kit (JDK) 11 or later ([https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
* Maven build tool ([https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi))
* A FHIR library for Java (e.g., HAPI FHIR ([https://hapifhir.io/hapi-fhir/docs/getting_started/downloading_and_importing.html](https://hapifhir.io/hapi-fhir/docs/getting_started/downloading_and_importing.html)))

**Installation**

1. Clone this repository:

   ```bash
   git clone [https://github.com/your-username/fhir-crud-java-springboot.git](https://github.com/Supernorwood/fhir-api.git)
   ```

2. Navigate to the project directory:

   ```bash
   cd fhir
   ```

3. Install dependencies:

   ```bash
   mvn clean install
   ```

**Running the Application**

1. Start the application:

   ```bash
   mvn spring-boot:run
   ```

2. Access the API endpoints (replace `localhost` with the actual server address if deployed elsewhere):

   * **Create Patient:**
     * Method: POST
     * URL: `http://localhost:8080/fhir/Patient`
     * Request Body: FHIR Patient resource in JSON format

   * **Read Patient:**
     * Method: GET
     * URL: `http://localhost:8080/fhir/Patient/{id}`
     * Path Parameter: `{id}` - The ID of the Patient resource

   * **Update Patient:**
     * Method: PUT
     * URL: `http://localhost:8080/fhir/Patient/{id}`
     * Path Parameter: `{id}` - The ID of the Patient resource
     * Request Body: Updated FHIR Patient resource in JSON format

   * **Delete Patient:**
     * Method: DELETE
     * URL: `http://localhost:8080/fhir/Patient/{id}`
     * Path Parameter: `{id}` - The ID of the Patient resource

   (Similar endpoints exist for Observations, following FHIR conventions)

**Testing**

This project includes unit tests for each API endpoint using JUnit 5. These tests are currently under development and will be further enhanced in upcoming commits.

**FHIR Library Configuration**

(Optional) If you're using a specific FHIR library, you might need to configure its settings in the Spring Boot application properties file (`application.properties`). Refer to the library's documentation for configuration details.

**Deployment**

You can deploy the application as a WAR file to a servlet container like Tomcat or embed it within a standalone Spring Boot application server.
