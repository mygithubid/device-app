package com.joel.device.domain.usecase.device.findall;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllImpl implements FindAll {

    private final DeviceGateway deviceGateway;

    public FindAllImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }


    @Override
    public List<Device> query() {
        var domainDevices = deviceGateway.findAll();
        return domainDevices.stream()
                    .map(this::toDevice)
                    .toList();
    }

    private Device toDevice(final com.joel.device.domain.model.Device domainDevice) {
        return new Device(
                domainDevice.id(),
                domainDevice.name(),
                domainDevice.brand(),
                domainDevice.createdAt()
        );

    }
}
