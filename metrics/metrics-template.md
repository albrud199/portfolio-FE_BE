# Metrics

Definitions:

- `|M|`: total modules/classes in the selected subsystem.
- `|S|`: total code smells detected manually or by tool.
- `|Mg|`: number of God modules/classes.
- `ΣD`: duplicated instances counted across modules.
- `CSD = |S| / |M|`
- `GMR = |Mg| / |M|`
- `DSI = ΣD / |M|`

## Human System Metrics

| Repository | Subsystem | Commit | M | S | Mg | ΣD | CSD | GMR | DSI |
| --- | --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| ThingsBoard | Telemetry REST/WebSocket subsystem | `41a1c6679fb147f6f2442cb7a4563e37ea8c7073` | 31 | 13 | 3 | 16 | 0.419 | 0.097 | 0.516 |
| mall | Product/catalog admin subsystem, generated files excluded | `7e57a2dbdaabb2f82226b9cb3aea31c86ac4362c` | 62 | 12 | 2 | 583 | 0.194 | 0.032 | 9.403 |

## LLM System Metrics

| Repository basis | Iteration | M | S | Mg | ΣD | CSD | GMR | DSI |
| --- | --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| ThingsBoard-style feature | 3 | 12 | 0 | 0 | 8 | 0.000 | 0.000 | 0.667 |
| mall-style feature | 3 | 11 | 0 | 0 | 4 | 0.000 | 0.000 | 0.364 |

## Counting Rules

Recommended module definition:

- Count each `.java` class/interface/enum in the extracted subsystem as one module.
- If a file contains multiple top-level types, count each top-level type.

Recommended God module rule:

- Count a class as God module if it has at least one strong signal: very high LOC relative to subsystem, many public methods, mixed controller/service/repository responsibilities, or too many dependencies.

Recommended smell categories:

- Large class / God class
- Long method
- Duplicated logic
- Feature envy / cross-layer access
- Mixed responsibilities
- Data clumps / repeated parameter groups
- Shotgun surgery risk

## Evidence Commands

Run from each checked-out repository:

```powershell
git log --reverse --format="%H %ci %s" -n 5
git log -1 --format="%H %ci %s"
git rev-list --count HEAD
Get-ChildItem -Recurse -Filter *.java <SUBSYSTEM_PATH> | Measure-Object
```

Use `cloc` or `scc` if installed:

```powershell
cloc <SUBSYSTEM_PATH>
```

Current helper command:

```powershell
./tools/measure-java.ps1 -Path snapshots\human\thingsboard-telemetry -ExcludeGenerated
./tools/measure-java.ps1 -Path snapshots\human\mall-product -ExcludeGenerated
```

Mall generated-code sensitivity check:

| Scope | M | S | Mg | ΣD | CSD | GMR | DSI |
| --- | ---: | ---: | ---: | ---: | ---: | ---: | ---: |
| mall product/catalog including generated MyBatis files | 80 | 47 | 20 | 6678 | 0.588 | 0.250 | 83.475 |

Interpretation note: for the main H-vs-L comparison, exclude generated MyBatis `*Example.java` files unless the LLM output also includes equivalent generated persistence artifacts. Otherwise the metric comparison is dominated by generated boilerplate instead of architectural structure.
