# Neo4j-API-Java-App
The Java-App provides services to Neo4j-API-Angular-GUI using Jersey-API-Client library.
This Java-App gives back informations provided by Neo4j-DBSM and sends to Angular-App for displaying.
We have to keep in mind that the Queries are written on Angular-side and send to Java-App and Java-App sends the query to Neo4j (using jersey-api).
Neo4j executes and returns the results to Java-App and Java gives them to Angular-App.
