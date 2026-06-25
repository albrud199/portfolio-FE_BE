# Repository Selection Evidence

Date collected: 2026-06-24

## Candidate 1: ThingsBoard

Repository: https://github.com/thingsboard/thingsboard

Evidence to record:

| Requirement | Evidence |
| --- | --- |
| Language | Java-majority repository. GitHub page lists Java as the largest language. |
| Real system | Open-source IoT platform for data collection, processing, visualization, and device management. |
| Size / commits | GitHub page shows 28,078 commits on 2026-06-24. |
| Pre-2020 start | Local root commit: `c22bf33defb1982446789d9b4ce6b05a8849dfca`, 2016-12-01, `Initial commit`. |
| 2+ years active | Local latest commit at clone time: `8943d52e3a9f33ccfe02bc136db7077067ded5b4`, 2026-06-23. GitHub page also showed a 2026 release. |
| Required folders | Repository root includes `application`, `dao`, `rule-engine`, `transport`; subsystem should include controller/service packages. |
| Pre-2020 checkout | `41a1c6679fb147f6f2442cb7a4563e37ea8c7073`, 2019-12-27, `Added support entity Label to state name and breadcrumb for dashboard`. |

Suggested subsystem:

- `application/src/main/java/org/thingsboard/server/controller/TelemetryController.java`
- `application/src/main/java/org/thingsboard/server/controller/plugin/TbWebSocketHandler.java`
- `application/src/main/java/org/thingsboard/server/service/telemetry`

## Candidate 2: macrozheng/mall

Repository: https://github.com/macrozheng/mall

Evidence to record:

| Requirement | Evidence |
| --- | --- |
| Language | GitHub API/page identifies repository language as Java. |
| Real system | Spring Boot + MyBatis e-commerce system with admin and portal modules. |
| Size / commits | GitHub page shows 1,083 commits on 2026-06-24. |
| Pre-2020 start | GitHub API reports `created_at: 2018-04-04T01:11:44Z`; local root commit: `42d8ff5fb3ed12ed1b79bc71ca2e7b6c71dc1ecc`, 2018-04-13. |
| 2+ years active | Local latest commit at clone time: `0504e86b1f1b6f1b8aa6a734d37a90fb67346be7`, 2026-05-14. |
| Required folders | Repository includes `mall-admin`, `mall-portal`, `mall-common`, `mall-mbg`, `mall-security`. README documents controller/service-style modules. |
| Pre-2020 checkout | `7e57a2dbdaabb2f82226b9cb3aea31c86ac4362c`, 2019-12-31, `Update LICENSE`. |

Suggested subsystem:

- `mall-admin/src/main/java/com/macro/mall/controller/Pms*.java`
- `mall-admin/src/main/java/com/macro/mall/service/Pms*.java`
- `mall-admin/src/main/java/com/macro/mall/service/impl/Pms*.java`
- `mall-admin/src/main/java/com/macro/mall/dto/Pms*.java`
- `mall-mbg/src/main/java/com/macro/mall/model/Pms*.java`
- `mall-mbg/src/main/java/com/macro/mall/mapper/Pms*.java`

## Screenshot Checklist

Capture and save screenshots under `evidence/screenshots/`:

1. GitHub repo main page showing stars/forks/commit count.
2. GitHub file tree showing module folders.
3. Local terminal output showing selected pre-2020 commit hash and date.
4. Local terminal output showing extracted subsystem structure.
5. LOC/count output used for size evidence.
