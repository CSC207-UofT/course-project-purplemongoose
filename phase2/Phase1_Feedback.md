# Summary of Phase 1 Feedback and Changes

Feedback from the TA after Phase 1 to be addressed

## Design document

- Some diagram or flow chart that:
  - explains the project specification end-to-end
  - shows different elements that compose different layers the interactions between those
- Include captions for any diagrams and tables that are included
- Include a UML diagram
- When mentioning libraries used, include references
- Elaborate explanations about design patterns

## Functionality

The program covers all tasks mentioned in the specification. Even though some of them are partially implemented yet,
there are sufficient comments that describe the scope for Phase 2. Good implementation of database and UI and their
integration with the Java application.

## Refactoring and code smells

- Address the identified code smells

## Testing

- Insufficient authentication testing coverage
- Avoid redundant code in tests which highlights problems with the inherent design of the application as indicated in the design document.

## Code style and documentation

- The `Name.java` class contains 3 different constructors based on whether some attributes are passed or not.
  - You can consider using a single constructor with default values as null so that there is no repeated code.
- I like that you're storing name and other PII as hashcodes
  - Demonstrates deep thought about both design and implementation.
- I hope you also understand in depth how the `Objects.hash` method works.
- More descriptive variable names
- Consistent overriding using `ProfileType` interface in `Organization.java` class
  - Example: `getName` returns `orgName`, while `getEmail` returns `orgEmail.getEmail()`
  - Either resolve inconsistency or provide a good explanation
- Implement `ProfileType` interface in `Business` class
- You should also add some type checking for inputs such as phone no. and email.
- Do something or explain why `ResponseContainer` interface is empty
- Why is there an empty data folder?
- You can also consider adding some lists for variables
  - such as pronouns so that an arbitrary value is not input.
  - This will also help you in making this value as a dropdown feature in the UI.

## Design Patterns

- Implement design patterns

## Clean Architecture

- Unclear which layer databases, requests, and resources fall into
  - Clean up or add explanation in design document

## SOLID

- Limited scope of following SOLID principles
  - Clean up with more work on the backend
- Deducting marks because team has yet to implement any "OLID" principles.
  - Address this in phase 2
  - Include explanations within the design documents