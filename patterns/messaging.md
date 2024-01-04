- Idempotence
- Retry
- Message storage

inside the domain, we prefer generic events, because we have much more control over it. while cross domains, we prefer RPC or specific events, because we need clear boundaries between domains. 