# spring-boot-postgresql-docker
# Getting Started

## Service Description
The service simulates an e-commerce platform where all users can create the products playing the role of admin. The Order and OrderItem need a session for the manipulations playing the role of customer.
The session is configured into the property file for 30min. User need to create his own at the session endpoint.
The service is built with 3 tables map as following:

OrderItem Entity:

- Represents an order item.
- Each order item is associated with one order (Orders entity).
- Relationship is Many-to-One, meaning many order items can belong to one order.
- The foreign key 'order_id' in the OrderItem table links to the primary key of the associated order in the Orders table.

Orders Entity:

- Represents an order.
- Contains a list of order items (OrderItem entities).
- The relationship is One-to-Many, meaning one order can have multiple order items.
- The mappedBy attribute specifies that the 'orderItems' list in the Orders entity is the owner of the relationship.

Product Entity:

- Represents a product.
- It's independent and doesn't define any specific relationships with other entities.


## Run and test the service
The following guides illustrate how to run and test the service concretely:
#### Build the project using gradle
- From the root of the project, build the jar file using `gradle build` command
   
#### Deployed the service image to docker. "I used Docker Desktop" 
- From the root of the project run these commands:
- ` docker build -t your-choosing-image-name . `
- ` docker-compose up `
 
#### Test the service. The service provides Swagger-UI for the documentation and the testing.
   - Browse the service at the port 9090. Example :`http://localhost:9090/swagger-ui/index.html#/`
   - Test the service by following these actions for the creation of Product, Order and OrderItem:
     1. ` create your session under session endpoint ` Example: paul.  The session gives you the access for the Order and OrderItem API's.
     2. ` 1st create a Product at the Products endpoint `
     3. ` 2nd create an OrderItem at the OrderItems endponit using the productId got at 1st step `
     4. ` 3rd create an Order at the Order endpoint using the productId and the orderId get from DB `

## Access the PostgreSQL on Docker via pgAdmin4. 

Log in to pgAdmin4:

- Open a web browser and go to http://localhost:5050. 
- Log in using the credentials specified in the docker-compose.yml file.

Add PostgreSQL Server:

- On the left sidebar, under "Quick Links," click on "Add New Server."
- Provide a name for your server.

In the "Connection" tab:

- Hostname/address: Use the name of your PostgreSQL service defined in Docker Compose file. In this case, it's psql-db.
- Port: 5432 (default PostgreSQL port).
- Maintenance database: The name of PostgreSQL database, in this case, it's commerce.
- Username and Password: The PostgreSQL username and password, in this case, it's root.
- Click "Save" to add the server.

Access PostgreSQL Database:

- In the left sidebar, expand the server you added under "Servers."
- Under the server, you will see the connected PostgreSQL database (commerce in this case). Expand it.


## Docker Compose support
This project contains a Docker Compose file named `docker-compose.yaml`.
This Docker Compose file defines a multi-container setup for the application with 3 containers:

psql-db Container:
  
- Running a PostgreSQL database named "commerce" with user "root" and password "root".
- Provides a service accessible on port 5432.

commerce Container:
  
- Building and running a Java application from a custom Dockerfile.
- Exposing the application on port 9090.
- Connecting to the PostgreSQL database (psql-db) on port 5432.

pgadmin Container:
  
- Setting up pgAdmin4, a web-based PostgreSQL administration tool.
- Accessible through a web interface on port 5050.
- Default login credentials: user@example.com and admin.

## Dockerfile file
This project contains a Dockerfile file named `dockerfile`.
This Dockerfile is used to build a Docker image for a Java application:

1. Base Image: It starts with the official OpenJDK 17 image.
2. Volume: Creates a volume at '/tmp' within the container.
3. Exposed Port: Specifies that the application inside the container will be accessible on port 9090.
4. Argument (ARG): Defines a build argument 'JAR_FILE' with a default value pointing to a JAR file in the 'build/libs' directory.
5. Copy: Copies the specified JAR file (determined by the 'JAR_FILE' argument) into the root directory of the container, naming it 'Ecommerce-0.0.1-SNAPSHOT.jar'.
6. Entry Point: Sets the entry point for the container to run the Java application using the 'java -jar' command with the copied JAR file as an argument.

## openapi file
This project contains an openapi file named `ecommerce-api-v1.yaml`.

## Code Coverage
- This project contains a report for code coverage at the location: `reports`
- Note that the entites and dto's classes are not tested.


## Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.2.1/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.2.1/reference/htmlsingle/index.html#data.sql.jpa-and-spring-data)
