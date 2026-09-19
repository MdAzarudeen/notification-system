NOTIFICATION SYSTEM — PROJECT OVERVIEW

1. PROJECT GOAL

Build a production-grade, scalable Notification System using Java and Spring Boot.

The system will provide a centralized service for sending notifications through multiple channels such as:

- Email
- SMS
- Push Notification

The project is not intended to be a simple CRUD application.

The main goal is to learn and implement real-world backend engineering concepts such as:

- REST APIs
- Database design
- Microservices
- Asynchronous processing
- Kafka
- Redis
- Caching
- Retry mechanisms
- Dead Letter Queue
- Idempotency
- Rate limiting
- Horizontal scaling
- Docker
- AWS deployment
- CI/CD
- Monitoring
- System Design


2. CORE ARCHITECTURE

Initial flow:

Client / Other Services
|
v
Notification API
|
v
Notification Service
|
v
PostgreSQL


Final production-style flow:

Order Service / Payment Service / User Service
|
v
Kafka
|
v
Notification Service
|
---------------------
|         |         |
v         v         v
Email      SMS       Push
Worker     Worker     Worker
|         |         |
v         v         v
Providers / External Services


Supporting components:

PostgreSQL
Redis
Kafka
Docker
AWS
Prometheus
Grafana
GitHub Actions


3. TECH STACK

Programming Language:
- Java 17

Backend Framework:
- Spring Boot 4.0.8
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Boot Actuator

Database:
- PostgreSQL

Messaging:
- Apache Kafka

Caching / Performance:
- Redis

Containerization:
- Docker

Cloud:
- AWS

CI/CD:
- GitHub Actions

Monitoring:
- Prometheus
- Grafana

API Testing / Documentation:
- Postman
- Swagger / OpenAPI

Version Control:
- Git
- GitHub


4. WHAT I WILL LEARN FROM EACH TECHNOLOGY

JAVA

Topics:
- OOP
- Collections
- Exception Handling
- Streams
- Optional
- Multithreading basics
- Concurrency basics
- CompletableFuture basics
- Clean Code
- SOLID principles

Goal:
Strengthen Java fundamentals and understand how Java is used in backend production systems.


SPRING BOOT

Topics:
- REST APIs
- Controllers
- Services
- Dependency Injection
- DTOs
- Validation
- Exception Handling
- Global Exception Handler
- Configuration
- Profiles
- Actuator
- Logging
- Spring Security
- API documentation

Goal:
Build production-quality REST APIs.


POSTGRESQL

Topics:
- Database design
- Tables
- Primary Keys
- Foreign Keys
- Relationships
- CRUD operations
- Joins
- Indexing
- Transactions
- Normalization
- Query optimization
- Connection pooling

Goal:
Understand relational database design and performance.


JPA / HIBERNATE

Topics:
- Entities
- Repositories
- Relationships
- Lazy vs Eager loading
- JPQL
- Transactions
- Persistence context
- N+1 problem
- Query optimization

Goal:
Understand ORM and how Spring Boot communicates with PostgreSQL.


KAFKA

Topics:
- Producer
- Consumer
- Topic
- Partition
- Offset
- Consumer Group
- Message ordering
- Replication basics
- Async processing
- Consumer failure
- Retry
- Dead Letter Queue

Goal:
Understand event-driven architecture and asynchronous processing.


REDIS

Topics:
- Key-value storage
- Caching
- TTL
- Cache-aside pattern
- Cache invalidation
- Rate limiting
- Distributed cache basics

Goal:
Understand caching and performance optimization.


SPRING SECURITY

Topics:
- Authentication
- Authorization
- JWT
- Roles / Permissions
- Securing APIs

Goal:
Secure the notification APIs.


DOCKER

Topics:
- Images
- Containers
- Dockerfile
- Docker Compose
- Environment variables
- Networking
- Running PostgreSQL, Kafka and Redis using Docker

Goal:
Containerize the complete application and its dependencies.


AWS

Topics:
- EC2
- IAM basics
- Security Groups
- RDS basics
- Cloud deployment
- Environment configuration
- Basic cloud architecture

Goal:
Deploy the project to a real cloud environment.


GITHUB ACTIONS

