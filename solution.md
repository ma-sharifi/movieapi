### What is solution? a short description of the solution and explaining some design decisions

I Implemented REST application to get movie information from multiple data sources Omdb API and a CSV file.
The user sends a movie's title then the application checks the CSV file to determine whether this movie is a winner of the Best Picture Oscar or not.
After we find the movie won the Best Picture Oscar award, we will fetch its data by title from Omdb API and show them to the user.
Defiened different Exception for different situation.

Use link into top-ten json, instead call Omdb for each movie;
Average latency of calling the Omdb API is 50ms. it means in average without a concurrent system we can have (a day is 24 * 60 * 60 * 1000 ms) / (50ms latency of each request) = 1_728_000 Request per day

### CSV file
While application is loading, Read the file and put all information into Arraylist. We cached all data of the file. It improves performance.
Used an Arrays List not a synchronized List like CopyOnWriteArrayList, because our csv file will not change in the future, and we don't need to update our cache.
Arrays List has more performance in read operation more than CopyOnWriteArrayList. 

### Omdb API
In top-ten service, For geting data of the movie to show to the user, we need to 10 times call the Omdb API.
1. I could use CompletableFuture for calling third-party at the same time for fetching top-ten movies information. It help us when me need to have a bach call.
2. Using HATEOAS. In this way, It help us don't call the Omdb API, when the user need can use the link to get the movie.
in the /rate service I added the imdb_id to database along with rate, box office adn other data I need.
imdb_id helps me to generate a link to a movie and when the user needs it, can reach the movie by calling this link.
Instead of calling the Omdb API every time, I just added a link to the movie to my object.
*Note: Defined GET /v1/movies/{imdb-id} API for translate link that provided in top-ten to Movie object we get from Omdb API.
   
### ResponseDto
There is a ResponseDto object. our response consist of this object. This object has a List<T> payload. 
Order to client find the body of the response here. Due to simplicity for client, payload is alwyas a list. As a result, client just need process this field.
It supports arrays and objects. An object is an array with a length of one.

### Security
All APIs protect by Bearer token except /login. For simplicity is used Spring Security. For production, I will use Keycloak.

###Test
For testing when we need to connect to Omdb API, I used MockRestServiceServer for mocking Omdb API server. It helps to have a
solidarity test, without dependency to Third Party and just test our functionality.
Tried to have different type of test.
You can test the application 4 different ways. Swagger, Postman, mvn Test, HTTPie CLI.

### Exception
Defined different Exceptions for different situations.
Provided a Global Exception handler to help handle exceptions in an easy way.

### Top-Ten
I can put the list of top-ten in the local cache and read them when I need, for simplicity I did not do it.


