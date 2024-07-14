package com.joel.device.adapter.rest.resource;

import com.joel.device.adapter.rest.resource.model.Device;
import com.joel.device.domain.usecase.device.create.Create;
import com.joel.device.domain.usecase.device.findall.FindAll;
import com.joel.device.domain.usecase.device.findbybrand.FindByBrand;
import com.joel.device.domain.usecase.device.findbyid.FindById;
import com.joel.device.domain.usecase.device.update.Update;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceResource {

    private final FindById findById;
    private final FindAll findAll;
    private final Create create;
    private final Update update;
    private final FindByBrand findByBrand;

    public DeviceResource(FindById findById, FindAll findAll, Create create, Update update, FindByBrand findByBrand) {
        this.findById = findById;
        this.findAll = findAll;
        this.create = create;
        this.update = update;
        this.findByBrand = findByBrand;
    }


    @GetMapping("/{id}")
    public Device getDevice(@PathVariable("id") Long id) {
        var user = findById.query(id);
        if (user.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "device #" + id + " not found"
            );
        }

        return mapToDevice(user.orElseThrow());
    }

    @PostMapping
    public Device createDevice(@RequestBody Device request) {
        var deviceRequest = new com.joel.device.domain.usecase.device.model.Device(
                null,
                request.name(),
                request.brand(),
                null
        );

        var user = create.execute(deviceRequest);
        return mapToDevice(user);
    }

    @PutMapping("/{id}")
    public Device updateDevice(@PathVariable("id") Long id, @RequestBody Device request) {
        var device = findById.query(id);
        if (device.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "device #" + id + " not found"
            );
        } else {
            var deviceRequest = new com.joel.device.domain.usecase.device.model.Device(
                    id,
                    request.name(),
                    request.brand(),
                    device.get().createdAt()
            );
            var updatedDevice = update.execute(deviceRequest);
            return mapToDevice(updatedDevice);
        }
    }

    @GetMapping("/list")
    public List<Device> getDevices() {
        var devices = findAll.query();

        return devices.stream().map(this::mapToDevice).toList();
    }

    @GetMapping("/bybrand/{brand}")
    public List<Device> getDevice(@PathVariable("brand") String brand) {
        var devices = findByBrand.query(brand);
        return devices.stream().map(this::mapToDevice).toList();
    }

    private Device mapToDevice(com.joel.device.domain.usecase.device.model.Device device) {
        return new Device(
                device.id(),
                device.name(),
                device.brand(),
                device.createdAt()
        );
    }
}
