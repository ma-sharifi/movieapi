## Movie API assignment for BB company
This is an assessment about handling a movie API with 2 different types of datasource.
The assessment consists of an API to be used for getting a movie if it won, rate the move , return top-ten.

# Introduction
Welcome to Movie API.

## The main features of this mini bank:
1. The application should Indicate whether a movie won a “Best Picture” Oscar, given a movie’s title based on this API and this CSV file that
   contains winners from 1927 until 2010.
2. It should also allow users to give a rating to movies.
3. and provide a list of 10 top-rated movies ordered by
    box office value.

# Documentation
## Swagger:
 > http://localhost:8080/swagger-ui/index.html

## Movie API Diagram
![data-flow](https://user-images.githubusercontent.com/8404721/194955295-3e279b9d-ccd0-4b51-81dc-ef69c99fd616.jpg)

* Note: for Top-Ten rated by Movie API website, in response you will see the link of movie not the movie. If you need you can use this link to get the movie.
        It helps us to don't communicate a lot with Omdb server (external server).

* Omdb API, does not response a different Http status for error. Instead it returns
```json
{"Response":"False","Error":"Something went wrong."}
```
For checking error must read the response body, then check field Response.
