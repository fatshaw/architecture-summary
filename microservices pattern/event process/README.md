The pattern to process the message in the message queue like rabbitmq or kafka
* step 1: save the event into database
    1. When consumer receieves the message, validate the message. if failed, log error and throw the message.
    2. If validated, save it to the event table in the database like mysql or postgresql or mongodb. usually, the event table contains `status` and `retry_times` columns. The `status` could be `PROCESSING`, `SUCCESS`, `FAILED`. The initial value is `PROCESSING`.
    3. Send `ack` to the broker of the message queue, wihch means the event is consumed. 

* step 2: process the event.
    1. Process the event asynchronously. if succeed, change the event status to `SUCCESS`, otherwise retry `N` times.
    2. If after retry N times it is still failed, then change the status to `FAILED`.
    3. Registe a scheduler to load the event with status in `FAILED` and retry the event process.
    4. After each retry, the `retry_times` will be decreased by one. when `retry_times` is decreased to zero, no more retry.

* step 3: manual retry.
    1. if the event status is `FAILED` and we need retry the process if we have modified the process code. we can change the `retry_times` to greater than zero, then the program can retry the event later again.

* step 4: exceptions:
    1. when the `status` in `PROCESSING` and due to some unexpected reason(e.g. processor is killed), the processor failed to save the `status` to `FAILED`, the message will be no longer processed. 
    2. Registe a scheduler to change the event with status equal to `PROCESSING` and created more than 60 miniutes ago to `FAILED`.