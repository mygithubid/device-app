package com.joel.device.domain.usecase.device.update;

import com.joel.device.domain.usecase.device.model.Device;

public interface Update {

    Device execute(Device device);
}