## Movie API assignment for BB company
The purpose of this assessment is to assess how to handle two different types of movie API data sources.

# Introduction
Welcome to Movie API.
The project consists of an API to be used for getting a movie if it won, rate the move , return top-ten.

## The main features of this application:
1. The application should Indicate whether a movie won a “Best Picture” Oscar, given a movie’s title based on this API and this CSV file that
   contains winners from 1927 until 2010.
2. It should also allow users to give a rating to movies.
3. and provide a list of 10 top-rated movies ordered by
    box office value.

## Swagger:
 > http://localhost:8080/swagger-ui/index.html

## Postman:
 > Postman API file is located in [here](postman/movie-api.postman_collection.json)

## Movie API Diagram

![data-flow](https://user-images.githubusercontent.com/8404721/194955295-3e279b9d-ccd0-4b51-81dc-ef69c99fd616.jpg)

### Data flow /won API

![data-flow-won](https://user-images.githubusercontent.com/8404721/195102785-21b86a33-458a-4dc9-b035-774f09ca0662.jpg)

### Entity diagram

![entity](https://user-images.githubusercontent.com/8404721/195184747-972367af-92ef-4ae2-a73f-c08892976c88.jpg)



## Test Coverage
* 100% Class
* 92% Method
* 90% Line

## Other Documents
* You can find diagrams in path [here](diagram)
* My assumptions when solving the challenge, you can find [here](assumptions.md)
* A short explanation about how to run the solution with all the needed parts, you can find [here](how_to_run.md)
* A file explaining what needs to be done to use the service, you can find [here](how_to_test.md)
* A short description of the solution and explaining some design decisions, you can find [here](solution.md)
* A description of how it will scale when the number of grows, you can find [here](scale.md)
* A to-do list with things I would add if I have more time and explaining what is missing and why, you can find [here](to_do.md)



