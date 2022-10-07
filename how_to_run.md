# How to run the application?

This application is written with Java 17.
To run locally the application you have to build it:

```shell
mvn package
OR
mvn spring-boot:run
```
and then execute the jar:
```shell
java -jar target/movieapi.jar
```

You can run the tests with:
```shell
mvn test
```

Please get your own **API_KEY** by register on [OMDB API](http://www.omdbapi.com/)  

Also, you can change below properties and `application.yaml` file:
>jwt.secret=mySecretKey  
omdbapi.url=http://www.omdbapi.com/  
omdbapi.apiKey=c732beed