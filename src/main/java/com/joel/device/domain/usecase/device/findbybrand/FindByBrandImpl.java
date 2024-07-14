package com.joel.device.domain.usecase.device.findbybrand;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("com.joel.device.domain.usecase.device.findbyid.FindByBrandImpl")
public class FindByBrandImpl implements FindByBrand {

    private final DeviceGateway deviceGateway;

    public FindByBrandImpl(DeviceGateway deviceGateway) {
        this.deviceGateway = deviceGateway;
    }


    @Override
    public List<Device> query(String brand) {
        var domainDevices = deviceGateway.findAll();
        return domainDevices.stream()
                .filter(device -> device.brand().equals(brand))
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
