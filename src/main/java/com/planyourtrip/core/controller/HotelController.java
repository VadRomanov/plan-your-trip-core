package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.HotelDto;
import com.planyourtrip.core.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
@Tag(name = "Hotels", description = "API для управления бронированиями отелей")
@RequiredArgsConstructor
public class HotelController {

    private final HotelService hotelService;

    @Operation(summary = "Получить список отелей для поездки")
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<List<HotelDto>> getHotelsByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(hotelService.getHotelsByTripId(tripId));
    }

    @Operation(summary = "Создать бронирование отеля")
    @PostMapping
    public ResponseEntity<HotelDto> createHotel(@RequestBody @Validated HotelDto hotelDto) {
        return ResponseEntity.ok(hotelService.createHotel(hotelDto));
    }

    @Operation(summary = "Обновить бронирование отеля")
    @PutMapping("/{id}")
    public ResponseEntity<HotelDto> updateHotel(@PathVariable Long id, @RequestBody @Validated HotelDto hotelDto) {
        hotelDto.setId(id);
        return ResponseEntity.ok(hotelService.updateHotel(hotelDto));
    }

    @Operation(summary = "Удалить бронирование отеля")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить информацию об отеле по ID")
    @GetMapping("/{id}")
    public ResponseEntity<HotelDto> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelService.getHotelById(id));
    }
}