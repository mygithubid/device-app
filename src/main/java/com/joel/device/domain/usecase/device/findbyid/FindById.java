package com.joel.device.domain.usecase.device.findbyid;

import com.joel.device.domain.usecase.device.model.Device;

import java.util.Optional;

public interface FindById {

    Optional<Device> query(Long id);
}
