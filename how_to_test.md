# How to test movie service?
There are three services in Movie API:
* Get movie details if the movie won Oscar of `Best Picture` base of its `title` .  
 **GET** `/v1/movie/won`
* User give rate to movies based on `title` with range 0 to 10  
  **POST**`/v1/movie/rate`
* GET top-ten rated movies based on users rates  
  **GET**`/v1/movie/top-ten`

