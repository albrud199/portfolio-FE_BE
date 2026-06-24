# LLM Generation & Iterative Prompting Log

This document tracks the iterative prompting process used to generate a replacement subsystem for the **ThingsBoard Device Management** module.

## Iteration 1: Naive Prompt
**Prompt:**
> "Write a Spring Boot Java backend for an IoT device management system. It should have endpoints to get, save, delete, and assign devices to customers. Include the logic for auditing these actions and checking user permissions."

**Output Flaws Detected:**
- **God Module Created**: The LLM generated a single massive file `DeviceManagementController.java` containing HTTP routing, raw database access logic, permission validation, and auditing.
- **Missing Abstraction**: No Service or Repository interfaces were defined.

## Iteration 2: Structural Constraints
**Prompt:**
> "Write a Spring Boot Java backend for an IoT device management system with endpoints to get, save, delete, and assign devices. You must strictly separate the code into a Controller layer and a Service layer."

**Output Flaws Detected:**
- **Cross-Layer Violations / Mixed Responsibilities**: While the LLM created `DeviceController` and `DeviceService`, the Controller still handled access control checks and explicit exception handling for every endpoint.
- **Duplicated Logic**: The LLM replicated the exact code smell found in the human system—wrapping every single Controller endpoint in a nearly identical `try-catch` block to handle auditing and exceptions.

## Iteration 3: Architectural Constraints
**Prompt:**
> "Write a Spring Boot Java backend for an IoT device management system with Controller, Service, and Repository layers. 
> Constraints:
> 1. Use DTOs for data transfer.
> 2. The Controller must be a 'thin' layer responsible ONLY for HTTP routing. 
> 3. Delegate ALL business logic, access control, and auditing entirely to the Service layer.
> 4. Do not use try-catch blocks in the Controller. Use a global `@ControllerAdvice` for exception handling."

**Improvements:**
- The resulting code is structurally sound.
- Duplicated `try-catch` blocks were entirely eliminated.
- The `DeviceController` is now purely declarative and delegates to `DeviceService`.
- No God Modules.

The final generated code has been saved as a separate artifact.
