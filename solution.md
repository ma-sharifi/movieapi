### What is solution?
A REST API application to get movie information from multiple data source Omdb API and CSV. 
User send a movie's title then application check the CSV file whether this movie is a winner of the Best Picture Oscar or not.
After we found movie won the Best Picture Oscar awards we will fetch its data by title from Omdb API and show them to user.

Defiened different Exception for different situation.

Use link into top-ten json, instead call Omdb for each movie;
Average latency of calling the Omdb API is 50ms. it means in average without a concurrent system we can have (a day is 24*60*60*1000 ms) / (50ms latency of each request) = 1_728_000 Request per day

All APIs protect by Bearer token except /login.

### CSV file
While application is loading, Read the the file and put all information into Arraylist.
Used an Arrays not a synchronized List like CopyOnWriteArrayList, because our csv file will not change in the future, and we dont need to update our cache. It improves performance.

### CSV file
While application is loading, Read the the file and put all information into Arraylist.
Used an Arrays not a synchronized List like CopyOnWriteArrayList, because our csv file will not change in the future, and we dont need to update our cache. It improves performance.


### ResponseDto
There is a ResponseDto object. our response consist of this object. This object has a List<T> payload. 
Order to client find the body of the response here. Due to simplicity for client, payload is alwyas a list. As a result, client just need process this field.
It supports arrays and objects. An object is an array with a length of one.

###Test
For testing when we need to connect to Omdb API, I used MockRestServiceServer for mocking Omdb API server. It helps to have a
solidarity test, without dependency to Third Party and just test our functionality.
Tried to have different type of test.

### Endpoints

**v1/movies/won**
Check is movie was won a Best Picture Oscar, return the information of the movie to user.
1. Check CSV for title and Best Picture Oscar movie
2. If Movie found, we will consider is it a winner or not. If it is not a winner we will return HTTP Status 404 and error_code 4043. It depends on our business.
    we can return 200 as well. We need to compromise with client about this code.
3. if was won, we call Omdb API to get the information of movie. Added a field of `"won_best_picture": true` to it. Return HTTP Status 200, error_code-0
    "message": "Success :-)";

Notes:
* Due to the lack of Category in Omdb API, first of all file was checked for "Best Picture".
* OMDB API->"Title": "Inception", "Awards": "Won 4 Oscars. 157 wins & 220 nominations total",
* CSV FILE-> 2010 (83rd),Best Picture,The King's Speech,"Iain Canning, Emile Sherman and Gareth Unwin, Producers",YES,,,,,,

**v1/movies/rate**
User can rate to a movie once.
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


