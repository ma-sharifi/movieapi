### What is solution?
A REST API application to get movie information from multiple data source Omdb API and CSV. 
User send a movie's title then application check the CSV file whether this movie is a winner of the Best Picture Oscar or not.
After we found movie won the Best Picture Oscar awards we will fetch its data by title from Omdb API and show them to user.

All APIs protect by Jwt token.
also users should have a JWT token (describe in `how_to_test.md`) to use RESTful services , 
application currently integrate two data source CSV file (contains oscar's candidates) 
and cache objects on startup in memory to improve performance another data source is OMDB API
during request to retrieve movie's information and user want to know the movie is won on oscar or not
in this case application inquiry from OMDB API and retrieve correct information ("complete title,..."),
for second step inquiry from memory (cached befor via CSV file) to know that this film is won in oscar or not 
after that return object response contains movie's data and won status in oscar,
also this application enable users to give rate to movies each user can rate to a movie once and users will be able to
get list of top ten rated movie based on data which was sent by users.
