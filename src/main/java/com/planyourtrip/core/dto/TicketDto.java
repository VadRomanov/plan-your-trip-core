package com.planyourtrip.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class TicketDto {
    private Long id;
    private Long tripId;
    private TicketType type;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String fileUrl;
    private OffsetDateTime createdAt;
}
