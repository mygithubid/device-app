package com.joel.device.domain.usecase.device.create;

import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

class CreateImplTest {

    MockedStatic<LocalDateTime> mockedLocalDateTime;
    DeviceGateway deviceGateway;
    CreateImpl createImpl;

    @BeforeEach
    void setUp() {
        mockedLocalDateTime = mockStatic(LocalDateTime.class);
        deviceGateway = mock(DeviceGateway.class);
        createImpl = new CreateImpl(deviceGateway);
    }

    @AfterEach
    void tearDown() {
        mockedLocalDateTime.close();
    }

    @Test
    void execute() {

        var now = LocalDateTime.now();

        mockedLocalDateTime.when(LocalDateTime::now).thenReturn(now);

        var domain = new com.joel.device.domain.model.Device(
                null,
                "name",
                "brand",
                now
        );

        Mockito.when(deviceGateway.save(domain)).thenAnswer(invocationOnMock -> {
            var domainDevice = invocationOnMock.getArgument(0, com.joel.device.domain.model.Device.class);
            return new com.joel.device.domain.model.Device(
                    1L,
                    "name",
                    "brand",
                    now
            );
        });

        var device = new Device(
                1L,
                "name",
                "brand",
                now
        );

        var savedDevice = createImpl.execute(device);

        assertEquals(device.id(), savedDevice.id());
        assertEquals(device.name(), savedDevice.name());
        assertEquals(device.brand(), savedDevice.brand());
        assertEquals(device.createdAt(), savedDevice.createdAt());
    }
}