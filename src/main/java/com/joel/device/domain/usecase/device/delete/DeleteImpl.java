package com.joel.device.domain.usecase.device.delete;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("com.joel.device.domain.usecase.device.create.DeleteImpl")
public class DeleteImpl implements Delete {

    private final DeviceGateway deviceGateway;

    public DeleteImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public void delete(Long id) {
        deviceGateway.delete(id);
    }
}
