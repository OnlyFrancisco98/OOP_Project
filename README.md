<<<<<<< HEAD
# Web Module for Appointment Scheduling (Applied for the UADY Psychology Faculty)

## Third Delivery

## Sections

### Table of Contents

#### Root Folder

* [**README.md**](README.md): The main entry point of the project, usually a summary.

#### Artifacts

Contains key artifacts from the analysis phase.

* [**User Stories**](Artifacts/HistoriasDeUsuario.md): Document describing the system's user stories.
* [**Use Case Diagram**](Artifacts/UseCaseDiagram.svg): Visual representation of the use cases and actors.

#### Competencies

Documentation regarding the acquired or required competencies.

* [**Generic Competencies**](Competencies/GenericCompetencies.md): Description of the general skills and competencies developed.
* [**Specific Competencies**](Competencies/SpecificCompetencies.md): Description of the specific skills relevant to the project domain.

#### Process

Documentation related to the management and tracking of the development process.

* [**Contribution Metric**](Process/ContributionMetric.md): Document defining how team contribution is measured.
* [**Process Description**](Process/ProcessDescription.md): Details of the development model, phases, or process used.
* [**Process Management**](Process/ProcessManagement.md): Document on project planning, tracking, and risk management.

#### Product

Documentation focused on the description and value of the final product.

* [**Product Description**](Product/ProductDescription.md): Detailed specifications and features of the final product.
* [**Value Proposition**](Product/ValueProposition.md): Statement of the value the product offers to its users or market.

#### Requirements

Documentation for functional, non-functional requirements, and system modeling.
Design and abstraction models of the system.

* [**Class Diagram**](Requirements/AbstractionProcess/ClassDiagram.svg): Representation of the static structure of the system.
* [**Sequence Diagram**](Requirements/AbstractionProcess/SequenceDiagram.svg): Representation of object interactions over time for a specific use case.
* [**Functional Requirements**](Requirements/FunctionalRequirements.md): List and description of the functions the system must perform.
* [**Non-Functional Requirements**](Requirements/NonFunctional.md): Quality aspects of the system (performance, security, usability, etc.).
* [**Requirements Prioritization**](Requirements/Priorization.md): Document justifying the order of implementation for the requirements.

### Project structure

### Team Members

