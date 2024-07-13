package com.joel.device.domain.usecase.device.findbybrand;

import com.joel.device.domain.usecase.device.findall.FindAll;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindByBrandImpl implements FindByBrand {

    private final DeviceGateway deviceGateway;


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
