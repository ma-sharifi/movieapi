### How we can scale the application?
a description of how it will scale when the number of users/agents/consumers grows from 100 per day to 10_000_000 per day,
and what changes would have to be made to keep the same quality of service

* Use Redis as a cache putting fetch movie on it and keep them for a period of time like one day. 
   It helps to don't call Omdb API a lot. **Because we know most of the information about movies will not change except rates**. 
   The time of caching data is up to the customer's need.
* Put the list of the top ten movies into a cache (Redis) and get the top-ten list from It. We can invalidate the cache data based on our customer needs.
* Based on Scale Qube, we can partition data in our database (scale by splitting similar things) to different clusters by username.
* Network / third-party server is not reliable, we can put the list of movies that we need to get from the Omdb API into an MQ like Kafka or Redis Pub/Sub then read them from MQ and send our request them to Omdb API.
* Use CompletableFuture for calling third-party to fetch more than one movie at the same time.
* Use circuit breaker pattern like Resilience4j for handling Omdb API (third-party) calls.
* Dockerize application for deploy on any cloud native platform
* Use a separate server for security. Use Keycloak as s security server (Decomposition of /v1/user from the current app) and integrate it with the application and deploy it on a cloud.
* Put log to ELK for monitoring (Or Prometheus and Grafana) for finding issue before they become serious.
* Use a cloud database.
* Add CI/CD pipeline.

