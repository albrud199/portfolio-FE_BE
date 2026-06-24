# Metric Calculation

This document calculates the Code Smell Density (CSD), God Module Ratio (GMR), and Duplication Spread Index (DSI) for both the Human (Pre-LLM) system and the final LLM-Generated system. We focus specifically on the extracted **ThingsBoard Device Management** subsystem for direct comparability.

## 1. Raw Metrics

### Human System (H)
*Data extracted from `DeviceController.java` and `DeviceService.java` in ThingsBoard.*
- Total Modules $|M|$ = 2 (Controller, Service)
- Total Code Smells $|S|$ = 3 (God Module, Mixed Responsibilities, Structural Duplication)
- God Modules $|M_g|$ = 1 (`DeviceController` acts as an orchestrator/god module)
- Duplicated instances $\Sigma D$ = 10 (Identical `try-catch` and audit logging blocks mapped across 10 endpoints)

### LLM System (L)
*Data extracted from the Iteration 3 generated code (`LLMDeviceController.java`, `LLMDeviceManagementService.java`).*
- Total Modules $|M|$ = 2 (Controller, Service)
- Total Code Smells $|S|$ = 0 (Global exception handling used, strict delegation implemented)
- God Modules $|M_g|$ = 0
- Duplicated instances $\Sigma D$ = 0

---

## 2. Calculated Metrics

### Code Smell Density (CSD)
Formula: $CSD = |S| / |M|$
- **Human (H)**: 3 / 2 = **1.50**
- **LLM (L)**: 0 / 2 = **0.00**

### God Module Ratio (GMR)
Formula: $GMR = |M_g| / |M|$
- **Human (H)**: 1 / 2 = **0.50**
- **LLM (L)**: 0 / 2 = **0.00**

### Duplication Spread Index (DSI)
Formula: $DSI = \Sigma D / |M|$
- **Human (H)**: 10 / 2 = **5.00**
- **LLM (L)**: 0 / 2 = **0.00**

## Evidence Summary
The metrics demonstrate a significant reduction in code smells when architectural constraints are explicitly provided to the LLM. The Human system suffered heavily from procedural duplication (`DSI = 5.0`) because cross-cutting concerns (auditing and exception handling) were implemented locally within every endpoint. The LLM system resolved this via `@ControllerAdvice` and strict delegation.
