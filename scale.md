### How we can scale the application?
a description of how it will scale when the number of users/agents/consumers grows from 100 per day to 10_000_000 per day,
and what changes would have to be made to keep the same quality of service

* Use Redis as a cache putting fetch movie on it and keep them for a period of time like one day. 
   It helps to don't call Omdb API a lot. Because we know most of the information about movies will not change except rates. 
   The time of caching data is up to our business.
* Based on Scale Qube, We can partition data in our database (scale by splitting similar things) to different clusters by username. 
* Network is not reliable, we can put top-ten movie task for fetch into a MQ like Kafka or Redis Pub/Sub then process them.
* Use CompletableFuture for calling third-party at the same time for fetching top-ten movies
* Dockerize application for deploy on any cloud native platform
* Use a separate server for security. Use Keycloak as s security server (Decomposition of /v1/user from current app) and integrate it with application and deploy it on cloud.
* Use a cloud database.
* Use circuit breaker pattern like Resilience4j for handling Omdb API (third-party) calls.
* Put log to ELK for monitoring (Or Prometheus and Grafana) for finding issue before they become serious.