Topics:
- CI
- Build
- Test
- Docker image build
- Automated deployment basics

Goal:
Create a basic production-style CI/CD pipeline.


PROMETHEUS + GRAFANA

Topics:
- Metrics
- Health checks
- Application monitoring
- CPU / Memory
- Request count
- Request latency
- Error rate
- Kafka-related metrics basics

Goal:
Understand observability and production monitoring.


5. PROJECT PHASES

PHASE 1 — BASIC SPRING BOOT API

Goal:
Create the basic notification API.

Topics:
- Spring Boot setup
- Project structure
- Controller
- Service
- Repository
- DTO
- Request / Response
- REST API
- Postman testing

Status:
IN PROGRESS

Current API:

POST /api/v1/notifications


PHASE 2 — REQUEST VALIDATION & ERROR HANDLING

Topics:
- Request DTO
- Bean Validation
- Required fields
- Enum validation
- Global Exception Handler
- Standard error response
- HTTP status codes

Goal:
Make the API production-quality.


PHASE 3 — POSTGRESQL + JPA

Topics:
- PostgreSQL setup
- Database schema
- Notification entity
- User / recipient information
- Notification status
- JPA Repository
- Save notification
- Fetch notification
- Transactions
- Indexing

Goal:
Persist notification data.


PHASE 4 — NOTIFICATION STATUS & BUSINESS LOGIC

Topics:
- CREATED
- QUEUED
- PROCESSING
- SENT
- FAILED

Implement:
- Notification lifecycle
- Business validation
- Service layer logic

Goal:
Build a proper notification lifecycle.


PHASE 5 — KAFKA INTEGRATION

Topics:
- Kafka setup
- Producer
- Topic
- Consumer
- Consumer Group
- Partitions
- Offset
- Spring Kafka

Flow:

API
|
v
PostgreSQL
|
v
Kafka
|
v
Notification Consumer

Goal:
Move notification processing from synchronous to asynchronous architecture.


PHASE 6 — NOTIFICATION WORKERS

Create separate processing logic for:

- Email
- SMS
- Push

Topics:
- Consumer processing
- External provider simulation
- Provider failure
- Success / failure status

Goal:
Separate notification processing from API requests.


PHASE 7 — RETRY + DEAD LETTER QUEUE

Topics:
- Retry
- Retry count
- Retry delay
- Exponential backoff basics
- Dead Letter Queue
- Failed message handling

Flow:

Kafka
|
v
Consumer
|
+---- Success ----> SENT
|
+---- Failure
|
v
Retry
|
+---- Success
|
+---- Failure
|
v
DLQ

Goal:
Build reliable message processing.


PHASE 8 — IDEMPOTENCY

Topics:
- Duplicate messages
- Idempotency key
- Duplicate prevention
- Exactly-once vs at-least-once concepts

Goal:
Ensure the same notification is not accidentally sent multiple times.


PHASE 9 — REDIS + CACHING

Topics:
- Redis setup
- Cache-aside pattern
- TTL
- Cache invalidation
- Frequently accessed data

Goal:
Improve application performance.


PHASE 10 — RATE LIMITING

Topics:
- Rate limiting
- Redis-based rate limiter
- Per-user limits
- API protection

Example:

User can send:
100 notifications / minute

If limit exceeds:
HTTP 429 Too Many Requests


PHASE 11 — SECURITY

Topics:
- Spring Security
- JWT
- Authentication
- Authorization
- Roles
- Protected APIs

Goal:
Secure the notification platform.


PHASE 12 — DOCKER

Topics:
- Dockerfile
- Docker image
- Docker container
- Docker Compose
- Environment variables
- Container networking

Run locally:

Spring Boot
PostgreSQL
Kafka
Redis

using containers.


PHASE 13 — TESTING

Topics:
- Unit testing
- JUnit
- Mockito
- Controller tests
- Service tests
- Repository tests
- Kafka consumer tests
- Integration testing

Goal:
Make the project production-quality and interview-ready.


PHASE 14 — AWS DEPLOYMENT

Topics:
- AWS basics
- EC2
- IAM
- Security Groups
- RDS
- Environment variables
- Deployment

Goal:
Deploy the backend to the cloud.


PHASE 15 — CI/CD

