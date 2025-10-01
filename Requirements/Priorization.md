# Requirements Prioritization Using the MoSCoW Method

This document outlines the prioritization of both functional and non-functional requirements for the backend development of the appointment scheduling system for the Faculty of Psychology at the Universidad Autónoma de Yucatán (UADY). The MoSCoW method was applied to classify each requirement based on its necessity, feasibility, and relevance to the system’s objectives.

## Functional Requirements

### FR1 – Submit Appointment Request
**Priority**: Must Have  
**Feasibility**: High. This functionality can be implemented using standard HTML forms and JavaScript logic to transmit user data to the backend.  
**Importance**: Essential. This is the core feature of the system and enables users to initiate the appointment process.

### FR2 – Form Field Validation
**Priority**: Must Have  
**Feasibility**: High. Validation can be performed both client-side and server-side using regular expressions and predefined rules.  
**Importance**: High. Ensures the integrity and correctness of user-submitted data.

### FR3 – Confirmation Notification
**Priority**: Should Have  
**Feasibility**: Medium. Requires integration with an email service to notify users upon successful submission.  
**Importance**: Moderate. Enhances user experience and provides assurance of successful interaction.

### FR4 – Access Code Delivery
**Priority**: Could Have  
**Feasibility**: Medium. Involves secure generation and transmission of access codes, along with persistent storage.  
**Importance**: Moderate. Facilitates user access to appointment details at any time.

### FR5 – Display Available Time Slots
**Priority**: Must Have  
**Feasibility**: High. Can be implemented through database queries and dynamic rendering of available slots.  
**Importance**: High. Enables users to select suitable appointment times.

### FR6 – Integration with Scheduling System
**Priority**: Must Have  
**Feasibility**: High. Requires backend logic to interface with the scheduling database and retrieve availability.  
**Importance**: High. Ensures consistency and prevents scheduling conflicts.

### FR7 – Store Appointment Data
**Priority**: Must Have  
