# Java LLM Structural Analysis Project

Start date: 2026-06-24

This workspace is organized for the comparative analysis assignment:

1. Select and verify two Java GitHub repositories.
2. Checkout each repository at a commit before 2020-01-01.
3. Extract one medium/large subsystem from each human-written system.
4. Analyze smells and architectural structure in the human code.
5. Generate an LLM version through three prompt iterations.
6. Calculate CSD, GMR, and DSI for human and LLM systems.
7. Write the final comparison report with evidence.

## Selected Repositories

| Repo | Domain | Planned subsystem | Local path |
| --- | --- | --- | --- |
| ThingsBoard | IoT platform | Telemetry REST/WebSocket controller and telemetry services | `repos/thingsboard` |
| macrozheng/mall | E-commerce system | Admin product/catalog controller, DTO, service, mapper, and model layer | `repos/mall` |

## Deliverables

| Deliverable | Path |
| --- | --- |
| Repository evidence | `evidence/repository-selection.md` |
| Human snapshots | `snapshots/human/` |
| Prompt log | `prompts/prompt-log.md` |
| LLM generated code | `snapshots/llm/` |
| Metrics | `metrics/metrics-template.md` |
| Final report | `report/final-report.md` |

## Current Snapshot Status

| Repo | Checked-out commit | Commit date | Human snapshot |
| --- | --- | --- | --- |
| ThingsBoard | `41a1c6679fb147f6f2442cb7a4563e37ea8c7073` | 2019-12-27 | `snapshots/human/thingsboard-telemetry` |
| mall | `7e57a2dbdaabb2f82226b9cb3aea31c86ac4362c` | 2019-12-31 | `snapshots/human/mall-product` |

Run the first-pass metric helper:

```powershell
./tools/measure-java.ps1 -Path snapshots\human\thingsboard-telemetry -ExcludeGenerated
./tools/measure-java.ps1 -Path snapshots\human\mall-product -ExcludeGenerated
```
