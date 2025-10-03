package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class NoteDto {
    private Long id;
    @NonNull
    private Long tripId;
    @NonNull
    private String content;
    private String title;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
