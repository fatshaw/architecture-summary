# Problem Space
Introduce What kind of problem we want to solve.

## Terminology
Naming Definition

## Background
PRD, pain points, technique issues, etc.

## Use Case
Business use cases

## Sub-Domains
Core subdomain
Generic subdomain
Support subdomain

# Solution Space
Introduce How to solve the problem at the solution level, not deep in technical implementation.

## Bounded context
Bounded context definition and context map

## Modeling
- Aggregate
- Entity
- Event
- State machine
- Repository

## Sequence Diagram
- Components interaction
- Dataflow

## Alternatives
Alternative solutions proposed by other engineers.

# Implementation Detail
Introduce the concrete implementation of the solution.
## API Design
- Protocol: JsonRPC, Rest, etc.
- Request & response DTO
- Error Code
- Idempotency

## Storage
- DDL
- DML
## Messaging
- Topic
- Region
- Message DTO

## Dependency
- Service name
- Timeout
- Retry policy
- Cache
- Idempotency
- Fallback
- Infrastructure such as buckets, etc.

# Deployment
## Configurations
- Gitlab
- Vault

## Release Management
- Release plan
- Rollback plan
- Backward compatibility
  - API
  - Storage
  - Configurations

## Observability
- Monitoring&Alert
- SQL
- Looker
- OpsGenie

## Security
- Adherence to Legal/Compliance obligation(PCI-DSS)
- Security by design

## Risk
- Number of Repos affected
- Number of resources necessary
- The complexity of the project itself
- Knowledge gaps
- Performance Limitation(CPU, Memory, Message Size, Traffic volume)

# Reference
