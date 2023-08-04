# Project Readme: "What Is My Name" Application

## Overview

The "What Is My Name" application is a word-guessing game where the user needs to guess a keyword within 20 attempts.
The application provides a forecasting system that reveals certain information about the guessed word,
such as correctly guessed letters and their positions. The game's goal is to successfully guess 
the word, which determined by Admin, within the specified attempts.

## Game Rules

- The user will be presented with the keyword, which remains hidden.
- The user can guess key word.
- After each guess, the application will display the following information:
    - Correctly Guessed Letters: The number of letters that match between the guessed word and the keyword.
    - Letters in the Right Place: The number of letters in the guessed word that are in the correct position.
    - Correctly Guessed Letters in the Wrong Place: The number of correctly guessed letters that are in the wrong position.
- The user has a maximum of 20 attempts to guess the correct word.

## Endpoints and Functionality

### Authentication (AuthController)

- `POST /auth/register`: Allows users to register for the application by providing their credentials.
- `POST /auth/login`: Enables users to log in with their credentials and receive a JWT token for authentication.
- `GET /auth/activate-status/{token}`: Activates the user account based on the provided token. Redirects to a web page upon successful activation.

### User Profile (UserProfileController)

- `POST /user-profile/create`: Creates a new user profile by providing necessary user details.
- `PUT /user-profile/update-userprofile/{token}`: Updates an existing user profile based on the provided token.
- `PUT /user-profile/update-manager-status/{authId}`: Updates the manager status of a user profile (Internal use only).
- `PUT /user-profile/activate-manager-status`: Activates the manager status for a user profile.
- `PUT /user-profile/forgot-password`: Allows users to change their password through a "Forgot Password" feature.

### Game (GameController)

- `GET /game/find-all`: Fetches a list of available games and their remaining life.
- `GET /game/find-life/{token}/{gameId}`: Retrieves the remaining life for a specific game.
- `POST /game/create`: Creates a new game and assigns it to the user.
- `POST /game/check-try`: Allows users to check their try and get the game status.

## Frontend and Backend Technologies

The application uses React.js for the frontend, which provides a user-friendly interface 
for playing the game and interacting with the system. The frontend consumes
APIs exposed by the backend to manage user authentication, profile creation, and gameplay.

## Backend Technologies
The backend of the "What Is My Name" application is built using Spring Boot, 
a powerful Java-based framework that provides a solid foundation for developing 
robust and scalable applications. It incorporates several other
technologies to handle various aspects of the application.

### PostgreSQL and MongoDB
The application uses both PostgreSQL and MongoDB for data storage.
PostgreSQL, a relational database management system, is used for structured data storage,
while MongoDB, a NoSQL database, is utilized for handling unstructured data
and providing flexibility for certain parts of the application.

### RabbitMQ
RabbitMQ serves as the message broker for the application. 
It facilitates asynchronous communication between microservices, allowing for 
decoupled and scalable communication patterns.

### OpenFeign
OpenFeign is integrated into the application to simplify communication
with other microservices. It provides a declarative way to define REST clients 
and automatically handles the communication details, making it easier to
interact with other services.

### Distributed Tracing with Zipkin
The application utilizes Zipkin, a distributed tracing system, to monitor 
and analyze the interactions between microservices. With Zipkin, we can 
gain insights into the system's performance, identify bottlenecks, and optimize 
communication between services.

### Config Server
The application utilizes a Config Server to manage externalized configuration
for the microservices. With the help of Spring Cloud Config, the configuration is 
centralized and can be updated dynamically without requiring a redeployment
of the services. This promotes maintainability and flexibility in a distributed environment.

## Microservices Architecture

The application is designed as a set of microservices to ensure modularity,
scalability, and maintainability. Each microservice handles a specific set of functionalities,
and they communicate through RESTful APIs. The microservices include:

1. **Auth Service**: Responsible for user authentication, registration, and account activation.
2. **User Profile Service**: Manages user profiles, including user information and manager status.
3. **Game Service**: Handles game-related actions, such as creating a new game, checking tries, and fetching game information.
4. **Mail Service**: Manage mail sending actions, such as activation of status and give information for forgotten passwords.

## Deployment and Configuration

Before deploying the application, ensure to configure the necessary properties,
such as database credentials, JWT secret, RabbitMQ settings, MongoDB connection details,
and Config Server configuration, in the application's configuration files.

**Please make sure to first create two roles which are ADMIN and USER**
**after create an admin mail and after that change the role of admin, USER to ADMIN**
