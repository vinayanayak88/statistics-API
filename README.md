## statistics-API
REST APIs to create transactions and get statistics of transactions in last 60 seconds

#### to start the service 
```bash
mvn spring-boot:run
```

#### to run the tests
```
mvn test
```

#### APIs exposed
- `POST` [http://localhost:8080/transactions](http://localhost:8080/transactions)  
body
```
{
  "amount": 12.3,
  "timestamp": 1478192204000
}
```
Where:
- amount - transaction amount
- timestamp - transaction time in epoch in millis in UTC time zone (this is not current
timestamp)
Returns: Empty body with either 201 or 204.
- 201 - in case of success
- 204 - if transaction is older than 60 seconds  
  
  

+ `GET` [http://localhost:8080/statistics](http://localhost:8080/statistics)  
response
```
{
  "sum": 1000,
  "avg": 100,
  "max": 200,
  "min": 50,
  "count": 10
}
```
Where:
- sum is a double specifying the total sum of transaction value in the last 60 seconds
- avg is a double specifying the average amount of transaction value in the last 60
seconds
- max is a double specifying single highest transaction value in the last 60 seconds
- min is a double specifying single lowest transaction value in the last 60 seconds
- count is a long specifying the total number of transactions happened in the last 60
seconds

Returns: Empty body with either 201 or 204.
- 201 - in case of success
- 204 - if there is no transaction in last 60 seconds 

