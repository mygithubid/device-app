package com.joel.device.infrastructure.repository;

import com.joel.device.domain.model.Device;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DeviceJPAGateway implements DeviceGateway {

    private final DeviceJPARepository deviceJPARepository;

    public DeviceJPAGateway(DeviceJPARepository deviceJPARepository) {
        this.deviceJPARepository = deviceJPARepository;
    }

    @Override
    public Optional<Device> findById(Long id) {
        var device = deviceJPARepository.findById(id);
        return device.map(this::toDevice);
    }

    @Override
    public Device save(Device device) {
        var entity = new com.joel.device.infrastructure.entity.Device(
                device.id(),
                device.name(),
                device.brand(),
                device.createdAt());
        var persisted = deviceJPARepository.save(entity);
        return toDevice(persisted);
    }

    @Override
    public List<Device> findAll() {
        var devices = deviceJPARepository.findAll();
        return devices.stream().map(this::toDevice).toList();
    }

    @Override
    public void delete(Long id) {
        var device = deviceJPARepository.findById(id);
        if (device.isPresent()) {
            deviceJPARepository.delete(device.get());
        }
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
