- **2pc** 2 phrase commit protocal  
  - prepare: coordinator send vote request to all the participants and every participant responses coordinator with 'Yes' or 'No'
  - commit: coordinator send commit/abort to all participants according to the prepare phrase. 
  - problem  
    - no timeout check
    - data is not consistent when coordinator and participant failed together and no other participants receive commit/abort request.
- **3pc** 3 phrase commit protocal
  - cancommit: vote
  - precommit: prepare to commit with timeout check
  - docommit: do commit or abort according to precommit phrase.
  - advantage: 
    - introduce timeout mechanism in precommit and commit phrase so that participants will not hold resource forever.
    - in precommit when coordinator and participant failed together , other participant will abort due to timeout of waiting coordinator, so that it can resolve the problem of 2pc 
  - disadvantage: in do commit phrase, when coordinator is down or network between coordinator and pariticipant is not connected then pariticipants will do commit even if it should do abort.

- **TCC** business application need implement three phase for each interface: try, confirm, cancel.
    - when try phase is success for all participators, it enters into confirm phase, otherwise, it goes to cancel phase.
    - if in the confirm phase, something goes wrong, then the RM will retry since the Try phase has been successful, then the confirm phase will become successful with great possibility.
    - cons: business application is complicated.
    
- **Message Table** use message table to track transaction status. 
    - Add message table
    - make application business operation and message table record as a local DB transaction.
    - execute a job to send unsent message to message queue, change message status = SENT.
    - when message subscriber finished processing the message, change message status = DONE.
    - cons. couple the message table with business application.

- **transaction message queue** decouple message table with business application by transaction message queue.
    - execute a local transaction, including business db operation and publishing message queue 
    - when local transaction commit succeeds, commit message queue at the same time.
    - when local transaction commit fails, cancel the message queue
    - if commmit or cancel the message queue fails, message queue will callback the business application to check whether the local transaction succeeds or fails.
    - pros. move message table from business application to transaction message queue.
    - in some cases, downstream system should implement try|confirm|cancel interface, since message consumer cannot make sure process must be succeed, if failed, it should call cancel interface of upstream system to revert the transaction.

- **Distributed Transaction middleware** seata, txlcn