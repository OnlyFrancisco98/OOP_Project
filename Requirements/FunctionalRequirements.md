# Restructuring of Functional Requirements

Grouping the requirements based on the expected functionalities of the system, in order to better understand and organize the general flow of the system.

### Initial Request Management

* **RF1: Submit an appointment request.** Users must be able to submit an appointment request at a selected time with their data, in order for the appointment to be scheduled.
* **RD2: Field validation.** The system must be able to validate each field of the form to ensure that the information entered is valid and truthful (e.g., email and phone).

### User Confirmation and Access

* **RF3: Success notification.** The system must issue an immediate notification confirming the successful appointment request.
* **RF4: Confirmation Email.** Accompanied by the confirmation notification, an email must be sent to the previously entered email address with the option to confirm the appointment.
* **RF5: Access code.** After confirmation, an access code will be sent by email, with which the user can log into the system and view their appointment information at any time.

### Viewing and Selecting Available Times

* **RF6: View and select available times.** When registering their appointment, the user must be able to see the available times per day, as well as select the most appropriate one for their request.
* **RF7: Connection to the schedule.** The system must have access to the schedule database to display available times in real time.

### Persistence

* **RF8: Save Information.** The system must be able to save all information related to each generated appointment in the database.
