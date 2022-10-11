## Movie API assignment for BB company
This is an assessment about handling a movie API with 2 different types of datasource.
The assessment consists of an API to be used for getting a movie if it won, rate the move , return top-ten.

# Introduction
Welcome to Movie API.

## The main features of this application:
1. The application should Indicate whether a movie won a “Best Picture” Oscar, given a movie’s title based on this API and this CSV file that
   contains winners from 1927 until 2010.
2. It should also allow users to give a rating to movies.
3. and provide a list of 10 top-rated movies ordered by
    box office value.

## Swagger:
 > http://localhost:8080/swagger-ui/index.html

## Postman:
 located in /postman/movie-api.postman_collection.json

## Movie API Diagram

![data-flow](https://user-images.githubusercontent.com/8404721/194955295-3e279b9d-ccd0-4b51-81dc-ef69c99fd616.jpg)

### Data flow /won API

![data-flow-won](https://user-images.githubusercontent.com/8404721/195102785-21b86a33-458a-4dc9-b035-774f09ca0662.jpg)


## Test Coverage
* 100% Class
* 93% Method
* 91% Line

* Note: for Top-Ten rated by Movie API website, in response you will see the link of movie not the movie. If you need you can use this link to get the movie.
        It helps us to don't communicate a lot with Omdb server (external server).

* Omdb API, does not response a different Http status for error. Instead it returns
```json
{"Response":"False","Error":"Something went wrong."}
```
For checking error must read the response body, then check field Response.


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