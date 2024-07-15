package com.joel.device.domain.usecase.device.delete;

import com.joel.device.domain.usecase.device.create.CreateImpl;
import com.joel.device.domain.usecase.device.gateway.DeviceGateway;
import com.joel.device.domain.usecase.device.model.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class DeleteImplTest {

    DeviceGateway deviceGateway;
    DeleteImpl deleteImpl;

    @BeforeEach
    void setUp() {
        deviceGateway = mock(DeviceGateway.class);
        deleteImpl = new DeleteImpl(deviceGateway);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void execute() {
        deleteImpl.delete(1L);

        verify(deviceGateway).delete(any());
    }
}