package com.planyourtrip.core.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoteDto {
    private Long id;
    private Long tripId;
    private String content;
    private LocalDateTime createdAt;
}
