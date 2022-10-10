# How to test service?

There are three services in Movie API:
* Get movie details if the movie won Oscar of **Best Picture** base of its title.  
 **GET** `/v1/movies/won`

```bash
http GET "localhost:8080/v1/movies/won?title=Hurt Locker" 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw'
```

```json
{
  "error_code": 0,
  "message": "Success :-)",
  "payload": [
    {
      "Actors": "Jeremy Renner, Anthony Mackie, Brian Geraghty",
      "Awards": "Won 6 Oscars. 125 wins & 130 nominations total",
      "BoxOffice": "$17,017,811",
      "Country": "United States",
      "DVD": "12 Jan 2010",
      "Director": "Kathryn Bigelow",
      "Genre": "Drama, Thriller, War",
      "Language": "English, Arabic",
      "Metascore": "95",
      "Plot": "During the Iraq War, a Sergeant recently assigned to an army bomb squad is put at odds with his squad mates due to his maverick way of handling his work.",
      "Poster": "https://m.media-amazon.com/images/M/MV5BYWYxZjU2MmQtMmMzYi00ZWUwLTg2ZWQtMDExZTVlYjM3ZWM1XkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg",
      "Production": "N/A",
      "Rated": "R",
      "Ratings": [
        {
          "Source": "Internet Movie Database",
          "Value": "7.5/10"
        },
        {
          "Source": "Rotten Tomatoes",
          "Value": "97%"
        },
        {
          "Source": "Metacritic",
          "Value": "95/100"
        }
      ],
      "Released": "31 Jul 2009",
      "Response": "True",
      "Runtime": "131 min",
      "Title": "The Hurt Locker",
      "Type": "movie",
      "Website": "N/A",
      "Writer": "Mark Boal",
      "Year": "2008",
      "imdbID": "tt0887912",
      "imdbRating": "7.5",
      "imdbVotes": "450,027",
      "won_best_picture": true
    }
  ]
}
```

When movie does not exist
```bash
http GET "localhost:8080/v1/movies/won?title=Hello Mahdi" 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw'```
```

```json
{
    "error_code": 4041,
    "message": "Could not find the movie: Hello Mahdi ;Based on CSV file",
    "payload": []
}
```

When movie was not won
```bash
http GET "localhost:8080/v1/movies/won?title=Black Swan" 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw'```

```json
{
  "error_code": 4043,
  "message": "Based on csv file movie was not won an Oscar of Best Picture with title: Black Swan",
  "payload": []
}
```


* User give rate to movies based on `title` with range 0 to 10  
  **POST**`/v1/movies/rate`

```bash 
  http POST localhost:8080/v1/movies/rate rate=9 title="Black Swan" 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw'
 ```

```json
{
  "error_code": 0,
  "message": "Success :-)",
  "payload": [
    {
      "box_office": 106954678,
      "imdb_id": "tt0947798",
      "rate": 9,
      "title": "Black Swan",
      "url": "http://localhost:8080/v1/movies/tt0947798",
      "username": "mahdi"
    }
  ]
}
```

* Find by imdb ID:

```bash 
http GET localhost:8080/v1/movies/tt0371746 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw' ```
```

```json
{
    "error_code": 0,
    "message": "Success :-)",
    "payload": [
        {
            "Actors": "Robert Downey Jr., Gwyneth Paltrow, Terrence Howard",
            "Awards": "Nominated for 2 Oscars. 21 wins & 73 nominations total",
            "BoxOffice": "$319,034,126",
            "Country": "United States, Canada",
            "DVD": "30 Sep 2008",
            "Director": "Jon Favreau",
            "Genre": "Action, Adventure, Sci-Fi",
            "Language": "English, Persian, Urdu, Arabic, Kurdish, Hindi, Hungarian",
            "Metascore": "79",
            "Plot": "After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMTczNTI2ODUwOF5BMl5BanBnXkFtZTcwMTU0NTIzMw@@._V1_SX300.jpg",
            "Production": "N/A",
            "Rated": "PG-13",
            "Ratings": [
                {
                    "Source": "Internet Movie Database",
                    "Value": "7.9/10"
                },
                {
                    "Source": "Rotten Tomatoes",
                    "Value": "94%"
                },
                {
                    "Source": "Metacritic",
                    "Value": "79/100"
                }
            ],
            "Released": "02 May 2008",
            "Response": "True",
            "Runtime": "126 min",
            "Title": "Iron Man",
            "Type": "movie",
            "Website": "N/A",
            "Writer": "Mark Fergus, Hawk Ostby, Art Marcum",
            "Year": "2008",
            "imdbID": "tt0371746",
            "imdbRating": "7.9",
            "imdbVotes": "1,048,221"
        }
    ]
}
```


* GET top-ten rated movies based on users rates  
  **GET**`/v1/movies/top-ten`

* Get a bearer token 
  **POST**`/v1/users/login`
```bash
  http --form POST localhost:8080/v1/users/login username=mahdi
