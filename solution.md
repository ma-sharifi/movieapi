### What is solution? a short description of the solution and explaining some design decisions

To get movie information from multiple sources, I implemented a REST application using the Omdb API and a CSV file.
The user sends a movie's title then the application checks the CSV file to determine whether this movie is a winner of the Best Picture Oscar or not.
After we find the movie won the Best Picture Oscar award, we will fetch its data by title from Omdb API and show them to the user.

![data-flow-won](https://user-images.githubusercontent.com/8404721/195102785-21b86a33-458a-4dc9-b035-774f09ca0662.jpg)


### Omdb API Latency
Average latency of calling the Omdb API is 50ms. it means in average without a concurrent system we can have (a day is 24 * 60 * 60 * 1000 ms) / (50ms latency of each request) = 1,728,000 Request per day per thread.
If wee need to meet 10,000,000 we need 6 thread. In peak time base our needs we need more thread.
Added the movie **url**, instead of the data of the movie into top-ten json response, instead call Omdb for each movie; It is shown in the figure below 
It helps don't call the Omdb API for each item in response of top-ten.
```json
{
    "title": "The Green Mile",
    "url": "http://localhost:8080/v1/movies/tt0120689",
    "rate_average": 10.0
}
```

### CSV file
While application is loading, Read the file and put all information into Arraylist. We cached all data of the file. It improves performance.
Used an Arrays List not a synchronized List like CopyOnWriteArrayList, because our csv file will not change in the future, and we don't need to update our cache.
Arrays List has more performance in read operation more than CopyOnWriteArrayList. 

### Omdb API
In top-ten service, For getting data of the movie to show to the user, we need to 10 times call the Omdb API. I've got 2 options.
1. In order to retrieve top-ten movie information, One option was using CompletableFuture for calling third parties at the same time. It helps us when me need to have a bach call. For simplicity I did not use it.
2. Using HATEOAS. In this way, It helps us don't call the Omdb API, when the user need can use the link to get the movie.
in the /rate service I added the imdb_id to database along with rate, box office and other data I need.
imdb_id helps me to generate a url to a movie and when the user needs it, can reach the movie by calling this link.
*Note: I defined `GET /v1/movies/{imdb-id}` API for translate link that provided in top-ten list to movie object.
I Defined a interface for Omdb. It help us with strategy pattern and adapter pattern easily add another IMDB API to our application.
   
### ResponseDto
There is a ResponseDto object. our response consist of this object. This object has a List<T> payload. 
Client **MUST** find the body of the response here. Due to simplicity for client, payload is always a list. As a result, client just need process this field.
We know an object is an array with a length of one.

### Security
All APIs protect by Bearer token except /login. For simplicity is used Spring Security. For production, I will use Keycloak.
I used a facade for authentication. It helps to retrieve the authentication everywhere,not just in @Controller beans.
Also This facade encapsulates a complex subsystem behind a simple interface. It defines a higher-level interface that makes the subsystem easier to use.
In the future we can add Keycloak to our project as well.

###Test
For testing when we need to connect to Omdb API, I used MockRestServiceServer for mocking Omdb API server. It helps to have a
solidarity test, without dependency to Third Party and just test our functionality.
Tried to have different type of test.
You can test the application 4 different ways. Swagger, Postman, mvn Test, HTTPie CLI.

### Exception
Defined different Exceptions for different situations.
Provided a Global Exception handler to help handle exceptions in an easy way.

### Mapping
I used MapStruct for mapping entity to Dto and vice versa. Mapstruct is compile time not runtime. It helps to have a better speed.

###Endpoint
**v1/movies/won**
According to the movie's `title`, you can find out if the film won the Oscar for Best Picture.
1. Check CSV file for title and Best Picture Oscar movie category.
2. If Movie found, we will consider is it a winner or not. If it is not a winner we will return HTTP Status 200 and error_code 1. It depends on our business.
   we can return 400 as well. We need to compromise with client about this code.
3. if was won, we call Omdb API to get the information of movie. Added a field of `"won_best_picture": true` to it. Return HTTP Status 200, error_code-0
   "message": "Success :-)";

####Notes:
* Due to the lack of Category in Omdb API, first of all file was checked for "Best Picture".
* Content of OMDB API: "Title": "Inception", "Awards": "Won 4 Oscars. 157 wins & 220 nominations total",
* Content of CSV FILE: 2010 (83rd),Best Picture,The King's Speech,"Iain Canning, Emile Sherman and Gareth Unwin, Producers",YES,,,,,,

**v1/movies/rate**
- Get top-ten rated movies based on users' rates
1. Get the movie information from Omdb API
2. Save Title, Box Office, Rate, ImdbId , and Username on database.
3. After creating resource, we return a body with a link to the movie. We could replace it with no body and put the url of our resource in `Location` HTTP Header.

**v1/movies/top-ten**
It retrieve top 10 rated movie with the link to teh movie, that all users voted them order by box office.
1. We run a query,
2. After find the result, Added the link of the movie into each record, in order to reach the movie with this link if user wants. (HATEOAS)

**v1/users/login**
Issue token
* Note:
    1. User already registered and we persist them.
    2. Assumed we checked the user and password and found they are a match. then we issued a token.
    3. This endpoint is not protected.
1. We issue a JWT token and set the subject our user.
2. Put the Bearer token into body(for simplicity) and Authorization HTTP Header.
3. Return 200 if everything goes well.