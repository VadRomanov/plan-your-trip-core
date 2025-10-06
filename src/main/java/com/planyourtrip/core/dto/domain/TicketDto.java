package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class TicketDto {
    private long id;
    private long tripId;
    private TicketType type;
    private String departure;
    private String arrival;
    private OffsetDateTime departureTime;
    private OffsetDateTime arrivalTime;
    private String fileUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
