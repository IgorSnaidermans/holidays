To run a program simply build it with 'mvn clean compile package' then run target/longWeekends-0.0.1-SNAPSHOT.jar
</br>Or run 'java -jar target/longWeekends-0.0.1-SNAPSHOT.jar' when built.
</br>Or run run.sh to build and run the application (Maven and Java needed)

Task:

1) Create a Spring Boot java application that provides REST API for the following requests:
   a) Is the given date a holiday in the given country, sample url:
   http://localhost:8080/holidayapi/LV/25122020
   Result would be “yes” or “no”
   You can use ISO 3166-1 alpha-2 for country codes, DDMMYYYY for dates b) Total amount of holidays in the given year in
   the given country, sample url:
   http://localhost:8080/holidayapi/LV/1997
   Result would be a number
2) As a source of data please use public REST API: https://date.nager.at/
   You do not need to cache/load/store data, each request to your REST API can result in a request
   to https://date.nager.at/.
3) Add swagger
4) Add unit tests
   Deliverables:
1) Link to a github repository
2) Description on how to run your program
    