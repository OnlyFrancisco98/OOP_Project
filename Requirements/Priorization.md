# 📌 Requirements Prioritization – MoSCoW Method

This document presents the prioritization of functional and non-functional requirements for the backend development of the appointment scheduling system for the Faculty of Psychology at UADY. The MoSCoW method was used to classify each requirement based on its importance and feasibility.

---

## ✅ Functional Requirements (FR)

### FR1 – Submit Appointment Request
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Can be implemented using HTML forms and JavaScript logic to send data to the backend.  
- **Importance**: Critical – This is the core functionality of the system.

---

### FR2 – Form Field Validation
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Achievable using frontend and backend validation with regular expressions and business rules.  
- **Importance**: High – Ensures data integrity and user input correctness.

---

### FR3 – Successful Submission Notification
- **MoSCoW Priority**: Should Have  
- **Feasibility**: Medium – Requires integration with an email service provider.  
- **Importance**: Medium – Enhances user experience and trust.

---

### FR4 – Access Code Delivery
- **MoSCoW Priority**: Could Have  
- **Feasibility**: Medium – Requires secure code generation and storage.  
- **Importance**: Medium – Allows users to retrieve appointment details later.

---

### FR5 – View Available Time Slots
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Can be implemented with database queries and dynamic rendering.  
- **Importance**: High – Essential for users to choose their preferred time.

---

### FR6 – Integration with Scheduling System
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Requires database access and logic to check availability.  
- **Importance**: High – Maintains consistency and prevents double bookings.

---

### FR7 – Store Appointment Data
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Can be implemented using a relational or NoSQL database.  
- **Importance**: Critical – Necessary for tracking and managing appointments.

---

## ⚙️ Non-Functional Requirements (NFR)

### NFR1 – Scalability
- **MoSCoW Priority**: Should Have  
- **Feasibility**: High – Achievable with modular architecture and decoupled services.  
- **Importance**: High – Enables future growth and integration.

---

### NFR2 – Portability
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Can be ensured using standard web technologies (HTML, CSS, JavaScript).  
- **Importance**: High – Ensures accessibility across devices and platforms.

---

### NFR3 – Availability
- **MoSCoW Priority**: Should Have  
- **Feasibility**: Medium – Requires reliable hosting and monitoring infrastructure.  
- **Importance**: Medium – Improves system accessibility and user satisfaction.

---

### NFR4 – Maintainability
- **MoSCoW Priority**: Must Have  
- **Feasibility**: High – Achievable through clean code practices, documentation, and modular design.  
- **Importance**: High – Facilitates future updates and debugging.

---

## 📊 Summary

RequirementPriorityFeasibilityImportanceFR1MustHighCritical| FR2         | Must     | High        | High       |
| FR3         | Should   | Medium      | Medium     |
| FR4         | Could    | Medium      | Medium     |
| FR5         | Must     | High        | High       |
| FR6         | Must     | High        | High       |
| FR7         | Must     | High        | Critical   |
| NFR1        | Should   | High        | High       |
| NFR2        | Must     | High        | High       |
| NFR3        | Should   | Medium      | Medium     |
| NFR4        | Must     | High        | High       |

---

> This prioritization helps guide development efforts by focusing on the most critical and feasible features first, ensuring a functional and scalable system foundation.

