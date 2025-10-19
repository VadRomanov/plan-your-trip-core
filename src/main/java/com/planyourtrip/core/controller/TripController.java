package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.domain.TripDto;
import com.planyourtrip.core.service.TripService;
import com.planyourtrip.core.service.TripSummaryService;
import com.planyourtrip.core.util.TextUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

import static java.lang.String.format;

@RestController
@RequestMapping("/api/trip")
@Tag(name = "Trips", description = "API для управления поездками")
@RequiredArgsConstructor
public class TripController {

    private final TripSummaryService tripSummaryService;
    private final TripService tripService;

    @Operation(summary = "Получить все поездки пользователя по Telegram ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Список поездок успешно получен")
    })
    @GetMapping("/user/{telegramId}")
    public ResponseEntity<Collection<TripDto>> getTripsByUser(@PathVariable Long telegramId) {
        Collection<TripDto> trips = tripService.getTripsByUser(telegramId);
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
        TripDto updated = tripService.updateTrip(tripDto.setId(id));
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

    @Operation(summary = "Получить краткое содержание поездки по ID в pdf")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Краткое содержание получено")
    })
    @GetMapping(value = "/{id}/summary", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PDF_VALUE})
    public ResponseEntity<?> getTripSummaryPdf(@PathVariable Long id,
                                               @RequestHeader(HttpHeaders.ACCEPT) String acceptHeader) {
        if (acceptHeader.equals(MediaType.APPLICATION_PDF_VALUE)) {
            var tripSummaryFile = tripSummaryService.getTripSummaryPdfById(id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            format("attachment; filename=%s",
                                    TextUtils.encodeBase64String(tripSummaryFile.getFileName())))
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(tripSummaryFile.getBody());
        } else {
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(tripSummaryService.getTripSummaryById(id));
        }
    }
}