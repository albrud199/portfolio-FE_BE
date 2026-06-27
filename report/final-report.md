# Comparative Structural Analysis Report

## 1. Repository Selection

### ThingsBoard

- Repository: https://github.com/thingsboard/thingsboard
- Evidence: Java-majority IoT platform, 28,078 commits observed on GitHub, root commit from 2016, latest local commit from 2026.
- Selected pre-2020 commit: `41a1c6679fb147f6f2442cb7a4563e37ea8c7073` from 2019-12-27.
- Extracted subsystem: telemetry REST/WebSocket controller and telemetry services in `snapshots/human/thingsboard-telemetry`.

### macrozheng/mall

- Repository: https://github.com/macrozheng/mall
- Evidence: Java e-commerce system, 1,083 commits observed on GitHub, created in 2018, latest local commit from 2026.
- Selected pre-2020 commit: `7e57a2dbdaabb2f82226b9cb3aea31c86ac4362c` from 2019-12-31.
- Extracted subsystem: product/catalog admin subsystem in `snapshots/human/mall-product`.

## 2. Human System Analysis

### Structures Identified

- **ThingsBoard**: Extensive use of deep inheritance and component plugins, separating concerns into `controller`, `plugin`, `cmd`, `exception`, and `sub` packages. However, much logic is centralized in `DefaultTelemetrySubscriptionService`.
- **mall**: Traditional three-tier architecture with `controller`, `service`, and `dao` (MyBatis mappers) layers. Uses `dto` and `model` objects for data transfer, but services like `PmsProductServiceImpl` aggregate many different operations.

### Existing Smells

- **ThingsBoard**: `DefaultTelemetrySubscriptionService` and `TelemetryController` act as God classes handling many different types of WebSocket and REST operations. High complexity and shotgun surgery risks.
- **mall**: `PmsProductServiceImpl` is a massive God module. It mixes diverse responsibilities (brands, categories, attributes) and has extensive duplicated parameter lists (data clumps).

### Human Metrics

| System | M | S | Mg | ΣD | CSD | GMR | DSI |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| ThingsBoard H | 31 | 13 | 3 | 16 | 0.419 | 0.097 | 0.516 |
| mall H | 62 | 12 | 2 | 583 | 0.194 | 0.032 | 9.403 |

## 3. LLM Generation Analysis

### Iteration 1

- Prompt: Naive prompt asking to generate the subsystems without structural guidance.
- Output flaws: Collapsed REST endpoints, in-memory persistence, validation, and response construction into monolithic God controllers (`TelemetryApiGodController` and `ProductCatalogGodController`).

### Iteration 2

- Prompt: Requested separate controller, service, and repository layers, and thin controllers.
- Improvements: Outputs successfully split endpoint handling, business operations, and storage into separate classes.
- Remaining flaws: Services instantiated repositories directly (hard-coded dependencies). Still lacked typed DTOs and used concrete repository classes instead of interfaces.

### Iteration 3

- Prompt: Enforced full architectural constraints: controller/service/repository layers, typed DTOs, domain models, constructor injection, and interface-based abstraction.
- Final structure: Clean separation of concerns with distinct `controller`, `service`, `repository`, `dto`, and `model` packages. Used constructor injection and interface abstractions.

## 4. LLM Metrics

| System | M | S | Mg | ΣD | CSD | GMR | DSI |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| ThingsBoard L | 12 | 0 | 0 | 8 | 0.000 | 0.000 | 0.667 |
| mall L | 11 | 0 | 0 | 4 | 0.000 | 0.000 | 0.364 |

## 5. Comparative Interpretation

- CSD changed from ~0.419/0.194 to 0.000 because: The LLM was explicitly prompted to avoid God classes and mixed responsibilities, resulting in highly cohesive, focused classes.
- GMR changed from 0.097/0.032 to 0.000 because: The LLM successfully distributed logic across services and repositories in Iteration 3, entirely avoiding the monolithic pattern.
- DSI changed from 0.516/9.403 to 0.667/0.364 because: The LLM generated concise implementations focusing purely on the core architectural structure without enterprise boilerplate.

Deep structural insight:

- While the LLM can generate architecturally sound code when heavily prompted, its natural tendency is to produce monolithic scripts. The generated code lacks the deep complexity and edge-case handling of the human systems, making the comparison somewhat uneven as the LLM code is essentially a "happy path" skeleton.

## 6. Limitations

- The LLM-generated subsystems are simplified mocks without real database persistence, security, or complex error handling, making them inherently smaller and less prone to smells.
- The metrics tool uses simple heuristics for God modules and code smells, which may not capture subtle architectural flaws or false positives.

## 7. Appendix

- Repo evidence: [repository-selection.md](../evidence/repository-selection.md)
- Prompt log: [prompt-log.md](../prompts/prompt-log.md)
- Human snapshot: [thingsboard-telemetry](../snapshots/human/thingsboard-telemetry) and [mall-product](../snapshots/human/mall-product)
- LLM code: [thingsboard-telemetry](../snapshots/llm/iteration-3/thingsboard-telemetry) and [mall-product](../snapshots/llm/iteration-3/mall-product)
- Metrics evidence: [metrics-template.md](../metrics/metrics-template.md)
