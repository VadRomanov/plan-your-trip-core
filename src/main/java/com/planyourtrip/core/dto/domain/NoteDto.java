package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;

@Data
@Accessors(chain = true)
public class NoteDto {
    private long id;
    private long tripId;
    private String content;
    private String title;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
