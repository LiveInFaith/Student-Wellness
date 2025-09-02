# BC Student Wellness Management System

### Project Overview
The system is designed to manage student wellness services and comprises two major components:

1. **Web Application (Milestone 1)** – Built with JSP, Servlets, and PostgreSQL
2. **Desktop Application (Milestone 2)** – Built with Java Swing and JavaDB

---

## BC Thrive Portal (Milestone 1: Web Application – Login & Registration)

## Features
- Student Registration and Login
- Input validation (email, password strength, phone number)
- Session management using `HttpSession`
- Secure password handling (hashed storage)
- PostgreSQL backend integration
- Simple dashboard with personalized greeting and logout

## Pages
- `index.jsp` – Home with navigation
- `register.jsp` – New student registration
- `login.jsp` – Student login
- `dashboard.jsp` – Post-login dashboard

## Servlets
- `RegisterServlet.java` – Handles registration logic
- `LoginServlet.java` – Manages login authentication

## PostgreSQL Schema
```sql
CREATE TABLE users (
    student_number VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    password TEXT NOT NULL
);
```

## Technologies Used
- Java SE 17+
- JSP & Servlets
- PostgreSQL
- HTML/CSS (for JSP pages)
- Git & GitHub

## How to Run

### Web App (Milestone 1)
- Set up PostgreSQL with the provided users table.
- Configure your database credentials in the DBConnection class.
- Deploy the web app to a servlet container (e.g., Apache Tomcat).
- Open index.jsp and test registration and login.


## Group Members
- Hayley Treutens: @CountessOreo
-  – Di-Jancko Calitz: @SiJanckoCalitz
-  – Marco Jacobs: @Piet-Potlood
-  – Heinrich Rudman: @LiveInFaith
All members contributed to both milestones and understand all concepts.
