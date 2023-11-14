# Online Manga Shopping Application Backend

Skills used: Java, Spring Framework, Spring Boot, MongoDB, MongoDB Compass, MySQL, MySQL Workbench, Spring Cloud(Circuit Breaker,Connectors,Gateway,Netflix,Security,Sleuth,Config), Spring MVC, Netflix Eureka, Spring Cloud Gateway, Lombok library, Postman, Testcontainers library, Spring Tool Suite 4, Maven, JUnit 5, Docker, Keycloak, Zipkin, Apache Kafka.

This application contains:
-Manga Service - Manga Catalog, create and view Manga products - mongoDB database.
-Order Service - the service that helps with the manga order - mySQL database.
-Inventory - Service - can check if the manga product is in stock - mySql database to store all the inventory information.
-Eureka Discovery Server - is storing all the information about the services, helps with the comunication between services.
-API Gateway - acts like a gateway to route the requests from users to the coresponding services, this way the user doesn't need to have 
access to the host name or the IP adress of services, just to the Gateway one.
-Notification Service - after the order is placed, we can send notifications using Kafka - does not have a database but it is responsible to send notifications.

To be able to access API Gateway resulting in accessing all the services, you need to pass the authorization server called keyclock.
When an order is placed, we will first check if the inventory is available or not by making a synchronous comunication. 
Once the order is placed successfully, we will send a notification using an asynchronous communication between order service and notification service.
This application also uses Zipkin to do the distributed tracing. It help us trace the request from start to finish so that if a request is failed 
at any point of time, we can understand where and why it failled.
All the services, API Gateway, Eureka Discovery Server, Keycloack, Zookeeper, Broker, Zipkin, databases are dockerized and can be run with docker compose.

Next step that can be done with this application is to create a frontend.


*Manga Service completed - MongoDB

<img width="705" alt="Docker Container" src="https://github.com/louissardaru/Manga-Store/assets/119102199/410790c9-09cd-439a-8414-2d70b29cf9f2">
<img width="362" alt="POST Manga" src="https://github.com/louissardaru/Manga-Store/assets/119102199/31fc88d7-3192-4f0e-a881-5697e0d105fc">
<img width="338" alt="GET Manga" src="https://github.com/louissardaru/Manga-Store/assets/119102199/be00e709-3c9a-4ba8-9823-139a8a10f2f0">
<img width="348" alt="manga_service_mongoDB" src="https://github.com/louissardaru/Manga-Store/assets/119102199/6a194cc0-abdf-45f6-8ec1-0aeb509240cc">

*Order Service completed - MySQL

<img width="348" alt="POST Order" src="https://github.com/louissardaru/Manga-Store/assets/119102199/62ca3296-6c0d-4cdb-8bc1-7ed2a9841025">

*Inventory Service completed - MySQL

<img width="348" alt="Inventory_mySQL" src="https://github.com/louissardaru/Manga-Store/assets/119102199/6828af5f-7bb3-4dd3-a9d2-637ebef96547">

*"Integrating all services in a project" completed

<img width="348" alt="integrating in a project" src="https://github.com/louissardaru/Manga-Store/assets/119102199/54a508a7-3816-48dd-961c-7399f9f6551f">

*Sync Communication between Order and Inventory services

<img width="848" alt="not in stock" src="https://github.com/louissardaru/Manga-Store/assets/119102199/fe2640fa-7658-42b5-aae1-f619fcda4eee">
<img width="348" alt="not in stock 1" src="https://github.com/louissardaru/Manga-Store/assets/119102199/537e58f0-7dc1-4104-93b7-0407d720ec6d">

*Discovery Server with Netflix Eureka created

<img width="700" alt="Eureka_Server" src="https://github.com/louissardaru/Manga-Store/assets/119102199/86ccd56b-0bea-43b9-a9ec-83dc9ea22c2c">

*API Gateway with Spring Cloud Gateway

<img width="348" alt="API Gateway" src="https://github.com/louissardaru/Manga-Store/assets/119102199/1993968f-8aab-4b3f-aa83-b51a3b55fa7c">

*Keycloak security added

<img width="648" alt="KEYCLOAK" src="https://github.com/louissardaru/Manga-Store/assets/119102199/275b201c-f4af-4129-a454-47bb4cca410d">
<img width="348" alt="KEYCLOAK and Postman" src="https://github.com/louissardaru/Manga-Store/assets/119102199/620e0ab5-d017-4d5b-bb7a-317165257de2">

*Circuit breaker and Distributed tracing implementation completed

<img width="348" alt="Zipkin" src="https://github.com/louissardaru/Manga-Store/assets/119102199/48854812-676d-4293-aafc-c8c016dfe80a">
<img width="348" alt="Zipkin into" src="https://github.com/louissardaru/Manga-Store/assets/119102199/903db9b4-5312-4311-a0c1-18dfee5a4fc8">

*Notification Service with Apache Kafka and interaction with Order Service

<img width="348" alt="docker" src="https://github.com/louissardaru/Manga-Store/assets/119102199/859ad705-431f-4cfb-9eaa-0e69ba9ec6c4">
<img width="348" alt="Notification service" src="https://github.com/louissardaru/Manga-Store/assets/119102199/8b6509fb-7135-493a-a57b-53669063c399">

*Dockerizing the application

<img width="348" alt="doker_site" src="https://github.com/louissardaru/Manga-Store/assets/119102199/1ea87e70-5309-4685-a233-d45f6637afb6">
<img width="398" alt="docker-desktop" src="https://github.com/louissardaru/Manga-Store/assets/119102199/74cc351f-35d3-4f05-b5fd-e2047d7d5eb4">






