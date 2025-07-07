# Jio Onboarding Demo

This repository demonstrates a **Spring Boot** microservice for a hypothetical **JioTV onboarding system**.

## Features
- RESTful API `/api/v1/onboarding` to onboard users
- Produces onboarding events to **Apache Kafka**
- Stores user data in **PostgreSQL**
- Dockerized with `docker-compose` to spin up **Kafka + PostgreSQL + App**

## Running Locally

```bash
# Build the project
mvn clean package

# Start services
docker compose up --build
```

API available at `http://localhost:8080/api/v1/onboarding`

Example request:

```bash
curl -X POST http://localhost:8080/api/v1/onboarding \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com"}'
```

## Architecture

```
+---------+      REST       +-------------------+     Kafka      +-----------------+
|  Client | ----> POST ----> | OnboardingService | -- produce --> |  user.onboarded |
+---------+                 +-------------------+                +-----------------+
                                    |  JPA
                                    v
                            +------------------+
                            | PostgreSQL users |
                            +------------------+
```

## License
MIT
