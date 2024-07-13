package com.joel.device.domain.usecase.device.gateway;

import com.joel.device.domain.model.Device;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface DeviceGateway {

    Optional<Device> findById(Long id);

    Device save(Device device);

    List<Device> findAll();
}
