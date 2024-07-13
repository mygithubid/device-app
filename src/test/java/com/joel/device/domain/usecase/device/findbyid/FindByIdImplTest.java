package com.joel.device.domain.usecase.device.findbyid;

import com.joel.device.domain.model.Device;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FindByIdImplTest {

    DeviceGateway deviceGateway;
    FindById findById;

    @BeforeEach
    void setUp() {
        deviceGateway = mock(DeviceGateway.class);
        findById = new FindByIdImpl(deviceGateway);
    }

    @Test
    void findNoDevice() {
        when(deviceGateway.findById(1L)).thenReturn(Optional.empty());
        var device = findById.query(1L);
        assertTrue(device.isEmpty());
    }

    @Test
    void findDevice() {

        var domain = new Device(
                1L,
                "name",
                "brand",
                null
        );
        when(deviceGateway.findById(1L)).thenReturn(Optional.of(domain));

        var device = findById.query(1L);
        assertTrue(device.isPresent());
        assertEquals(domain.id(), device.get().id());
        assertEquals(domain.name(), device.get().name());
        assertEquals(domain.brand(), device.get().brand());
        assertEquals(domain.createdAt(), device.get().createdAt());
    }

}