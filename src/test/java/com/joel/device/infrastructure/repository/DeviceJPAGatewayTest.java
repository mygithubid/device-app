package com.joel.device.infrastructure.repository;

import com.joel.device.infrastructure.entity.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceJPAGatewayTest {

    DeviceJPARepository repository;

    DeviceJPAGateway gateway;

    @BeforeEach
    void setUp() {
        repository = mock(DeviceJPARepository.class);
        gateway = new DeviceJPAGateway(repository);
    }

    @Test
    void findById() {
        var entity = new Device(
                1L,
                "name",
                "brand",
                null);
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        var user = gateway.findById(1L);

        var domain = new com.joel.device.domain.model.Device(
                1L,
                "name",
                "brand",
                null
        );
        assertTrue(user.isPresent());
        assertEquals(domain.id(), user.get().id());
        assertEquals(domain.name(), user.get().name());
        assertEquals(domain.brand(), user.get().brand());
        assertEquals(domain.createdAt(), user.get().createdAt());
    }

    @Test
    void findAll() {
        var entity = new Device(
                1L,
                "name",
                "brand",
                null);
        when(repository.findAll()).thenReturn(List.of(entity));

        var devices = gateway.findAll();

        var domain = new com.joel.device.domain.model.Device(
                1L,
                "name",
                "brand",
                null
        );
        assertFalse(devices.isEmpty());
        assertEquals(domain.id(), devices.get(0).id());
        assertEquals(domain.name(), devices.get(0).name());
        assertEquals(domain.brand(), devices.get(0).brand());
        assertEquals(domain.createdAt(), devices.get(0).createdAt());
    }

    @Test
    void create() {
        var entity = new Device(
                1L,
                "name",
                "brand",
                null);
        when(repository.save(any(Device.class))).thenReturn(entity);

        var device = gateway.save(new com.joel.device.domain.model.Device(
                null,
                "name",
                "brand",
                null
        ));

        var domain = new com.joel.device.domain.model.Device(
                1L,
                "name",
                "brand",
                null
        );

        assertEquals(domain.id(), device.id());
        assertEquals(domain.name(), device.name());
        assertEquals(domain.brand(), device.brand());
        assertEquals(domain.createdAt(), device.createdAt());
    }
}