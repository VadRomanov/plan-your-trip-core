package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.UserDto;
import com.planyourtrip.core.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Tag(name = "Users", description = "API для управления пользователями Telegram")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Зарегистрировать или обновить пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь успешно сохранён")
    })
    @PostMapping
    public ResponseEntity<UserDto> saveOrUpdateUser(@RequestBody @Validated UserDto userDto) {
        return ResponseEntity.ok(userService.saveOrUpdate(userDto));
    }

    @Operation(summary = "Получить пользователя по Telegram ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    @GetMapping("/{telegramId}")
    public ResponseEntity<UserDto> getUserByTelegramId(@PathVariable Long telegramId) {
        return ResponseEntity.ok(userService.getByTelegramId(telegramId));
    }

    @Operation(summary = "Удалить пользователя по Telegram ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Пользователь удалён")
    })
    @DeleteMapping("/{telegramId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long telegramId) {
        userService.deleteByTelegramId(telegramId);
        return ResponseEntity.noContent().build();
    }
}
