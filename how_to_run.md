# How to run the application?

This application is written with Java 11.
To run locally the application you have to build it:
Or give a profile as a parameter. Without profile, it will use with default.

```shell
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
or package and then execute the jar:
```shell
mvn package
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