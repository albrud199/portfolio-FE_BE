package org.thingsboard.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.dao.device.DeviceRepository;
import org.thingsboard.server.service.security.AccessControlService;
import org.thingsboard.server.service.audit.AuditService;
import org.thingsboard.server.common.data.audit.ActionType;
import org.thingsboard.server.service.security.permission.Operation;
import org.thingsboard.server.service.security.permission.Resource;

@Service
public class LLMDeviceManagementService {

    private final DeviceRepository deviceRepository;
    private final AccessControlService accessControlService;
    private final AuditService auditService;

    @Autowired
    public LLMDeviceManagementService(DeviceRepository deviceRepository, 
                                      AccessControlService accessControlService, 
                                      AuditService auditService) {
        this.deviceRepository = deviceRepository;
        this.accessControlService = accessControlService;
        this.auditService = auditService;
    }

    public Device getDeviceWithAccessControl(String deviceId) {
        accessControlService.checkPermission(Resource.DEVICE, Operation.READ, deviceId);
        Device device = deviceRepository.findById(deviceId);
        auditService.logAction(ActionType.READ, device);
        return device;
    }

    public Device saveDeviceWithAccessControl(Device device) {
        accessControlService.checkPermission(Resource.DEVICE, Operation.WRITE, device.getId());
        Device saved = deviceRepository.save(device);
        auditService.logAction(ActionType.UPDATED, saved);
        return saved;
    }

    public void deleteDeviceWithAccessControl(String deviceId) {
        accessControlService.checkPermission(Resource.DEVICE, Operation.DELETE, deviceId);
        Device device = deviceRepository.findById(deviceId);
        deviceRepository.delete(deviceId);
        auditService.logAction(ActionType.DELETED, device);
    }

    public Device assignDeviceToCustomerWithAccessControl(String customerId, String deviceId) {
        accessControlService.checkPermission(Resource.DEVICE, Operation.ASSIGN_TO_CUSTOMER, deviceId);
        Device device = deviceRepository.findById(deviceId);
        device.setCustomerId(customerId);
        Device saved = deviceRepository.save(device);
        auditService.logAction(ActionType.ASSIGNED_TO_CUSTOMER, saved);
        return saved;
    }
}
