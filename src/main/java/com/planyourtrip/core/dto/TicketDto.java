package com.planyourtrip.core.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class TicketDto {
    private Long id;
    @NonNull
    private Long tripId;
    private TicketType type;
    private String departure;
    private String arrival;
    private OffsetDateTime departureTime;
    private OffsetDateTime arrivalTime;
    private String fileUrl;
    private OffsetDateTime createdAt;
}
