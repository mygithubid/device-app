package com.joel.device.domain.model;

import java.time.LocalDateTime;

public record Device (
        Long id,
        String name,
        String brand,
        LocalDateTime createdAt
) {
}
