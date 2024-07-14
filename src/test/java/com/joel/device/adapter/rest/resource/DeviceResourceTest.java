package com.joel.device.adapter.rest.resource;

import com.joel.device.domain.usecase.device.model.Device;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class DeviceResourceTest extends AbstractDeviceAppResourceTest {

    @Test
    public void createDeviceSuccess() throws Exception {
        var deviceJson = """
                {
                  "name": "string",
                  "brand": "string",
                  "createdAt": "2024-01-30T23:52:31.128361"
                }
                """;
        when(createDevice.execute(any())).thenReturn(
                new Device(
                        1L,
                        "string",
                        "string",
                        LocalDateTime.now()
                )
        );
        var mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/device")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(deviceJson))
                .andReturn();
        var status = mvcResult.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);
    }

    @Test
    void findByIdSuccess() throws Exception {
        when(findDeviceById.query(any())).thenReturn(
                Optional.of(new Device(
                        1L,
                        "string",
                        "string",
                        LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/device/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }

    @Test
    void findByAllSuccess() throws Exception {
        when(findAllDevices.query()).thenReturn(
                List.of(new Device(
                                1L,
                                "string",
                                "string",
                                LocalDateTime.now()
                        )
                )
        );
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/device/list")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();
        assertEquals(HttpStatus.OK.value(), status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();
        assertNotNull(content);
    }
}