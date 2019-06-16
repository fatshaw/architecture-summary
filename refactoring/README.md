## Refactoring

**First set of refactorings**
- extract method
    - 6 line average
    - Name the function with what it is doing
- Split phase
    - OCP
    - Much clear
- INTRODUCE PARAMETER OBJECT
- COMBINE FUNCTIONS INTO TRANSFORM
    
**Encapsulation**: decompose modules, hide secrets from the rest system.
- ENCAPSULATE RECORD
    - hide data 
- ENCAPSULATE COLLECTION
    - Do not return raw collection data
    - Return a protected view on it, using a read-only proxy or a copy.
- REPLACE PRIMITIVE WITH OBJECT
    - Place to put bahavior
    - Extension
- REPLACE TEMP WITH QUERY
    - no longer need to pass in variables into the extracted functions
    - avoid duplicating the calculation logic
- EXTRACT CLASS
    - A class that is too big to understand easily
    - split data and methods that seems to go together
    - sub typed class
- HIDE DELEGATE
    - remove dependency

**Moving Features**: 
- MOVE FUNCTION
    - 


### Book
- Refactoring: improving the design of existing code
- The Art of Readable Code
