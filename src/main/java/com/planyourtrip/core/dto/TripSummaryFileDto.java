package com.planyourtrip.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TripSummaryFileDto {
    private String fileName;
    private byte[] body;
}
