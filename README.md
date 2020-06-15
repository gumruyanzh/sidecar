# Sidecar Application
implementation of redis spring security jwt authentication

##Dependencies
1) Spring Security
2) Jedis client
3) Spring data redis
4) Lombok
5)jjwt-api (-token -jackson)



## Startup 
Application creates test user on startup with test credentials 
* username: `test`
* password: `test`

### To run application 
1) `mvn clean install`
2) `mvn spring-boot:run`
3) `curl -X POST -H "Content-Type: application/json" -d '{"username":"test","password":"test"}' http://localhost:8081/auth`
4) Grab the jwt token after response 
* expample 
```{"jwt":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNTkyMjUzMjIyLCJleHAiOjE1OTIyODkyMjJ9.MhoXBliPNsp_kzH2wD0MIso0N-Sa_kBjztyInJJguQE"}```
5) Get user data from redis with 
* `curl -i -H "Autherization:Bearer {your_JWT_token}"  http://localhost:8081/users/test`

### User creation
Simply perform POST request to `/users` with body 

* `curl -X POST -H "Content-Type: Application/json, Autherization:Bearer {your_JWT_token}" --data "username:username, password:password" http://localhost:8081/users`


 
 