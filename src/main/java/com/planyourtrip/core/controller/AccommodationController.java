package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.domain.AccommodationDto;
import com.planyourtrip.core.service.AccommodationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/accommodation")
@Tag(name = "Accommodations", description = "API для управления бронированиями отелей")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @Operation(summary = "Получить список отелей для поездки")
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<Collection<AccommodationDto>> getAccommodationsByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(accommodationService.getAccommodationsByTripId(tripId));
    }

    @Operation(summary = "Создать бронирование отеля")
    @PostMapping
    public ResponseEntity<AccommodationDto> createAccommodation(@RequestBody @Validated AccommodationDto accommodationDto) {
        return ResponseEntity.ok(accommodationService.createAccommodation(accommodationDto));
    }

    @Operation(summary = "Обновить бронирование отеля")
    @PutMapping("/{id}")
    public ResponseEntity<AccommodationDto> updateAccommodation(@PathVariable Long id, @RequestBody @Validated
    AccommodationDto accommodationDto) {
        return ResponseEntity.ok(accommodationService.updateAccommodation(accommodationDto.setId(id)));
    }

    @Operation(summary = "Удалить бронирование отеля")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {
        accommodationService.deleteAccommodation(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Получить информацию об отеле по ID")
    @GetMapping("/{id}")
    public ResponseEntity<AccommodationDto> getAccommodationById(@PathVariable Long id) {
        return ResponseEntity.ok(accommodationService.getAccommodationById(id));
    }
}