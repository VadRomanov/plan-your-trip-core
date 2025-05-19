package com.planyourtrip.core.dto;

import lombok.Data;

@Data
public class TripDto {
    private Long id;
    private String userId;
    private String name;
    private String startDate;
    private String endDate;
    private String languageCode;
}
