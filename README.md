# 🎟️ Event Ticketing Platform   

A full-stack **Event Ticketing Platform** built with **Spring Boot (Monolithic Architecture)** and **ReactJS**, designed to streamline the process of **creating, managing, and booking event tickets**.  
The platform ensures a smooth experience for both **event organizers** and **attendees** by combining:  
- ✅ **Secure authentication & authorization** via **Keycloak**  
- 🎨 **Modern, intuitive UI** built with React  
- ⚡ **Robust backend** powered by Spring Boot  
- 📈 **Scalability & maintainability** through clean architecture and modular design 

<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white">
  <img src="https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB">
  <img src="https://img.shields.io/badge/Keycloak-2C7FBF?style=for-the-badge&logo=keycloak&logoColor=white">
  <img src="https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white">
</p>  

## 📑 Table of Contents

1. [✨ Core Features](#core-features)
   - [👩‍💼 Event Management (Organizers)](#event-management-organizers)
   - [🎟️ Published Events (Attendees)](#published-events-attendees)
   - [🛒 Ticketing Lifecycle](#ticketing-lifecycle)
   - [✅ Ticket Validation](#ticket-validation)
   - [🛡️ Security & Reliability](#security--reliability)
   - [🚀 End-to-End Flow](#end-to-end-flow)
   - [🌟 Benefits](#benefits)
2. [🔐 Security & Configuration Implementations](#security--configuration-implementations)
   - [🛡️ Authentication & Authorization](#authentication--authorization)
   - [🔄 Stateless Security Architecture](#stateless-security-architecture)
   - [⚙️ Custom Security Filters](#custom-security-filters)
   - [🎟️ QR Code Ticketing](#qr-code-ticketing)
   - [📊 Auditing & Traceability](#auditing--traceability)
3. [🚨 Error Handling](#error-handling)
4. [🔄 DTO Mapping](#dto-mapping)
5. [🛠️ Technology Stack](#-technology-stack)




---

## ✨ Core Features

The platform provides a **comprehensive event management and ticketing ecosystem**, delivering a seamless experience for **organizers**, **attendees**, and **event staff**.  
It is designed with a strong focus on **security, scalability, and reliability**, ensuring smooth operations even under high concurrency.

---

### 👩‍💼 Event Management (Organizers)
Organizers benefit from a full-featured back-office to manage the entire event lifecycle:

- **Create & Configure Events**: Define event metadata (name, venue, schedule, description) and attach multiple **ticket types** with different pricing and availability rules.  
- **Dynamic Updates**: Modify event details or adjust ticket types at any stage, ensuring flexibility.  
- **Event Listings**: Paginated and filterable listing of all events scoped to the logged-in organizer.  
- **Detailed Insights**: Retrieve structured event details including ticket availability and associated metadata.  
- **Secure Deletion**: Events can be safely removed, respecting strict ownership and authentication boundaries.  

✔️ **Outcome**: Organizers gain **fine-grained control** over their events, reducing overhead while keeping data integrity intact.

---

### 🎟️ Published Events (Attendees)
Attendees enjoy a **frictionless discovery and booking experience**:

- **Browse & Discover**: Explore all `PUBLISHED` events in a paginated, user-friendly catalog.  
- **Full-Text Search**: Search by event name, venue, or description with optimized queries.  
- **Rich Event Details**: Access complete event information (description, venue, dates, ticket types, availability).  

✔️ **Outcome**: Attendees can **easily find, evaluate, and book events** with confidence.

---

### 🛒 Ticketing Lifecycle
The ticketing system ensures **secure, atomic, and fraud-resistant transactions**:

- **Secure Ticket Purchases**:  
  - Validates purchaser identity.  
  - Uses **pessimistic locking** to prevent race conditions and overselling.  
  - Enforces availability constraints with precise exceptions (`TicketsSoldOutException`).  
  - Persists tickets with status `PURCHASED`.  
  - Automatically generates and attaches a **unique QR Code** per ticket.  

- **Ticket Management**:  
  - **List Tickets**: Attendees can view all their tickets in a paginated, scoped manner.  
  - **Get Ticket Details**: Detailed ticket view enriched with event metadata (price, description, venue, schedule).  
  - **Secure Access**: Ownership enforced via **JWT authentication** (Keycloak).  

- **QR Code Integration**:  
  - Each ticket is associated with a **Base64-encoded PNG QR Code** generated using `QRCodeWriter`.  
  - QR Codes are persisted with `ACTIVE` status and can be downloaded as raw PNG images.  
  - Invalid or corrupted QR codes trigger safe fallback exceptions.  

✔️ **Outcome**: A **fraud-proof and reliable ticketing pipeline**, designed for both scalability and trust.

---

### ✅ Ticket Validation
Event staff can **validate attendee access** with multiple methods:

- **QR Scan Validation**: Tickets are validated at the gate via QR code scanning.  
- **Manual Validation**: Staff can validate by ticket ID as a fallback.  
- **One-Time Use Guarantee**: Once validated as `VALID`, subsequent scans automatically flag the ticket as `INVALID`.  
- **Audit Trail**: Every validation attempt is persisted (`TicketValidation` entity) with method, timestamp, and outcome.  

✔️ **Outcome**: Ensures **fraud prevention, accountability, and traceability** of all event check-ins.

---

### 🛡️ Security & Reliability
- **Authentication & Authorization**: All organizer and attendee actions are scoped to their identity via **Keycloak JWT**.  
- **Ownership Enforcement**: Organizers cannot view or modify events they don’t own, attendees cannot access tickets they didn’t purchase.  
- **Public vs Private Endpoints**: Public endpoints (event discovery) are openly accessible, while organizer/ticketing endpoints require authentication.  
- **Transactional Boundaries**: Critical operations (e.g., ticket purchase) are wrapped in `@Transactional` to ensure atomicity and consistency.  
- **Domain-Driven Error Handling**: Custom exception hierarchy (`EventNotFoundException`, `QrCodeGenerationException`, etc.) provides clear API feedback.  

✔️ **Outcome**: Guarantees **system integrity, data safety, and smooth user experience** even under heavy traffic.

---

### 🚀 End-to-End Flow
1. **Organizer publishes an event** with multiple ticket types.  
2. **Attendee browses/searches** published events and selects one.  
3. **Ticket purchase** is completed (availability checked, QR Code generated, transaction persisted).  
4. **Ticket retrieved** by attendee along with a downloadable QR Code.  
5. **Event check-in** occurs via QR scan or manual validation.  
6. **Audit log persisted**, ensuring transparency and preventing fraud.  

---

### 🌟 Benefits
- **Seamless experience** for organizers, attendees, and event staff.  
- **Fraud-proof entry control** with one-time QR validation.  
- **Highly concurrent and scalable design**, resistant to overselling.  
- **Developer-friendly**: Domain-driven architecture with modular services and clear error handling.  
- **User-centric**: Predictable APIs, clean workflows, and secure self-service ticket management.  
 


---

## 🔐 Security & Configuration Implementations  

The platform integrates **enterprise-grade security** powered by **Spring Security** and **Keycloak**, ensuring compliance with modern authentication and authorization standards. The configuration follows best practices for **stateless REST APIs** and **role-based access control**.  

### 🛡️ Authentication & Authorization  
- Implemented **OAuth2 / OpenID Connect** authentication with **Keycloak** as the Identity Provider.  
- Secured API endpoints with **JWT-based authentication**.  
- Custom **`JwtAuthenticationConverter`** to transform Keycloak’s `realm_access.roles` into Spring Security authorities.  
- Fine-grained **role-based access control (RBAC)**:  
  - `ORGANIZER`: Authorized to create and manage events.  
  - `ATTENDEE`: Restricted to browsing and booking events.  
  - Public access (`permitAll`) for published events browsing.  

### 🔄 Stateless Security Architecture  
- Configured **stateless session management** (`SessionCreationPolicy.STATELESS`) for scalability and REST compliance.  
- Disabled **CSRF protection** (token-based security removes the need for CSRF tokens).  
- Enforced **bearer token validation** via Keycloak’s JWT support.  

### ⚙️ Custom Security Filters  
- Added **`UserProvisioningFilter`** (executed post-authentication) to automatically provision users in the system upon first login.  

### 🎟️ QR Code Ticketing  
- Integrated **ZXing (`QRCodeWriter`)** for generating **QR codes** tied to event tickets.  
- Enables **digital ticket validation** through QR code scanning at event check-in.  

### 📊 Auditing & Traceability  
- Enabled **Spring Data JPA Auditing** to track entity lifecycle changes (`created_at`, `updated_at`, etc.).  
- Ensures accountability and improves system traceability for compliance.  

---

## 🚨 Error Handling  

The platform implements a **robust, centralized error handling mechanism** to provide consistent, clear, and user-friendly API responses while maintaining clean backend logic.  

- **Custom Exception Hierarchy**: Domain-specific exceptions (e.g., `EventNotFoundException`) extend a base `EventTicketException` for precise categorization and semantic error handling.  
- **Global Exception Handler**: A unified `@RestControllerAdvice` (`GlobalExceptionHandler`) intercepts all exceptions, transforming them into structured `ErrorDto` responses with appropriate HTTP status codes.  
- **Validation Enforcement**: Input validation via `@Valid` and Jakarta Validation ensures request errors are caught and returned in a standardized, predictable format.  
- **Structured Logging & Fail-Safe Protection**: All exceptions are logged with full stack traces, and unexpected errors are gracefully handled to prevent system crashes.  

✨ **Benefits**: Predictable and consistent API responses, improved usability, cleaner business logic, and faster debugging through structured logging.

---

## 🔄 DTO Mapping  

To ensure a **clean separation between domain models and API contracts**, the platform leverages **MapStruct** for efficient and type-safe object mapping.  

- **EventMapper**: Handles conversions between `Event` entities and multiple DTOs for event creation, updates, and detailed/published views.  
- **TicketMapper**: Maps `Ticket` and `TicketType` entities into DTOs, enriching responses with event metadata (e.g., venue, schedule, pricing).  
- **TicketValidationMapper**: Simplifies mapping of `TicketValidation` entities into validation response DTOs for ticket scanning and verification.  

✔️ **Benefits**:  
- Eliminates repetitive boilerplate code.  
- Guarantees consistent, maintainable DTO transformations.  
- Keeps controllers and services focused on **business logic**, not data conversion.  

---

## 🛠️ Technology Stack  

The platform is built with a **modern, production-ready stack** that ensures performance, scalability, and maintainability:  

### 🔧 Backend  
- **Spring Boot 3** – Core framework for building the REST API.  
- **Spring Security** – Integrated with Keycloak for authentication & authorization.  
- **Spring Data JPA (Hibernate)** – ORM for efficient database interaction.  
- **MapStruct** – Compile-time DTO mapping for cleaner service and controller layers.  
- **Jakarta Validation** – Request validation for predictable API behavior.  

### 🎨 Frontend  
- **React 19** – Modern component-based UI library.  
- **React Router 7** – Client-side routing and navigation.  
- **TailwindCSS 4** – Utility-first CSS framework for responsive design.  
- **Tailwind Merge** – Class merging utility for Tailwind.  
- **Radix UI Components** – Accessible UI primitives (Dialog, Dropdown, Avatar, Alert, etc.).  
- **Lucide React** – Open-source icon library.  
- **React Day Picker** – Date picker component.  
- **@yudiel/react-qr-scanner** – QR code scanning integration.  
- **jwt-decode / react-oidc-context / oidc-client-ts** – OIDC & JWT-based authentication management.  
- **clsx & class-variance-authority** – Dynamic className handling and utility variants.  
- **tw-animate-css** – Animations library for Tailwind.  
- **Vite** – Frontend build tool and dev server.  
- **TypeScript 5** – Strongly typed JavaScript superset.  
- **ESLint & Prettier** – Code quality, linting, and formatting.   

### 🗄️ Database & Persistence  
- **PostgreSQL** – Relational database optimized for consistency and scalability.  
- **Flyway** – Database versioning and migration management.  

### 🔐 Identity & Security  
- **Keycloak** – Open-source Identity and Access Management (IAM) for OAuth2/OIDC + JWT.  
- **JWT (JSON Web Token)** – Stateless authentication between clients and server.  

### 🧰 Utilities & Tools  
- **ZXing (`QRCodeWriter`)** – QR Code generation for digital tickets.  
- **Lombok** – Reduces boilerplate code in Java classes.  
- **Maven** – Build and dependency management.  
- **Docker** (optional) – Containerization for local development and deployment.