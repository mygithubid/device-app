package com.joel.device.domain.usecase.device.model;

import java.time.LocalDateTime;

public record Device (
        Long id,
        String name,
        String brand,
        LocalDateTime createdAt
) {
}
