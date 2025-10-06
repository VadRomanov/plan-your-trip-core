package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class AccommodationDto {
    private long id;
    private long tripId;
    private AccommodationType type;
    private String name;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String address;
    private String fileUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