```
Result: 
```json
{
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQzNzgxMywiZXhwIjoxNjY4MDMzNDEzfQ.xtuu2sriJZFI0_Q__OYr-aK1vPJkyiUjMo0F-k0E6lnJR0AO3xagz6bT3WqZVIopTl_VZRBDvqlPZxGQijvnyg",
    "user": "mahdi"
}
```

* TopTen list: 

```bash
http GET localhost:8080/v1/movies/top-ten 'Authorization:Bearer BEARER_TOKEN'
http GET localhost:8080/v1/movies/top-ten 'Authorization:Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJtb3ZpZWFwaSIsInN1YiI6Im1haGRpIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImlhdCI6MTY2NTQxMDA5OSwiZXhwIjoxNjY4MDA1Njk5fQ.UCrWJtWMvCGensjZf6fuowrXTkE-j8adtyEwwABg57R0QZ8p_IFVqflkhhoGIuLcleDonXjxmTbHQUecYJcYMw'
```

```json

{
    "payload": [
        {
            "title": "The Green Mile",
            "url": "http://localhost:8080/v1/movies/tt0120689",
            "imdb_id": "tt0120689",
            "rate_average": 10.0,
            "box_office": 12
        },
        {
            "title": "The Lord of the Rings",
            "url": "http://localhost:8080/v1/movies/tt0077869",
            "imdb_id": "tt0077869",
            "rate_average": 10.0,
            "box_office": 11
        },
        {
            "title": "Million Dollar Baby",
            "url": "http://localhost:8080/v1/movies/tt0405159",
            "imdb_id": "tt0405159",
            "rate_average": 10.0,
            "box_office": 10
        },
        {
            "title": "The Hurt Locker",
            "url": "http://localhost:8080/v1/movies/tt0887912",
            "imdb_id": "tt0887912",
            "rate_average": 9.0,
            "box_office": 9
        },
        {
            "title": "Iron Man",
            "url": "http://localhost:8080/v1/movies/tt0371746",
            "imdb_id": "tt0371746",
            "rate_average": 9.0,
            "box_office": 8
        },
        {
            "title": "A Beautiful Mind",
            "url": "http://localhost:8080/v1/movies/tt0268978",
            "imdb_id": "tt0268978",
            "rate_average": 9.0,
            "box_office": 5
        },
        {
            "title": "Alice in Wonderland",
            "url": "http://localhost:8080/v1/movies/tt1014759",
            "imdb_id": "tt1014759",
            "rate_average": 7.0,
            "box_office": 7
        },
        {
            "title": "Good Will Hunting",
            "url": "http://localhost:8080/v1/movies/tt0119217",
            "imdb_id": "tt0119217",
            "rate_average": 7.0,
            "box_office": 6
        },
        {
            "title": "127 Hours",
            "url": "http://localhost:8080/v1/movies/tt1542344",
            "imdb_id": "tt1542344",
            "rate_average": 4.0,
            "box_office": 4
        },
        {
            "title": "The King's Speech",
            "url": "http://localhost:8080/v1/movies/tt1504320",
            "imdb_id": "tt1504320",
            "rate_average": 3.0,
            "box_office": 3
        }
    ],
    "error_code": 0
}

```



