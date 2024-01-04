1. JsonRPC: default
2. Common fields
    - Request: 
        - request ID
    - RPC response
        - Do not use enum in the response 
        - Error_code, message
3. Timeout: 99 percentile of latency + standard deviation
4. Idempotence: request_id
5. Retry policy: retry 3 times with 
6. Fallback strategy: 
    - Local cache -> central cache(redis) -> remote call -> fallback cache(longer TTL in redis)
