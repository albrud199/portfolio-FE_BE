# Repository Selection Evidence

This document contains evidence that both selected repositories meet the criteria for the Pre-LLM vs LLM-Generated code analysis:
1. Language: Java/Python/TS
2. Size: >10k LOC or 500 commits
3. Age: Pre-2020 start date, 2+ years active
4. Structure: Contains required folders (controller, service, etc.), Real system

## 1. ThingsBoard (`thingsboard/thingsboard`)

- **GitHub Link**: [https://github.com/thingsboard/thingsboard](https://github.com/thingsboard/thingsboard)
- **Language**: Java
- **Total Commits**: 28,078 (> 500)
- **Lines of Code (Java)**: 93,323 (> 10,000)
- **Start Date**: Dec 1, 2016
- **Pre-2020 Commit Checked Out**: `41a1c6679fb147f6f2442cb7a4563e37ea8c7073` (Dec 27, 2019)
- **Activity**: Highly active, real-world open-source IoT platform.
- **Structure**: Core backend application uses standard Java layered architecture. Subsystem extraction target: `application/src/main/java/org/thingsboard/server/controller`

## 2. Mall (`macrozheng/mall`)

- **GitHub Link**: [https://github.com/macrozheng/mall](https://github.com/macrozheng/mall)
- **Language**: Java
- **Total Commits**: 1,083 (> 500)
- **Lines of Code (Java)**: 64,480 (> 10,000)
- **Start Date**: Apr 13, 2018
- **Pre-2020 Commit Checked Out**: `7e57a2dbdaabb2f82226b9cb3aea31c86ac4362c` (Dec 31, 2019)
- **Activity**: Active, popular open-source e-commerce system.
- **Structure**: Spring Boot-based architecture. Subsystem extraction target: `mall-admin/src/main/java/com/macro/mall/controller`

## Conclusion
Both repositories meet all conditions and have been successfully cloned and reverted to a pre-2020 state locally in `scratch/repo-analysis/`.
