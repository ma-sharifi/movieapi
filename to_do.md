### What is missing and why:

* Tried to have a simple application not complex application
* Add Postman API file to project with test.
* Add more detailed error code and message. Map detailed error to not detailed error code and send to client 
* Use Keycloak as s security server and integrate it with application.
* Dockerize application
* Use Github action for CI/CD
* For Production use a persistent database like MariaDB
* If our customers increase we can partition data to different clusters based on username.
* Network is not reliable, we can put top-ten movie task for fetch into a MQ like Kafka or Redis Pub/Sub then process them.
* Use Redis as a cache putting fetch movie on it and keep them for a period of time like one day. It helps to don't call Omdb API a lot.
* Use CompletableFuture for calling third-party at the same time for fetching top-ten movies
* Use SonarQube for checking code smells.
* Use circuit breaker pattern like Resilience4j for handling Omdb API (third-party) calls. 
* Put log to ELK for monitoring (Or Prometheus and Grafana)
* Add more unit tests
* Use the same format for JSON serialization. Like first_name instead of FirstName;
* Find movie by title and ImdbID at the same time.

### Why are they missed: 
* Due to the deadline has been set there is not enough time for complete them.
* For simplicity. Each technology you add, it will increase the complexity.
* If you want to get more than **1,000 daily call** you MUST buy a Patreon Account Type.

