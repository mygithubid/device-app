package com.joel.device.infrastructure.repository;

import com.joel.device.domain.model.Device;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class DeviceJPAGateway implements DeviceGateway {

    private final DeviceJPARepository deviceJPARepository;

    @Override
    public Optional<Device> findById(Long id) {
        var device = deviceJPARepository.findById(id);
        return device.map(this::toDevice);
    }

    @Override
    public Device save(Device device) {
        var entity = com.joel.device.infrastructure.entity.Device.builder()
                .name(device.name())
                .brand(device.brand())
                .createdAt(device.createdAt())
                .build();
        var persisted = deviceJPARepository.save(entity);
        return toDevice(persisted);
    }

    @Override
    public List<Device> findAll() {
        var devices = deviceJPARepository.findAll();
        return devices.stream().map(this::toDevice).toList();
    }

    private Device toDevice(final com.joel.device.infrastructure.entity.Device entity) {
        return new Device(
                entity.getId(),
                entity.getName(),
                entity.getBrand(),
                entity.getCreatedAt()
        );
    }
}
