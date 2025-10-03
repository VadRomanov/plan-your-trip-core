package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.domain.UserDto;

public interface UserService {
    UserDto saveOrUpdate(UserDto dto);

    UserDto getByTelegramId(Long telegramId);

    void deleteByTelegramId(Long telegramId);
}
