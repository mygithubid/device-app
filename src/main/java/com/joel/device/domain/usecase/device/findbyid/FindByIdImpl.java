package com.joel.device.domain.usecase.device.findbyid;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("com.joel.device.domain.usecase.device.findbyid.FindByIdImpl")
public class FindByIdImpl implements FindById {

    private final DeviceGateway deviceGateway;

    public FindByIdImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }

    @Override
    public Optional<Device> query(Long id) {
        var domain = deviceGateway.findById(id);
        if (domain.isEmpty()) {
            return Optional.empty();
        }

        var device = domain.get();
        return Optional.of(new Device(
                device.id(),
                device.name(),
                device.brand(),
                device.createdAt()
        ));
    }
}