Topics:
- GitHub Actions
- Automated build
- Automated tests
- Docker image
- Deployment pipeline

Flow:

Git Push
|
v
GitHub Actions
|
v
Build
|
v
Test
|
v
Docker Image
|
v
Deploy


PHASE 16 — MONITORING & OBSERVABILITY

Topics:
- Spring Boot Actuator
- Prometheus
- Grafana
- Health checks
- Metrics
- Request latency
- Error rate
- Throughput
- JVM metrics

Goal:
Understand how production applications are monitored.


PHASE 17 — SCALING & SYSTEM DESIGN

Topics:
- Load Balancer
- Horizontal scaling
- Multiple application instances
- Multiple Kafka consumers
- Consumer groups
- Kafka partitions
- Database replication basics
- Database scaling
- Redis
- Fault tolerance
- High availability
- Bottleneck identification

Goal:
Convert the project into a strong System Design interview discussion.


6. FINAL ARCHITECTURE

                    CLIENTS
                       |
                       v
                Load Balancer
                       |
                       v
              Notification APIs
                       |
                ----------------
                |              |
                v              v
           PostgreSQL        Redis
                |
                v
               Kafka
                |
        -------------------
        |        |        |
        v        v        v
   Email     SMS      Push
   Worker    Worker    Worker
   |        |        |
   v        v        v
   Provider Provider Provider

Supporting:

GitHub Actions → CI/CD
Docker → Containers
AWS → Cloud
Prometheus → Metrics
Grafana → Monitoring


7. MAIN INTERVIEW CONCEPTS THIS PROJECT SHOULD COVER

Backend:
- Java
- Spring Boot
- REST API
- JPA / Hibernate
- PostgreSQL
- Exception Handling
- Security

System Design:
- Functional Requirements
- Non-Functional Requirements
- Horizontal Scaling
- Load Balancing
- Caching
- Message Queues
- Kafka
- Database Scaling
- Reliability
- Fault Tolerance

Distributed Systems:
- Async Processing
- Event-driven architecture
- Idempotency
- Retry
- DLQ
- Rate Limiting
- Consumer Groups
- Partitioning

DevOps:
- Docker
- CI/CD
- AWS

Observability:
- Metrics
- Health Checks
- Prometheus
- Grafana


8. DEVELOPMENT RULE

Do NOT implement all technologies at once.

Build incrementally:

Basic API
↓
Validation
↓
PostgreSQL
↓
Business Logic
↓
Kafka
↓
Workers
↓
Retry + DLQ
↓
Idempotency
↓
Redis
↓
Rate Limiting
↓
Security
↓
Testing
↓
Docker
↓
AWS
↓
CI/CD
↓
Monitoring
↓
Scaling


9. CURRENT PROJECT STATUS

Completed:

- Spring Initializr project created
- Java 17
- Spring Boot 4.0.8
- Maven
- Basic project structure
- Controller package
- Service package
- Repository package
- NotificationController
- NotificationService
- NotificationRepository
- Basic POST API
- Postman testing

Current working API:

POST /api/v1/notifications

Current response:

"Notification created successfully"


NEXT STEP:

Request DTO

Expected request:

{
"userId": "U101",
"type": "EMAIL",
"message": "Your order has been shipped"
}

Then:

DTO
→ Validation
→ Response DTO
→ PostgreSQL
→ Kafka
→ Workers
→ Reliability
→ Redis
→ Docker
→ AWS
→ Monitoring
→ Scaling


10. PROJECT OBJECTIVE

This project should demonstrate the ability to:

1. Build a backend application using Java and Spring Boot.
2. Design and implement REST APIs.
3. Work with PostgreSQL and JPA/Hibernate.
4. Build asynchronous systems using Kafka.
5. Implement caching and rate limiting using Redis.
6. Handle failures using retry and DLQ.
7. Handle duplicate messages using idempotency.
8. Secure APIs.
9. Containerize applications using Docker.
10. Deploy applications to AWS.
11. Build basic CI/CD.
12. Monitor applications using Prometheus and Grafana.
13. Explain the complete architecture and technology decisions in a backend/system-design interview.

The project should be treated as a production-oriented learning project, not as a simple CRUD project.