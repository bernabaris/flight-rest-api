# Flight Rest API 🛩️

## Overview


`flight-rest-api` is a RESTful API designed to provide a seamless experience in searching for flights. Built with Spring Boot, this project offers scalable solutions for flight search applications.
## Features

- **Flight Search**: Allows users to search for available flights based on various criteria.
- **User Authentication**: Basic Authentication (Secure user registration and login.)

## Getting Started

### Prerequisites

- Java 17
- Maven
- Docker Desktop
- MariaDB


### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/bernabaris/flight-rest-api.git
2. Navigate to directory for MariaDB setup with Docker:
   ```sh
   cd flight-rest-api/docker/mariadb
3. Start the MariaDB container by running:
   ```sh
   docker compose up -d  
4. Build the project with Maven:
   ```sh
   mvn clean install
5. Run the application:
   ```sh
   mvn spring-boot:run
