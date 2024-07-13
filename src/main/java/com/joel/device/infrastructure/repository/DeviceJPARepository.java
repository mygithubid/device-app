package com.joel.device.infrastructure.repository;

import com.joel.device.infrastructure.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceJPARepository extends JpaRepository<Device, Long> {
}
