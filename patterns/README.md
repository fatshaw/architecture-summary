RFC - Patterns



# Problem space
Summarize the common patterns in the team to facilitate development efficiency.

## Best practices
[microservices](./microservices%20pattern/)

[API Design](API-design.md)

[DDD Pattern](https://medium.com/airwallex-engineering/domain-driven-design-practice-modeling-payments-system-f7bc5cf64bb3)

[Database pattern](database.md)

[Configuration](configuration.md)

[RPC](rpc.md)

[Messaging](messaging.md)

[client](client.md)

## Integration-testing
## Observability
## Programming Specification
### Naming
1. PO: Persistence object As database persistence object
2. DTO: data transfer object As API Request and response
3. Domain: As a business object if the business is complicated, otherwise suggest not to use it. 
4. example:  rule
    - PO: RulePO
    - DTO: RuleDTO
    - Domain: Rule

### Exception
1. If the function does not want the caller to care about the error, just raise a runtime exception
2. If the function wants the caller to deal with the error, needs to return Either<Error, T: Result> 

### Release versions
1. Group: com.airwallex.pa
2. Module: domain-xxx. E.g., pa-core-client
3. Version: [Major Version].[Minor version].[Patch version]
    - Major Version: Updating on big releases
    - Minor version: Updating on features or milestones
    - Patch version: Updating on bug fixes and small functionalities
4. Forbid override any release version. 
5. NO use of SNAPSHOT in the production

