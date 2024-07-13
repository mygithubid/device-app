package com.joel.device.domain.usecase.device.update;

import com.joel.device.domain.usecase.device.create.Create;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component("com.joel.device.domain.usecase.device.create.CreateImpl")
@RequiredArgsConstructor
public class UpdateImpl implements Update {

    private final DeviceGateway deviceGateway;

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
