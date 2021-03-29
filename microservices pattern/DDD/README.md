## Terminology

# Problem Space

- Domain
- Sub Domain
  - core
  - support
  - generic

# Solution Space

- Bounded context: **boundaries of models. Mapping with subdomain is N:1.**
- Domain
  - Aggregate: An *Aggregate is a cluster of associated objects treated as a unit for data changes.* 
  - Aggregate root:  *External references are restricted to one member of the AGGREGATE*
  - Entity: *Object with* a unique identity 
  - Value object: *Object* without identity; they are defined solely by their attribute values
  - Factory: Factory to build aggregate.
  - Domain Event: An event represents something that took place in the domain
  - Domain service: business logic of the aggregate.
  - Repository: Repository of the aggregate root.
- Application
  - Command: Request changes to the domain by sending commands
  - Command Handler: Application Layer service handle one use case and drive the workflow of call different domain function or domain service function
  - Event Handler: Domain Event Handler

# Package Layer

- API(interfaces): This layer holds everything that interacts with other systems, such as RPC, Restful API. It handles interpretation, validation, and translation of DTO to Command.
  - RPC: JsonRPC interfaces
  - Transform: DTO to Command transformer.
- Application: The application layer is responsible for driving the workflow of the application, matching the **use cases** at hand
  - command: per use case, request to change domain.  
  - command Handler: Drive the execution of command on the domain layer.
  - event handler: Domain event Handler
- Domain: The core of the business logic. There is one package per aggregate, and to each aggregate belongs entities, value objects, domain events, factories, domain services.
  - model: Domain model definition
  - service: domain services
  - factory: Factory to build aggregate
  - facade: ACL Layer for the domain. e.g. RPC call to the external system
  - repository: the interface of the domain repository. implementation is in the infrastructure layer
- Infrastructure: 
  - Persistence, Event, RPC, etc.
- Dependency: 
![ddd_dependency](./ddd_dependency.png)
- DTO(interfaces) → Command → CommandHandler(application) → Domain → infrastructure


# Mapping of layer and terminology
![ddd_package](./ddd_package.png)


# Known Problems:

1. Transaction: the Aggregate root operation should be transactional
2. if the Repository and domain event publish within one transaction, the domain event handler may fail to read the aggregate root since the transaction has not committed yet.

## Reference

- https://inside.getyourguide.com/blog/2019/11/18/tackling-business-complexity-with-strategic-domain-driven-design#:~:text=Pattern%3A%20Customer%2DSupplier,the%20downstream%20is%20the%20customer.&text=The%20trade%2Doff%20of%20this%20pattern%20is%20its%20nature%20itself.
- https://contextmapper.org/