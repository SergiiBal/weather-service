### 14.09.2022
TO DO:
* Read link https://junit.org/junit5/docs/current/user-guide/#overview 
* Read link https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
* Add jUnit5 to dependencies 
* In com.sergii.HelloWorld class create method Hello that returns hello + parametr "name" and create test for it

### 18.09.2022
LEARNED:
* Java annotation
* Maven parent, plugin, dependency, property
* Introduction to Spring

TO DO:
* Finish tutorial https://spring.io/guides/gs/spring-boot/ (start from test)
* https://spring.io/guides/gs/rest-service/ start from Create a Resource Representation Class
* Explain http udp tcp (OSI model), port
* Explain REST (GET, POST, PUT, DELETE) "GET MAPPING"

### 25.09.2022
LEARNED:
* [JSON](https://www.json.org/json-en.html)
* Dependency Injection, Inversion of Control
* Working with JSON and issuing first HTTP call
* https://rapidapi.com/natkapral/api/countries-cities
* Cache(https://spring.io/guides/gs/caching/)

TO DO:
* Make Controller that returns Animal (that can be Cat or Dog)
* Make /country/city-details that will return passed City Details using Countries-Cities API (accepts geonameid that you find from https://www.geonames.org/)
* Make CodeWars task and add it here as Service with JUnit test + RestController (/codewars/task/{param})

### 05.10.2022
LEARNED:
* method Overloadind and Overriding

TO DO:
* Create WeatherController, which for given adress and date gives weather
  * Use geocoding from https://rapidapi.com/googlecloud/api/google-maps-geocoding for getting coordinates
  * Using timeMachine and Forecast from https://rapidapi.com/darkskyapis/api/dark-sky get weather details
  * Your response should be readable text

### 12.10.2022
LEARNED: 
* working with IntelliJ warnings, inspecting entire code
* repeated @RequestParam vs @PathVariable differences and use case
* worked with RestTemplate, understood ResponseType
* learned Java Class(class Class) and use cases of it (dynamic response, Java Reflections API)
TO DO:
* inspect entire code and resolve warnings when applicable
* read more about Java Reflections and make small example
* after finishing testing Weather part with coordinates, extract businnes logic into service and make new URL as requested in previous homework
* Use new weather service https://rapidapi.com/weatherapi/api/weatherapi-com?fbclid=IwAR06qsqCjD7RUB6wCQSDs5OZrbug1Rsg9XP8Z03GSaIKEMckfEOIdLqMtkg

/weather?address=Kyiv,Ukraine&date=12-10-2022

"it will be raining in Kyiv, Ukraine on 12-10-2022"

/weather?address=Kyiv,Ukraine

"it is raining in Kyiv, Ukraine today"

