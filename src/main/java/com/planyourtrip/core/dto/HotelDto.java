package com.planyourtrip.core.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class HotelDto {
    private Long id;
    private Long tripId;
    private String name;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String address;

}
