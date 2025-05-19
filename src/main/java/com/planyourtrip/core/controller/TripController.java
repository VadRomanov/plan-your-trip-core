package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.TripDto;
import com.planyourtrip.core.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
@Tag(name = "Trips", description = "API для управления поездками")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @Operation(summary = "Получить все поездки пользователя по Telegram ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список поездок успешно получен")
    })
    @GetMapping("/user/{telegramId}")
    public ResponseEntity<List<TripDto>> getTripsByUser(@PathVariable Long telegramId) {
        List<TripDto> trips = tripService.getTripsByUser(telegramId);
        return ResponseEntity.ok(trips);
    }

    @Operation(summary = "Создать новую поездку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поездка успешно создана")
    })
    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody @Validated TripDto tripDto) {
        TripDto created = tripService.createTrip(tripDto);
        return ResponseEntity.ok(created);
    }

    @Operation(summary = "Обновить существующую поездку")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поездка успешно обновлена")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @RequestBody @Validated TripDto tripDto) {
        tripDto.setId(id);
        TripDto updated = tripService.updateTrip(tripDto);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Удалить поездку по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Поездка успешно удалена")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@PathVariable Long id) {
        tripService.deleteTrip(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить поездку по ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Поездка найдена")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }
}