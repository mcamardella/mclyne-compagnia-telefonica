# mclyne :o:
Implementazione di una Spring Boot Application che permette di gestire un operatore di telefonia fissa e mobile.
L'applicazione implemeterà le seguenti funzionalità:
- API REST per la gestione del cliente;
- API REST di attivazione, sospensione e cessazione della linea fissa o mobile;
- API REST per il tracciamento delle chiamate;
- API REST di reportistica.

## :fast_forward: Tecnologie
Spring Boot: crea un'API CRUD utilizzando PostgreSQL e PgAdmin con Lombok, Spring Data, Swagger 3 (OpenAPI 3) per API di documentazione e SLF4J per Logback

-	Spring Boot 2.6.7;
-	Spring Boot DevTools;
- Spring Data - JPA + Hibernate;
-	Lombok;
-	Creation of REST API;
-	Using all HTTP verbs;
-	Data transmission in JSON format;
-	Using PostegreSQL + PgAdmin;
-	API documentation with Swagger 3 (OpenAPI 3);
-	SLF4J for Logback.

## :fast_forward: AWS
Il JAR dell'applicazione è hostato su un'istanza di <b>EC2</b> (Linux), mentre il Database è pubblicato su un'istanza di <b>RDS</b>
#### Endpoint API CRUD
```
 http://54.167.83.14:80900/api/v1 + URI
```
#### Endpoint Swagger
```
 http://54.167.83.14:8090/api/v1/swagger-ui/index.html
```


## :fast_forward: Modello E/R
•	Nella radice del repository è presente il file `Diagrams-DB-ER.png` che mostra la stuttura del database.

## :fast_forward: Postman
•	Per eseguire i test dei servizi, segui il file `mclyne.postman_collection.json` che si trova nella radice del repository da importare in Postman .
