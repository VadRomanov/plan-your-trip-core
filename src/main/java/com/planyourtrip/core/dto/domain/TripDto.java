package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class TripDto {
    private long id;
    @NonNull
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean expired;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Set<Long> ticketIds;
    private Set<Long> accommodationIds;
    private Set<Long> noteIds;
    @NonNull
    private Set<Long> userIds;
}
