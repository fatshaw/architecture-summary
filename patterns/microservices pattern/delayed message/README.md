## Delayed Message
make message received by the consumer with some delay

- **rabbitmq dead letter exchange(DLX)** for rabbitmq queue, it can be set a property dead letter queue. when the message in the queue expires due to TTL, then the message will be republished to the DLX. So the TTL is the delay we should set. 

- **retried queue** 
    - Two queues. one is the original consumption queue, and another is the retry queue. 
    - One DLX.
    - Consumer processes message from the original queue, if the consumption failed, create a new message with TTL=retried delay time and republish it to the retried queue. 
    - The retried queue has no consumer. When the message expires, it will be republished to the DLX, which republished the message to the origin queue.

## Reference
- https://engineering.nanit.com/rabbitmq-retries-the-full-story-ca4cc6c5b493