package com.planyourtrip.core.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private Long telegramId;
    private String username;
    private String firstName;
    private String lastName;
    private String languageCode;
}
