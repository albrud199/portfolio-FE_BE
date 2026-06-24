# Human System (Pre-LLM) Analysis

In this phase, we analyze the human-written (Pre-LLM) code for the target subsystems in both `ThingsBoard` and `Mall`.

## 1. Analyzed Subsystems
- **ThingsBoard**: `DeviceController` and `DeviceService` (Device Management subsystem)
- **Mall**: `PmsBrandController` and `PmsBrandService` (Brand Management subsystem)

## 2. Identified Code Smells

### ThingsBoard (`DeviceController.java`)
- **God Module**: `DeviceController` is acting as a God Module. Instead of being a thin API layer, it mixes API request handling with access control (`accessControlService.checkPermission`), auditing (`logEntityAction`), and orchestration across 7 different services (`deviceService`, `actorService`, `deviceStateService`, `claimDevicesService`, `customerService`, `deviceCredentialsService`).
- **Mixed Responsibilities**: Business logic and system auditing are deeply coupled within the controller endpoints.
- **Duplicated Logic**: Almost every endpoint (10 instances) repeats an identical `try-catch` block structure that logs the action (e.g., `logEntityAction(emptyId(EntityType.DEVICE), null, null, ActionType.XXX, e, strDeviceId)`) and then throws a wrapped exception (`throw handleException(e)`).

### Mall (`PmsBrandController.java`)
- **Duplicated Logic**: The controller repeats identical procedural checks for the database update result count across 6 different endpoints (`create`, `update`, `delete`, `deleteBatch`, `updateShowStatus`, `updateFactoryStatus`).
  ```java
  if (count == 1) { // or > 0
      commonResult = CommonResult.success(count);
  } else {
      commonResult = CommonResult.failed();
  }
  ```

## 3. Human System (H) Metrics

We calculated the baseline metrics across the extracted human-written components:

- **Total Modules $|M|$**: 4 (2 Controllers, 2 Services)
- **Total Code Smells $|S|$**: 4 (God Module, Mixed Responsibilities, 2 instances of structural Duplicated Logic)
- **God Modules $|M_g|$**: 1 (`DeviceController` in ThingsBoard)
- **Duplicated instances $\Sigma D$**: 16 (10 in ThingsBoard, 6 in Mall)

These baseline metrics will be compared against the LLM-generated iterations.
