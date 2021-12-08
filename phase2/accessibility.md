# kard Accessibility Document

<details>
  <summary>Contents</summary>

## Contents

- [Introduction](#introduction)
- [Principles of Universal Design](#universal-design-principles)
  - [Equitable use](#equitable-use)
  - [Flexibility in use](#flexible-in-use)
  - [Simple and intuitive use](#simple-and-intuitive-use)
  - [Perceptible information](#perceptible-information)
  - [Tolerance for error](#tolerance-for-error)
  - [Low physical effort](#low-physical-effort)
  - [Size and space for approach and use](#size-and-shape-for-approach-and-use)
- [Audience](#audience)
- [Target demographics](#target-demographics)

</details>

## Introduction

The graphical user interface of the application is implemented and designed based on the accessibility guidelines described in [Google's Material 2 Design Guidelines](https://material.io/design) with further references to [Google's Material You (Material 3) Design Guidelines](https://m3.material.io). This features requirements regarding the **clarity** and **robustness** of the design. 

Furthermore, since the application was developed using the open source the [Flutter](https://flutter.dev) framework, it has made it possible and feasible to ship kard with Web, iOs, Android, MacOS and Windows applications simultaneously increasing accessibility to the application by providing various venues for accessing the kard backend server.

<br/>
<div align="right">
    <b><a href="#----">↥ back to top</a></b>
</div>
<br/>

## Principles of Universal Design

Either a description of the features that can be implemented in accordance to a given principle or how that principle does not apply to this program

### Equitable use

> Useful and marketable to people with diverse abilities

**Relevant features:**

- Ability to skip setup questions (pronouns, titles, email, phone number) 
  - Preserves option for user privacy
  - Does not discriminate against users based on access to resources
- Titles are supplemented with unambiguous, universal symbols independent of any language
  - Invites usage by individuals that don't speak English
  - Unambiguous symbols can be interpreted independent of text size

### Flexibility in use

> Accommodates a wide range of individual preferences and abilities

**Relevant features:**

- Multiple ways to complete actions 
  - Swipe or tap to return to previous page
- Submit as much or as little information as each user is comfortable with
- Backend implementation allows for a variety of organizations that implement kard 
  
### Simple and intuitive use

> Is easy to understand, regardless of experience, knowledge, language skills, or current concentration level

**Relevant features:**

- Different colour theme for setup and normal application use
  - Light colour theme for setup, dark colour theme for homepage
  - Visual distinction between different stages of the app setup and usage
- Use simple, unambiguous symbols for different features and actions
  - Simple line drawings for buttons to go back, add users, or check own status
  - Clear person silhouette for users with no profile photo setting

### Perceptible information

> Communicates necessary information effectively to the user, regardless of ambient conditions or user's sensory abilities

**Relevant features:**

- Colour theme follows guidelines for visual contrast between features
- Dark colour theme in main usage screen with high text contrast reduces eye strain

### Tolerance for error

> Minimizes hazards and adverse consequences of accidental or unintended actions

**Relevant features:**

- Option to return to previous page of setup form to resolve mistakes
- Confirmation and safeguard pop-up dialogues after passing in command and before in-app command response to prevent unintentional actions

### Low physical effort

> Used effectively and comfortably and with minimum fatigue 

**Relevant features:**

- Multiple options to complete tasks
  - Avoids mandatory physical strain for simple actions
  - Accommodates for individuals of different physical ability
- Very few steps to complete any operation
  - Simulates simplicity and appeal of traditional physical business cards with technological convenience
  
### Size and space for approach and use

> Appropriate size and space is provided for approach, reach, manipulation, and use regardless of the user's body size, posture, or mobility

**Relevant features:**

- Spaces between buttons and input fields follow Google's Material 2 guidelines
- Implementation on different platforms allows users to transition seamlessly if their device disposition is insufficient for their use

<br/>
<div align="right">
    <b><a href="#----">↥ back to top</a></b>
</div>
<br/>

## Audience

If sold or licensed, kard would be marketed towards other companies in order to help them and their employees
build networks amongst their team and with other companies. To market, kard features a paperless, digital 
business card sharing tool that behaves the same as a physical business card. The main companies that implement
kard will likely do so to appeal to a younger workforce and to promote environmentally conscious solutions 
within their company. Like traditional business cards, kard will likely appeal more to young professionals or 
professionals looking for a new job to build their industry connections. An additional subset given the digital 
feature of kard will likely appeal greater to the more tech-savvy and environmentally-aware members of the 
companies.

<br/>
<div align="right">
    <b><a href="#----">↥ back to top</a></b>
</div>
<br/>

## Target demographics

This application, kard, is not likely to be less used to be one less group than another. Its user interface is
designed so that it is easily used and accommodates to a variety of different audiences. On the backend, the 
way that the entities and relationships are set up allow for the extended application to organizations beyond
just corporate settings. 