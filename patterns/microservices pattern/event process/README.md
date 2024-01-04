The pattern to process the message in the message queue like rabbitmq or kafka
## Pattern 1: Save the message in database
* Process the event
    1. When consumer receieves the message, validate the message. if failed, log error and throw the message.
    2. If validated, save it to the event table in the database like mysql or postgresql or mongodb. usually, the event table contains `status` and `retry_times` columns. The `status` could be `PROCESSING`, `SUCCESS`, `FAILED`. The initial value is `PROCESSING`.
    3. Send `ack` to the broker of the message queue, wihch means the event is consumed. 

* Retry the message
    1. Process the event asynchronously. if succeed, change the event status to `SUCCESS`, otherwise retry `N` times.
    2. If after retry N times it is still failed, then change the status to `FAILED`.
    3. Registe a scheduler to load the event with status in `FAILED` and retry the event process.
    4. After each retry, the `retry_times` will be decreased by one. when `retry_times` is decreased to zero, no more retry.

* Manual retry.
    1. if the event status is `FAILED` and we need retry the process if we have modified the process code. we can change the `retry_times` to greater than zero, then the program can retry the event later again.

* Exceptions:
    1. when the `status` in `PROCESSING` and due to some unexpected reason(e.g. processor is killed), the processor failed to save the `status` to `FAILED`, the message will be no longer processed. 
    2. Registe a scheduler to change the event with status equal to `PROCESSING` and created more than 60 miniutes ago to `FAILED`.

## Pattern 2: Introduce DLQ
* Process the event 
    1. When consumer receieves the message, validate the message. if failed, log error and throw the message.
    2. If validated, ack the message and process the message asynchronously.
* Retry the message 
    1. If processing the event asynchronously failed, send the event into DLQ
    2. registe a scheduler to comsume the DLQ and send back the message to the original queue.
    3. Retry Limit: in the message header, add two headers:
        - `LAST_RETRY_TIME`: last retry time. if `LAST_RETRY_TIME` within 5 minutes, then retry it later.
        - `RETRIED_TIMES`: retried times. if `RETRIED_TIMES` >= `MAX_RETRIED_TIMES` then discard the message
