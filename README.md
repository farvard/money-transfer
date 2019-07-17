# money-transfer
A simple RESTful API for money transfers between accounts

### Technologies
- Java 8
- [Spark Framework](http://sparkjava.com) (with embedded Jetty)
- [Lombok](https://projectlombok.org)
- [Log4j & Slf4j](https://www.slf4j.org/)
- [google/gson](https://github.com/google/gson)
- [H2 in memory database](https://www.h2database.com)
- [JUnit](https://junit.org/)

### REST api
| METHOD | PATH | Description | 
| -----------| ------ | ------ |
| GET | /accounts | all accounts | | |
| GET | /accounts/{id} | get one account ||| 
| POST | /accounts | save account |||
| GET | /accounts/{id}/transactions |	account's all transactions |||
| GET | /transactions | all transactions |||
| GET | /transactions/{id} | one account |||
| POST | /transfer | transfer money |||

### How to run
```sh
mvn exec:java
## see http://localhost:4567/accounts
```

### How to run Tests
```sh
mvn test
# converage file: ./target/site/jacoco/index.html
``` 
