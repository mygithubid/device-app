package com.joel.device.adapter.rest.resource.model;

import java.time.LocalDateTime;

public record Device(
        Long id,
        String name,
        String brand,
        LocalDateTime createdAt
) {
}
