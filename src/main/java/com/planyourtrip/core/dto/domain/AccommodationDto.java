package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class AccommodationDto {
    @NonNull
    private Long id;
    @NonNull
    private Long tripId;
    @NonNull
    private AccommodationType type;
    @NonNull
    private String name;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String address;
    private String fileUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
