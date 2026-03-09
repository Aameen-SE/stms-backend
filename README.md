# STMS Backend

**STMS (Student Task Management System)** is a backend REST API built using **Spring Boot** that provides secure task management functionality with **JWT-based authentication**, **MySQL database**, and **Docker containerization**.

This project demonstrates modern backend development practices including secure authentication, layered architecture, database integration, and containerized deployment.

---

# 🚀 Tech Stack

* **Java 17**
* **Spring Boot**
* **Spring Security**
* **JWT Authentication**
* **Spring Data JPA (Hibernate)**
* **MySQL**
* **Docker**
* **Docker Compose**
* **Maven**

---

# 📌 Features

* User Registration
* User Login with JWT Authentication
* Secure REST API endpoints
* Password encryption using Spring Security
* MySQL database persistence
* Stateless authentication using JWT
* Dockerized application deployment
* Multi-container setup using Docker Compose

---

# 🏗️ Architecture

The project follows a **layered architecture** to maintain separation of concerns.

Controller Layer
Handles HTTP requests and responses.

Service Layer
Contains business logic.

Repository Layer
Handles database operations using Spring Data JPA.

Security Layer
Manages authentication and JWT validation.

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
MySQL Database
```

---

# 📁 Project Structure

```
stms-backend
│
├── src/main/java/com/aameen/stms
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── security
│   └── config
│
├── src/main/resources
│   └── application.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# ⚙️ Configuration

Application properties include configuration for:

* MySQL database connection
* JWT secret key
* Token expiration
* JPA settings

Example:

```
spring.datasource.url=jdbc:mysql://mysql:3306/stms
spring.datasource.username=root
spring.datasource.password=root
```

---

# 🐳 Running the Project with Docker

### 1️⃣ Clone the repository

```
git clone https://github.com/Aameen-SE/stms-backend.git
cd stms-backend
```

### 2️⃣ Build and start containers

```
docker compose up --build
```

### 3️⃣ Services Started

| Container               | Port |
| ----------------------- | ---- |
| Spring Boot Application | 8080 |
| MySQL Database          | 3306 |

### 4️⃣ Access Application

```
http://localhost:8080
```

---

# 🔐 Authentication Flow (JWT)

1. User registers or logs in.
2. Server validates credentials.
3. Server generates a JWT token.
4. Client sends token in request headers.
5. JWT filter validates the token before allowing access.

Example header:

```
Authorization: Bearer <JWT_TOKEN>
```

---

# 📡 API Endpoints

## Authentication

### Register User

```
POST /api/auth/register
```

### Login User

```
POST /api/auth/login
```

Returns JWT token on successful authentication.

---

## Protected APIs

These endpoints require a **valid JWT token** in the Authorization header.

Example:

```
Authorization: Bearer <token>
```

---

# 🧪 Testing APIs

You can test APIs using:

* Postman
* Insomnia
* Curl

---

# 🗄️ Database

Database used:

```
MySQL 8
```

Database name:

```
stms
```

Tables are automatically created using **Hibernate JPA**.

---

# 🐙 Version Control

Version control is managed using **Git** and hosted on **GitHub**.

Repository:

```
https://github.com/Aameen-SE/stms-backend
```

---

# 👨‍💻 Author

**Aameen Hussain**

Backend Developer
Java | Spring Boot | REST APIs

---

# 📈 Future Improvements

Possible enhancements:

* Task management APIs
* Role-based access control
* API documentation with Swagger
* Cloud deployment (AWS / Render / Railway)
* CI/CD pipeline
* Unit and integration testing

---

# 📄 License

This project is for educational and learning purposes.
