package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.UserDto;
import com.planyourtrip.core.mapper.UserMapper;
import com.planyourtrip.core.repository.UserRepository;
import com.planyourtrip.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto saveOrUpdate(UserDto dto) {
        var user = userMapper.toEntity(dto);
        var savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto getByTelegramId(Long telegramId) {
        return userMapper.toDto(userRepository.findByTelegramId(telegramId)
                .orElseThrow(() -> new NoSuchElementException("User not found")));
    }

    @Override
    public void deleteByTelegramId(Long telegramId) {
        userRepository.deleteByTelegramId(telegramId);
    }
}

