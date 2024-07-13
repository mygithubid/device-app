package com.joel.device.domain.usecase.device.findall;

import com.joel.device.domain.usecase.device.model.Device;

import java.util.List;

public interface FindAll {

    List<Device> query();
}
