# Summary of Phase 1 Feedback and Changes

Feedback from the TA after Phase 1 to be addressed. ***Responses to feedback in italics***

## Design document

- Some diagram or flow chart that:
  - explains the project specification end-to-end
  
  - shows different elements that compose different layers the interactions between those
  
    ***RESPONSE: Implemented UML Diagram and Flow chart illustrating data flow between different parts of kard. These can be viewed [here]().***
  
- Include captions for any diagrams and tables that are included 

- Include a UML diagram

  ***RESPONSE: See above***

- When mentioning libraries used, include references

  ***RESPONSE: All libraires linked to references***

- Elaborate explanations about design patterns

  ***RESPONSE: Implemented Memento, Strategy, and Command design patterns. Discussed these implementations in [Design Document]()***

## Functionality
The program covers all tasks mentioned in the specification. Even though some of them are partially implemented yet, 
there are sufficient comments that describe the scope for Phase 2. Good implementation of database and UI and their 
integration with the Java application.

***RESPONSE: We have continued to implement features to kard including but not limited to: editing profiles, fully functional iOs/Android application - TODO: Expand***

## Refactoring and code smells
- Address the identified code smells

  ***RESPONSE: All identified code smells have been addressed. Refer to [Design Document]() for evidence on continued refactoring*** 

## Testing
- Insufficient authentication testing coverage

  ***RESPONSE: TODO: Ling ADD***

- Avoid redundant code in tests which highlights problems with the inherent design of the application as indicated in the design document.

  ***RESPONSE: TODO: Ling ADD***

## Code style and documentation
- The `Name.java` class contains 3 different constructors based on whether some attributes are passed or not. 
  - You can consider using a single constructor with default values as null so that there is no repeated code. 
  

    ***RESPONSE: Edited `name.java` to use the [Java Record](https://docs.oracle.com/en/java/javase/14/language/records.html) feature introduced in JDK 14 as seen below:***
  
    ```java
    public record Name(String first, String last, String pronouns,
                       String titles) implements Serializable {
      public Name {
          Objects.requireNonNull(first);
          Objects.requireNonNull(last);
      }
      // javadocs and other methods ommited for concisensess
    }
    ```
  
    Records were appropriate since `Name` is a purely data class and records allow for names to be constructed with `null` parameters without issue, except for the values specified as "Not Null"
  
    Details about this implementation can be found on the [GitHub page for Name.java](https://github.com/CSC207-UofT/course-project-purplemongoose/blob/d1b7c6104203066d85167381caa0de09f12ec11a/kard-server/src/main/java/entity/datafiles/Name.java#L14)
  
- I like that you're storing name and other PII as hashcodes 
  
  - Demonstrates deep thought about both design and implementation. 
  
- I hope you also understand in depth how the `Objects.hash` method works. 

  ​	***TODO: LING - demonstrate understanding***

- More descriptive variable names

  ***RESPONSE: An effort has been made to refactor our code to avoid any single letter and undescriptive variable names.***

- Consistent overriding using `ProfileType` interface in `Organization.java` class

  - Example: `getName` returns `orgName`, while `getEmail` returns `orgEmail.getEmail()`

  - Either resolve inconsistency or provide a good explanation

    ***RESPONSE: Organizations have been removed from the scope of kard due to time constraints. We would have loved to implement it, however due to the limited time frame of CSC207, it simply was not feasible to finish.***

- Implement `ProfileType` interface in `Business` class

  ***RESPONSE: Organizations have been removed from the scope of kard due to time constraints. We would have loved to implement it, however due to the limited time frame of CSC207, it simply was not feasible to finish.***

- You should also add some type checking for inputs such as phone no. and email.

  ***TODO: CAN WE STILL DO THIS?***

- Do something or explain why `ResponseContainer` interface is empty

  ***TODO: LING - Is this resolved***

- Why is there an empty data folder? 

  ***RESPONSE: The `data` folder contained the database file for kard. It was empty since we did not want the database to sync between computers. The folder has since been removed on Git and is automatically created if it does not already exist on startup of kard-server***

- You can also consider adding some lists for variables 
  - such as pronouns so that an arbitrary value is not input. 
  
    ***RESPONSE: Given recent laws passed by the Trudeau Government, specifically [Bill C16](https://www.parl.ca/DocumentViewer/en/42-1/bill/c-16/royal-assent), An Act to amend the Canadian Human Rights Act and the Criminal Code, and since kard is an application built with inclusivity in mind, we do not believe it would be appropriate for us, as developers of kard, to limit a persons freedom of expression in the areas of title, pronoun, and gender identity.***
  
  - This will also help you in making this value as a dropdown feature in the UI.
  
    ***RESPONSE: Given our above explanation, we believe that a free entry field for this information would be more inclusive and would better represent users of kard.***

## Design Patterns

- Implement design patterns

  ***RESPONSE: As we referred to above, we have implemented Memento, Strategy, and Command design patterns. Discussed these implementations in [Design Document]()***

## Clean Architecture

- Unclear which layer databases, requests, and resources fall into
  - Clean up or add explanation in design document
  
    ***TODO: Ling - provide explanation***

## SOLID
- Limited scope of following SOLID principles
  - Clean up with more work on the backend
- Deducting marks because team has yet to implement any "OLID" principles. 
  - Address this in phase 2
  - Include explanations within the design documents		
  - ***TODO: Address***