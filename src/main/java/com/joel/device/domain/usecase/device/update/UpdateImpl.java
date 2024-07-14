package com.joel.device.domain.usecase.device.update;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.springframework.stereotype.Component;

@Component("com.joel.device.domain.usecase.device.create.UpdateImpl")
public class UpdateImpl implements Update {

    private final DeviceGateway deviceGateway;

    public UpdateImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Device execute(Device device) {
        var domainDeviceRequest = new com.joel.device.domain.model.Device(
                device.id(),
                device.name(),
                device.brand(),
                device.createdAt()
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
