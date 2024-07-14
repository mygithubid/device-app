package com.joel.device.domain.usecase.device.create;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("com.joel.device.domain.usecase.device.create.CreateImpl")
public class CreateImpl implements Create {

    private final DeviceGateway deviceGateway;

    public CreateImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Device execute(Device device) {
        var domainDeviceRequest = new com.joel.device.domain.model.Device(
                null,
                device.name(),
                device.brand(),
                LocalDateTime.now()
        );

        var savedDomain = deviceGateway.save(domainDeviceRequest);
        return new Device(
                savedDomain.id(),
                savedDomain.name(),
                savedDomain.brand(),
                savedDomain.createdAt()
        );
    }
}
