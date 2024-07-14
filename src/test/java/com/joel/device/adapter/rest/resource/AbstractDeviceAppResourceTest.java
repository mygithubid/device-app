package com.joel.device.adapter.rest.resource;

import com.joel.device.app.Starter;
import com.joel.device.domain.usecase.device.create.Create;
import com.joel.device.domain.usecase.device.findall.FindAll;
import com.joel.device.domain.usecase.device.findbyid.FindById;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = Starter.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public abstract class AbstractDeviceAppResourceTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected FindById findDeviceById;

    @MockBean
    protected FindAll findAllDevices;

    @MockBean
    protected Create createDevice;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
}