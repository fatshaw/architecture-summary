## Auth Server with OAuth2

1. client call server at endpointer=/oauth/authorize to get code
2. server check whether user have logined, if not redirect to login page
3. user login and server will check username and password. if correct, redirect to user website with generated code
4. client call server at endpointer=/oauth/token with code generated at last step to get access token
5. client can call other api with access token

## Reference
- https://segmentfault.com/a/1190000012275317
- https://stripe.com/docs/connect/express-accounts#token-request
- https://github.com/Baeldung/spring-security-oauth