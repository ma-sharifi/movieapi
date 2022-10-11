# How to run the application?

This application is written with Java 11 and is compatible with java 17.
You can give a profile as a parameter. Without a profile, it will use by default.

```shell
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.profiles=prod
```
OR
You can build application and package it into jar file. 
```shell
mvn package
```
You can run the tests with:
```shell
mvn test
```
You can execute the jar:
```shell
java -jar target/movieapi.jar
```

Please get your own **API_KEY** by register on [OMDB API](http://www.omdbapi.com/)  

Also, you can change below properties and `application.yaml` file:
>jwt.secret=mySecretKey  
omdbapi.url=http://www.omdbapi.com/  
omdbapi.apiKey=c732beed