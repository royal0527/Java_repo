# Invoice Management Spring Boot Application

This is a Spring Boot 3+ application developed using Java 17 that provides REST API endpoints to manage invoices. The application includes unit and integration tests for various components and a Dockerfile for containerization.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Docker](#docker)
- [Contributing](#contributing)

## Features

- Create, retrieve, update, and delete invoices via REST API.
- Full test coverage using unit and integration tests.
- Docker support for easy deployment and scalability.

## Technologies Used

- **Java 17**
- **Spring Boot 3+**
    - Spring Web (for building REST APIs)
    - Spring Data JPA (for database interaction)
    - H2 Database (for in-memory database, useful for testing)
- **JUnit 5** and **Mockito** (for unit and integration testing)
- **Maven** (for dependency management and build)
- **Docker** (for containerization)

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-repo/invoice-management.git
