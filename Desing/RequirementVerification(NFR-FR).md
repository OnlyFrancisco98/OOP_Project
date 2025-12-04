# Requirements Verification Report

## 1. Objective
The objective of requirements verification is to ensure that all functional and non-functional requirements defined in the Software Requirements Specification (SRS) document have been correctly implemented and are fulfilled by the developed system.

---

## 2. Verification Methods and Strategy
The following verification methods were employed to validate the requirements:

- **Code Reviews:** Conducted to verify requirements related to coding standards and security.  
- **Demo Testing:** For functionality verification, demo tests were performed on isolated code sections. For example, in the email confirmation service, a separate module was created with the service and an HTML demo to capture data. After confirming local functionality through testing, it was integrated with the database and the appointment request HTML view.  
- **System Testing:** Comprehensive testing of integrated components.  
- **Integration Testing:** Validation of API connections and data synchronization.  
- **Performance Testing:** Load and stress testing for non-functional requirements.  
- **Security Testing:** Validation of encryption, authentication, and data protection mechanisms.

---

## 3. Verification Traceability Matrix

---ndaksd---

# Functional Requirements

| ID | Requirement Description | Verification Method | Success Criteria|
|----|-------------------------|----------------------|------------------|
| **FR-01** | The system must connect to the scheduling module (via API or defined feed) to query and consume available time slots in real time, using it as the single source of truth when creating or rescheduling an appointment. | Integration Testing, Demo | Successful connection to scheduling API; real-time slot retrieval functioning |  |  |
| **FR-02** | The system must synchronize in real time all changes made (reschedules, cancellations) with the scheduling module, ensuring that time slots are correctly released or blocked as appropriate. | Integration Testing | Changes immediately reflected in scheduling module; slots properly updated |  |  |
| **FR-03** | The system must include a state management mechanism for appointments (requested, confirmed, rescheduled, cancelled, completed, no-show) to facilitate validations. | Code Review, System Testing | All status transitions working correctly; validations enforced |  |  |
| **FR-04** | The system must allow users to create an appointment request that generates a unique identifier, including patient ID, service, selected slot, professional, and status. | Demo Testing, System Testing | Unique ID generated; all required fields captured; request stored successfully |  |  |
| **FR-05** | Once an appointment is confirmed, the system must generate a temporary credential (token or temporary password with expiration) and send a confirmation email containing the appointment details. | Demo Testing | Temporary credentials generated with expiration; confirmation email sent with correct details |  |  |
| **FR-06** | The system must allow users to reschedule an existing appointment, update the status to rescheduled, and maintain an immutable history of all changes (dates, actor, reason). | System Testing | Rescheduling functional; status updated; change history maintained |  |  |
| **FR-07** | The system must allow the patient (or authorized actor) to cancel or delete their appointment, update the status to cancelled, and record the action in the history log with reason and timestamp. | System Testing | Cancellation working; status updated; audit log populated |  |  |
| **FR-08** | The system must automatically expire unconfirmed appointment requests after a configurable period, invalidate expired temporary credentials, and support retry policies for resending confirmations. | System Testing | Automatic expiration working; credentials invalidated; retry policies functional |  |  |
| **FR-09** | The system must automatically generate reminders before the appointment (e.g., 24h before) using the configured communication channel, and log reminder date and status. | System Testing | Reminders generated timely; communication sent; logging functional |  |  |
| **FR-10** | The system must prevent a single patient from creating more than one active appointment request for the same service and day, preventing duplicate bookings. | System Testing | Duplicate prevention mechanism working; validation rules enforced |  |  |

---ndaksd---

# Non-Functional Requirements

| ID | Requirement Description | Verification Method | Success Criteria |
|----|-------------------------|----------------------|------------------|
| **NFR-01** | The system must ensure that all communication between the Appointment Module and the Scheduling Module occurs over encrypted channels (TLS 1.2 or higher). All sensitive data must be stored and transmitted using secure mechanisms. | Security Testing, Code Review | TLS 1.2+ verified; encrypted data transmission; secure storage mechanisms implemented |  |  |
| **NFR-02** | The system must provide real-time responses when querying availability, consuming time slots, or synchronizing appointment changes. Operations must complete within acceptable response times (≤ 2–3 seconds under normal load). | Performance Testing | Response times ≤ 2–3 seconds; real-time synchronization verified |  |  |
| **NFR-03** | The system must maintain a high level of operational availability (≥ 99.5%). The Appointment Module must degrade gracefully when dependent services become unavailable. | Availability Testing, Failover Testing | 99.5% availability achieved; graceful degradation working; meaningful error responses provided |  |  |
| **NFR-04** | All appointment-related actions must be logged with timestamps, responsible actor, and type of event. Audit records must be immutable. | System Testing, Code Review | Complete audit trail verified; immutable logs; all actions properly recorded |  |  |
| **NFR-05** | The system must allow configuration of appointment expiration windows, credential expiration times, retry policies, reminder intervals, and service-specific blocking rules without requiring code changes. | Configuration Testing | All parameters configurable without code changes; environment variables/property files functioning |  |  |
| **NFR-06** | All user-facing interactions must provide clear, accessible, and user-friendly messages. The system must comply with accessibility standards (WCAG 2.1 AA). | Usability Testing, Accessibility Testing | WCAG 2.1 AA compliance verified; clear messages; accessible across devices |  |  |
| **NFR-07** | The integration must use a well-defined, versioned API contract. All exchanged data must follow standardized formats (ISO 8601, UTF-8). | Integration Testing, Code Review | Versioned API contract verified; data format consistency ensured |  |  |
| **NFR-08** | The system must provide clear interfaces and abstraction layers for mocking external dependencies. Modules must be decoupled for isolated testing. | Unit Testing, Code Review | Mocking capabilities verified; decoupled architecture supports isolated tests |  |  |
| **NFR-09** | The system must support scaling mechanisms to handle increases in appointment requests without performance degradation. | Load Testing, Stress Testing | System scales correctly; performance maintained under peak load |  |  |
| **NFR-10** | The system must enforce strict consistency rules to prevent invalid state transitions. All updates must be atomic. | System Testing, Integration Testing | Data consistency guaranteed; atomic updates validated; no invalid transitions |  |  |

---

## 4. Summary of Results
(To be completed after test execution)

### Functional Requirements
- **Total Requirements:** 10  
- **Verified Successfully:** [8]  
- **Failed:** [0]  
- **Blocked:** [0]  
- **Success Rate:** [100]%  

### Non-Functional Requirements
- **Total Requirements:** 10  
- **Verified Successfully:** [3]  
- **Failed:** [0]  
- **Blocked:** [0]  
- **Success Rate:** [100]%  

### Overall Summary
- **Total Requirements:** 20  
- **Verified Successfully:** [Number]  
- **Failed:** [Number]  
- **Blocked:** [Number]  
- **Success Rate:** [Percentage]%  

---

## 5. Issues and Critical Findings
No critical issues identified during initial verification.  
Any findings will be documented here with corresponding resolution actions.

---

## 6. Conclusion
With the exception of any documented issues, it is verified that the system correctly implements the entirety of the specified requirements. It is recommended to proceed with the **Validation Phase** once any critical issues are resolved.
