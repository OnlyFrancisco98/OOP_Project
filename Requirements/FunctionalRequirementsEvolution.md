# Functional Requirements — Appointment Request and Management Module

### **FR-01 — Integration with Scheduling Module (Availability Consumption)**
The system must connect to the scheduling module (via API or defined feed) to query and consume available time slots in real time, and use that source as the only valid reference when creating or rescheduling an appointment.

---

### **FR-02 — Automatic Synchronization with the Schedule**
The system must synchronize in real time all changes made (reschedules, cancellations) with the scheduling module, ensuring that time slots are correctly released or blocked as appropriate.

---

### **FR-03 — Appointment State Management**
The system must include a state management mechanism for appointments (`requested`, `confirmed`, `rescheduled`, `cancelled`, `completed`, `no-show`) to facilitate validations.  
For example: an appointment with the status **"completed"** cannot be rescheduled.

---

### **FR-04 — Create Appointment Request (Registration of Request)**
The system must allow users to create an appointment request that generates a **unique identifier**.  
The record should include:
- Patient ID  
- Service (type of consultation)  
- Selected time slot (date/time)  
- Professional (if applicable)  
- Appointment status  

---

### **FR-05 — Email Confirmation with Temporary Credentials**
Once an appointment is confirmed, the system must generate a **temporary credential** (token or temporary password with expiration) linked to the appointment and send an email confirmation to the patient containing the appointment details and the temporary credential.

---

### **FR-06 — Rescheduling While Preserving History**
The system must allow users to reschedule an existing appointment by selecting a new slot from the scheduling module, update the status to **rescheduled**, and maintain an immutable history of changes (dates, actor, reason).

---

### **FR-07 — Cancellation / Deletion by User**
The system must allow the patient (or an authorized actor) to cancel or delete their appointment through an operation that updates the status to **cancelled** and records the action in the history log with reason and timestamp.

---

### **FR-08 — Automatic Expiration and Retry Policies**
The system must automatically expire unconfirmed appointment requests after a configurable period, invalidate expired temporary credentials, and support retry policies for resending confirmations (with defined limits).

---

### **FR-09 — Automatic Reminder Generation**
The system must automatically generate reminders before the appointment (e.g., 24 hours prior), using the configured communication channel (email, SMS, or web notification), and log the date and reminder status.

---

### **FR-10 — Control of Concurrent Patient Sessions**
The system must prevent a single patient from creating more than one active appointment request for the same service and day, thereby avoiding duplicate bookings.
