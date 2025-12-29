Project Overview

LINK - https://eat-club-front-end-github.vercel.app/  (used outletName - 46  if dont know outletId)
SMS will not work. Reason i am having Twillio Free Account for sms integration. if really interested to checkout clone this repo see the env variables and setup use your own twillio account.
EatClub Automation is a solution that eliminates the need to manually check restaurant availability on the EatClub platform. When the "Kitchen Full - Come Back Later" message appears, this application continuously monitors the restaurant’s status until it becomes available.

Key Features

Automated Queue Monitoring: Continuously checks the restaurant API every 5 seconds

Instant SMS Notifications: Sends alerts via Twilio when restaurants become available

Non-blocking Architecture: Backend uses background threads for seamless user experience

Form Validation: Input validation using regex for city names and phone numbers

Modern UI: Clean Material-UI interface with responsive design

Real-time Updates: Instant feedback and proper error handling

Tech Stack
Backend (Spring Boot)

Java 17 – Programming language

Spring Boot 3.4.11 – Framework for REST APIs

Spring Web – Web layer and REST endpoints

Twilio SDK 9.3.0 – SMS notifications

Maven – Dependency management

HTTP Client – API calls to EatClub

Frontend (React + Vite)

React 19.2.0 – UI framework

TypeScript 5.9.3 – Type safety

Vite 7.2.2 – Build tool and development server

Material-UI 7.3.5 – Component library

Axios 1.13.2 – HTTP client

Integration & APIs

EatClub Box8 API – Restaurant status monitoring

Twilio API – SMS delivery service

CORS Configuration – Cross-origin request handling
