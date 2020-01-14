package com.isilona.hrm.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class BaseDto {
    private UUID uuid;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
