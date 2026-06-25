# Prompt Log

Target comparison: each LLM task mirrors one extracted human subsystem so that H and L are compared at the subsystem-architecture level, not at whole-repository scale.

## Iteration 1: Naive Prompt

### ThingsBoard-style Telemetry Prompt

```text
Generate Java Spring Boot code for a ThingsBoard-style telemetry subsystem. It should support saving telemetry values, reading latest telemetry values, and subscribing or unsubscribing WebSocket clients to telemetry updates.
```

### mall-style Product Prompt

```text
Generate Java Spring Boot code for an e-commerce product catalog admin subsystem. It should support creating products, updating products, publishing products, deleting products, listing products, and managing brands and categories.
```

### Observed Output Flaws

- The telemetry output collapsed REST endpoints, in-memory persistence, WebSocket subscription state, validation, and response construction into `TelemetryApiGodController`.
- The product output similarly collapsed product, brand, category, status updates, storage, ID generation, and validation into `ProductCatalogGodController`.
- Both outputs used generic `Map<String, Object>` payloads instead of typed DTOs or domain models.
- Controllers owned persistence-like state directly, creating cross-layer responsibility mixing.
- The generated code was easy to read at a small scale but structurally unlike the selected human systems, which both use controller/service/domain or mapper boundaries.

Files saved:

- `snapshots/llm/iteration-1/thingsboard-telemetry/TelemetryApiGodController.java`
- `snapshots/llm/iteration-1/mall-product/ProductCatalogGodController.java`

## Iteration 2: Structural Constraints

### ThingsBoard-style Telemetry Prompt

```text
Generate Java Spring Boot code for a ThingsBoard-style telemetry subsystem.
Use separate controller, service, and repository layers.
Keep controller methods thin and put business logic in services.
```

### mall-style Product Prompt

```text
Generate Java Spring Boot code for an e-commerce product catalog admin subsystem.
Use separate controller, service, and repository layers.
Keep controller methods thin and put business logic in services.
```

### Observed Improvements

- The outputs split endpoint handling, business operations, and storage into separate classes.
- Controllers became smaller and delegated work to services.
- Repository-like classes isolated the in-memory storage maps.

### Remaining Flaws

- Services instantiated repositories directly with `new`, so dependencies were hard-coded rather than injected through interfaces.
- The APIs still used `Map<String, Object>` instead of explicit DTOs.
- Repository classes were concrete and storage-specific; there was no abstraction boundary comparable to mapper/service interfaces in the human systems.
- Validation stayed embedded in service methods and was not reusable.

Files saved:

- `snapshots/llm/iteration-2/thingsboard-telemetry/`
- `snapshots/llm/iteration-2/mall-product/`

## Iteration 3: Architectural Constraints

### ThingsBoard-style Telemetry Prompt

```text
Generate Java Spring Boot code for a ThingsBoard-style telemetry subsystem.
Use controller/service/repository layers, typed DTOs, domain models, constructor injection, interface-based service abstraction, and repository interfaces.
Avoid God classes, duplicated validation logic, direct database access from controllers, and mixed responsibilities.
Return code as separate files with package names.
```

### mall-style Product Prompt

```text
Generate Java Spring Boot code for an e-commerce product catalog admin subsystem.
Use controller/service/repository layers, typed DTOs, domain models, constructor injection, service interfaces, repository interfaces, and mapper/converter classes.
Avoid God classes, duplicated validation logic, direct database access from controllers, and mixed responsibilities.
Return code as separate files with package names.
```

### Final Structural Notes

- Final telemetry code separates `TelemetryController`, `TelemetryService`, `SubscriptionService`, repository abstraction, DTOs, and domain objects.
- Final mall code separates `ProductController`, `ProductService`, `ProductRepository`, `ProductMapper`, DTOs, and domain model classes.
- The final code is smaller than the human snapshots because it is generated as a focused architectural slice, not a full production implementation.
- The final LLM code intentionally preserves meaningful layers while avoiding generated persistence boilerplate so the comparison highlights architectural structure rather than framework volume.

Files saved:

- `snapshots/llm/iteration-3/thingsboard-telemetry/`
- `snapshots/llm/iteration-3/mall-product/`
