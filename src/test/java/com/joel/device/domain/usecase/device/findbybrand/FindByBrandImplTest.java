package com.joel.device.domain.usecase.device.findbybrand;

import com.joel.device.domain.model.Device;
import com.joel.device.domain.usecase.device.findall.FindAllImpl;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByBrandImplTest {

    DeviceGateway deviceGateway;
    FindByBrandImpl findByBrand;

    @BeforeEach
    void setUp() {
        deviceGateway = mock(DeviceGateway.class);
        findByBrand = new FindByBrandImpl(deviceGateway);
    }

    @Test
    void findNoDevices() {
        when(deviceGateway.findAll()).thenReturn(List.of());
        var devices = findByBrand.query("XYZ");
        assertTrue(devices.isEmpty());
    }

    @Test
    void findDevices() {
        var device = new Device(
                1L,
                "name",
                "brand",
                LocalDateTime.now()
        );
        when(deviceGateway.findAll()).thenReturn(List.of(device));

        var devices = findByBrand.query("brand");
        assertFalse(devices.isEmpty());
        assertEquals(device.id(), devices.get(0).id());
        assertEquals(device.name(), devices.get(0).name());
        assertEquals(device.brand(), devices.get(0).brand());
        assertEquals(device.createdAt(), devices.get(0).createdAt());
    }
}