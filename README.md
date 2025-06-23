# YcBank_Microservice
This is a microservices architecture based project developed using Java, Spring Boot, Spring Data JPA, REST API and Spring Cloud

# 🏦 Microservice-Based Banking System

This is a self-developed banking application based on **Microservices architecture** using Spring Boot and Spring Cloud components. It simulates the core banking modules such as **Accounts**, **Loans**, and **Cards**, along with all supporting infrastructure like API Gateway, Service Discovery, and Configuration Server.

---

## 🚀 Modules

- **Accounts Service** – Manages customer accounts and basic information.
- **Loans Service** – Handles loan details for users.
- **Cards Service** – Manages card data including limits and history.
- **Eureka Server** – Enables service discovery and registration.
- **API Gateway** – Acts as the single entry point to all backend services.
- **Config Server** – Centralized configuration management using Spring Cloud Config.
  
---

## 🔧 Technologies Used

- **Spring Boot**
- **Spring Cloud** (Eureka, Gateway, Config Server, LoadBalancer)
- **Spring Data JPA**
- **H2 Database**
- **OpenFeign** – for service-to-service communication
- **Resilience4j** – for:
  - Circuit Breaker
  - Retry pattern
  - Rate Limiting
- **SpringDoc OpenAPI** – for Swagger UI documentation

---

## 📌 Features

- ✅ Modular microservices architecture
- ✅ Centralized config management
- ✅ Fault tolerance using Circuit Breaker
- ✅ Retry mechanism for transient failures
- ✅ Rate limiting for traffic control
- ✅ Inter-service communication with OpenFeign
- ✅ Interactive API documentation with Swagger
- ✅ In-memory H2 database for lightweight testing

---

## 📂 How to Run

> Make sure you run the services in the following order for proper dependency loading:

1. **Eureka Server**
2. **Config Server**
3. **Accounts / Loans / Cards Services**
4. **Gateway Server**

Each service has its own `application.yml` and runs on its own port. Eureka and Config Server allow dynamic registration and externalized configuration.
**API Collection**
https://.postman.co/workspace/My-Workspace~51ead673-5277-4b85-8769-5c06c2e1d346/collection/34656872-3d5fa803-942a-401b-8108-a0eb46ccdf59?action=share&creator=34656872

---

## 🤝 Contributing

This is a personal learning project. Feel free to fork and enhance it!

---

## 📧 Contact

**Yogesh Choudhar**  
Digital Specialist Engineer at Infosys  
www.linkedin.com/in/yogesh-choudhar-6a797015a

