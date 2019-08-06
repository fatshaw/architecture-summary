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
    - modularity
    - related software elements are grouped
- MOVE STATEMENTS INTO FUNCTION
    - Removing duplication
- SLIDE STATEMENTS
    - make it easy to understand code
    - declare elements close to where I use them
    - preprepare for another refactoring
- Split Loop
    - only do one thing
    - followed by Extract Function or Slide statements

**Simplifying Conditional Logic**
- DECOMPOSE CONDITIONAL
    - make intention clearer
    - replace each chunk of code with a function call. `Extract Function`
- CONSOLIDATE CONDITIONAL EXPRESSION
- REPLACE NESTED CONDITIONAL WITH GUARD CLAUSES
    - one leg is normal and the other indicates an unusual condition.
    - much more clearer
- REPLACE CONDITIONAL WITH POLYMORPHISM
    - form a set of types with different logic

**Refactoring APIs**
- SEPARATE QUERY FROM MODIFIER
    - Query no side effect
- PARAMETERIZE FUNCTION
    - remove the duplication by using a single function with parameters for the different values
- REMOVE FLAG ARGUMENT
    - Replace Parameter with Explicit Methods
    - decompose different conditions
- PRESERVE WHOLE OBJECT
    - feature envy: Pulling several values from an object to do some logic on them alone
- REPLACE FUNCTION WITH COMMAND

### Book
- Refactoring: improving the design of existing code
- The Art of Readable Code
