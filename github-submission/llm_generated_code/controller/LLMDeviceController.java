package org.thingsboard.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.service.LLMDeviceManagementService;

@RestController
@RequestMapping("/api/device")
public class LLMDeviceController {

    @Autowired
    private LLMDeviceManagementService deviceService;

    @GetMapping("/{deviceId}")
    public Device getDeviceById(@PathVariable String deviceId) {
        return deviceService.getDeviceWithAccessControl(deviceId);
    }

    @PostMapping
    public Device saveDevice(@RequestBody Device device) {
        return deviceService.saveDeviceWithAccessControl(device);
    }

    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDevice(@PathVariable String deviceId) {
        deviceService.deleteDeviceWithAccessControl(deviceId);
    }

    @PostMapping("/customer/{customerId}/{deviceId}")
    public Device assignDeviceToCustomer(@PathVariable String customerId, @PathVariable String deviceId) {
        return deviceService.assignDeviceToCustomerWithAccessControl(customerId, deviceId);
    }
}
