package com.planyourtrip.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TicketDto {
    private Long id;
    private Long tripId;
    private TicketType type;
    private String departure;
    private String arrival;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private String fileUrl;
}