* José Francisco Montero Uc             [LinkedIn](https://www.linkedin.com/in/francisco-montero-uc-a8a06028a)
* José Heynar Sandoval Tamayo           [LinkedIn](https://www.linkedin.com/in/sandoval-tamayo-jose-heynar-893ba9324)
* Kevin Antonio Canto Díaz              [LinkedIn](https://www.linkedin.com/in/Dev-KevinCanto)
* Hector Adrian Rosado Yama             [LinkedIn](https://www.linkedin.com/in/hector-adrian-rosado-yama)
* Cristofer Aldair Barrera Escalante    [LinkedIn](https://www.linkedin.com/in/barrera-escalante-cristofer-aldair-2376a9324/?trk=opento_sprofile_topcard)

### Resources

* [Rúbrica](#)
* [Video](https://drive.google.com/file/d/1oaKZo8NASnfySeM8NOIvCIkjc4FRVn7y/view?usp=sharing)

---
=======
# Web Module for Appointment Scheduling (Applied for the UADY Psychology Faculty)

## Third Delivery

### Project Folder

* [**Java Application**](modulosolicitudes/): The complete application ready to be checked

### Desing

Contains key parts from the desing phase.

* [**Abstraction Process**](Desing/AbstractionProcess.md): Document describing the followed abstraction process.
* [**Class Diagram**](Desing/ClassDiagram.svg): Visual representation of the Class Diagram.
* [**MVC Use**](Desing/MVC-Use.md): Justification and specifications for the MVC use.
* [**Requirement Verification**](Desing/RequirementVerification(NFR-FR).md): Requirement Verification for Non Functional and Functional requirements.

### Process

Documentation related to the management and tracking of the development process.

* [**Contribution Metric**](Process/ContributionMetric.md): Document defining how team contribution is measured.
* [**Process Description**](Process/ProcessDescription.md): Details of the development model, phases, or process used.
* [**Process Management**](Process/ProcessManagement.md): Document on project planning, tracking, and risk management.

### Product

Documentation focused on the description and value of the final product.

* [**Product Evolution**](Product/ProductEvolution.md): Detailed specifications and features of the final product.

### Requirements

Documentation for functional, non-functional requirements, and system modeling.
Design and abstraction models of the system.

* [**Functional Requirements Evolution**](Requirements/FunctionalRequirementsEvolution.md): List and description of the functions the system must perform.
* [**Non-Functional Requirements Evolution**](Requirements/NonFunctionalRequirementsEvolution.md): Quality aspects of the system (performance, security, usability, etc.).
* [**Requirements Prioritization**](Requirements/Priorization.md): Document justifying the order of implementation for the requirements.
* [**Artifact Refinement**](Requirements/ArtifactRefinement.md): Specifications for the refinement of artifacts.
* [**Use Case Diagram**](Requirements/Artifacts/UseCaseDiagram.svg): Use Case Diagram.
* [**User Stories**](Requirements/Artifacts/UserStories.md): User Stories.

### Reflection

Documentation regarding the reflection from the team

* [**Generic Competencies**](Reflection/GenericCompetencies.md): Description of the general skills and competencies developed.
* [**Specific Competencies**](Reflection/SpecificCompetencies.md): Description of the specific skills relevant to the project domain.
* [**Areas for Improvement**](Reflection/AreasForImprovement.md): Description of the areas of improvement for each team member.

### Project structure

```text
OOP_Project
├─ Desing
│  ├─ AbstractionProcess.md
│  ├─ ClassDiagram.svg
│  ├─ MVC-Use.md
│  └─ RequirementVerification(NFR-FR).md
├─ Process
│  ├─ ContributionMetric.md
│  ├─ ProcessDescription.md
│  └─ ProcessManagement.md
├─ Product
│  └─ ProductEvolution.md
├─ README.md
├─ Reflection
│  ├─ AreasForImprovement.md
│  ├─ GenericCompetencies.md
│  └─ SpecificCompetencies.md
├─ Requirements
│  ├─ ArtifactRefinement.md
│  ├─ Artifacts
│  │  ├─ UseCaseDiagram.svg
│  │  └─ UserStories.md
│  ├─ FunctionalRequirementsEvolution.md
│  └─ NonFunctionalRequirementsEvolution.md
└─ modulosolicitudes
   ├─ pom.xml
   ├─ src
   │  ├─ main
   │  │  ├─ java
   │  │  │  └─ modulopsicologia
   │  │  │     ├─ Main.java
   │  │  │     ├─ config
   │  │  │     │  └─ MailConfig.java
   │  │  │     ├─ controller
   │  │  │     │  ├─ CitaController.java
   │  │  │     │  ├─ HorarioController.java
   │  │  │     │  ├─ ReprogramacionController.java
   │  │  │     │  └─ TestEmailController.java
   │  │  │     ├─ dto
   │  │  │     │  ├─ AgendarCitaRequest.java
   │  │  │     │  ├─ AprobarReprogramacionRequest.java
   │  │  │     │  ├─ BuscarCitaRequest.java
   │  │  │     │  ├─ CitaResponse.java
   │  │  │     │  ├─ HorarioResponse.java
   │  │  │     │  ├─ ReprogramacionRequest.java
   │  │  │     │  ├─ SolicitudResponse.java
   │  │  │     │  └─ TestEmailRequest.java
   │  │  │     ├─ model
   │  │  │     │  ├─ Cita.java
   │  │  │     │  ├─ Horario.java
   │  │  │     │  ├─ Paciente.java
   │  │  │     │  └─ Reprogramacion.java
   │  │  │     ├─ repository
   │  │  │     │  ├─ CitaRepository.java
   │  │  │     │  ├─ HorarioRepository.java
   │  │  │     │  ├─ PacienteRepository.java
   │  │  │     │  └─ ReprogramacionRepository.java
   │  │  │     └─ service
   │  │  │        ├─ CitaService.java
   │  │  │        ├─ EmailService.java
   │  │  │        ├─ HorarioService.java
   │  │  │        ├─ PacienteService.java
   │  │  │        └─ ReprogramacionService.java
   │  │  └─ resources
   │  │     ├─ application-mail.properties
   │  │     ├─ application.properties
   │  │     └─ view
   │  │        ├─ reprogramar.html
   │  │        └─ solicitud.html
   │  └─ test
   │     └─ java
   │        └─ modulopsicologia
   │           └─ service
   │              ├─ CitaServiceTest.java
   │              └─ ReprogramacionServiceTest.java
   └─ target
      ├─ classes
      │  ├─ application-mail.properties
      │  ├─ application.properties
      │  ├─ modulopsicologia
      │  │  ├─ config
      │  │  ├─ controller
      │  │  ├─ dto
      │  │  ├─ model
      │  │  ├─ repository
      │  │  └─ service
      │  └─ view
      │     ├─ reprogramar.html
      │     └─ solicitud.html
      ├─ generated-sources
      │  └─ annotations
      ├─ generated-test-sources
      │  └─ test-annotations
      ├─ maven-archiver
      │  └─ pom.properties
      ├─ maven-status
      │  └─ maven-compiler-plugin
      │     ├─ compile
      │     │  └─ default-compile
      │     │     ├─ createdFiles.lst
      │     │     └─ inputFiles.lst
      │     └─ testCompile
      │        └─ default-testCompile
      │           ├─ createdFiles.lst
      │           └─ inputFiles.lst
      └─ test-classes
         └─ modulopsicologia
            └─ service

```

### Team Members

* José Francisco Montero Uc             [LinkedIn](https://www.linkedin.com/in/francisco-montero-uc-a8a06028a)
* José Heynar Sandoval Tamayo           [LinkedIn](https://www.linkedin.com/in/sandoval-tamayo-jose-heynar-893ba9324)
* Kevin Antonio Canto Díaz              [LinkedIn](https://www.linkedin.com/in/Dev-KevinCanto)
* Hector Adrian Rosado Yama             [LinkedIn](https://www.linkedin.com/in/hector-adrian-rosado-yama)
* Cristofer Aldair Barrera Escalante    [LinkedIn](https://www.linkedin.com/in/barrera-escalante-cristofer-aldair-2376a9324/?trk=opento_sprofile_topcard)

### Resources

* [Rúbrica](#)
* [Video](#)
>>>>>>> a9a50a6567da3f12a39166c8d641872472998e86
