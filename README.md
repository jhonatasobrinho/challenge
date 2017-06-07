# Challenge

To compile the project, use 
```sh
$ mvn clean install
```
and then, use the following to make the endpoint available:
```sh
$ java -jar target/challenge-0.0.1.jar
```

Then go to `http://localhost:8080/rest/mars/{input}`, changing the input to something like:
 - L to move left
 - R to move right
 - M to move forward

You can use this input as many times as you want. Try some examples:
```
$ curl -s --request POST http://localhost:8080/rest/mars/MMRMMRMM
(2, 0, S)

$ curl -s --request POST http://localhost:8080/rest/mars/MMR
(0, 2, E)

$ curl -s --request POST http://localhost:8080/rest/mars/MMRMML
(2, 2, N)
```
