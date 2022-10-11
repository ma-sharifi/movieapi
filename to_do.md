### What is missing and why:
to-do list with things you would add if you have more time or explaining what is missing and why etc

* Add more detailed error code and message. Map detailed error to not detailed error code and send to client. It needs more time.
* Use Keycloak as s security server and integrate it with application. Because it Increased dependency I did not add it.
* Dockerize application. Did not mention for our project.
* Use Github action for CI/CD. Did not mention for our project.
* If our customers increase we can partition data to different clusters based on username.
* Network is not reliable, we can put top-ten movie task for fetch into a MQ like Kafka or Redis Pub/Sub then process them.
* Use Redis as a cache putting fetch movie on it and keep them for a period of time like one day. It helps to don't call Omdb API a lot.
* Use CompletableFuture for calling third-party at the same time for fetching top-ten movies. For simplicity I didn't add it.
* Use circuit breaker pattern like Resilience4j for handling Omdb API (third-party) calls. For simplicity I didn't add it.
* Put log to ELK for monitoring (Or Prometheus and Grafana). It takes time to add to our project.
* Add more tests. I need more time.
* Use the same format for JSON serialization. Like first_name instead of FirstName; It needs more time.
* Find movie by title and ImdbID at the same time. n

### Why are they missed: 
* Due to the deadline has been set there is not enough time for complete them.
* For simplicity. Each technology you add, it will increase the complexity.
* If you want to get more than **1,000 daily call** you MUST buy a Patreon Account Type. 
  If not, you must to creat  10,000 accounts to meet 10,000,000 request per day and use them for your request.
* Due to simplicity I did not put the list of top-ten in the local cache and read them when I need. 


