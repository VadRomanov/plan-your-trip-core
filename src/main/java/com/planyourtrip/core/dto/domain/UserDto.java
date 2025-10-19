package com.planyourtrip.core.dto.domain;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

import java.time.OffsetDateTime;
import java.util.Set;

@Data
@Accessors(chain = true)
public class UserDto {
    @NonNull
    private Long id;
    private Long telegramId;
    @NonNull
    private String username;
    private String firstName;
    private String lastName;
    @NonNull
    private String languageCode;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private Set<Long> tripIds;
}
