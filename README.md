# Apsis RnD Java assignment
## Specifications
Your app should be implemented as a web service designed to be used by multiple clients.

It should expose a few methods:

- one to increment a named counter by 1
- one to get the current value of a given counter
- one to get a list of all counters and their current value
- one to create new counters

### OpenAPI link: 
 - Remove ID field from payload before try in POST method - (need to be fixed from swagger sample payload)  
http://localhost:8080/swagger-ui/index.html#/

### How to run

````
- Inside apsis directory just run (default port is 8080)
    mvn spring-boot:run
````


### create new counter:
````
POST /v1/counters/

 {
   "counterName":"fancyCounter",
   "initValue":1
 }
````

### Get all counters:
````
GET /v1/counters/
````

### Get specific counter:
````
POST /v1/counters/{counter_name}
````

### Increase a counter:
````
PUT /v1/counters/{counter_name}
````

in Post method backend will create UUID as a unique identifier for each counter.
