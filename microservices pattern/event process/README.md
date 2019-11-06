The pattern to process the message in the message queue like rabbitmq or kafka
* step 1: save the event into database
    1. When consumer receieves the message, validate the message. if failed, log error and throw the message.
    2. If validated, save it to the event table in the database like mysql or postgresql or mongodb. usually, the event table contains `status` and `retry_times` columns. The `status` could be `RECEIVED`, `PENDING_PROCESSING`, `SUCCESS`, `FAILED`. The initial value is `RECEIVED`.
    3. Send `ack` to the broker of the message queue, wihch means the event is consumed. 

* step 2: process the event.
    1. Process the event asynchronously. if succeed, change the event status to `SUCCESS`, otherwise retry `N` times.
    2. If after retry N times it is still failed, then change the status to `PENDING_PROCESSING`.
    3. Registe a scheduler to load the event with status in `RECEIEVED`,`PENDING_PROCESSING` and retry the event process.
    4. If after M times, the event process still failed, change the status to `FAILED`.

* step 3: manual retry.
    1. if the event status is `FAILED` and we need retry the process if we have modified the process code. we can change the event status in the database to `PENDING_PROCESSING`. Then the program can retry the event later again.