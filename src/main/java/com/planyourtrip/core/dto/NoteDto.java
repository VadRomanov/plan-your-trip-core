package com.planyourtrip.core.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class NoteDto {
    private Long id;
    private Long tripId;
    private String content;
    private OffsetDateTime createdAt;
}
