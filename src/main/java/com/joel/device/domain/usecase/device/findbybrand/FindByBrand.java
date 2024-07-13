package com.joel.device.domain.usecase.device.findbybrand;

import com.joel.device.domain.usecase.device.model.Device;

import java.util.List;

public interface FindByBrand {

    List<Device> query(String brand);
}
