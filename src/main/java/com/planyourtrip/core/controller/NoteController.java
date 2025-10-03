package com.planyourtrip.core.controller;

import com.planyourtrip.core.dto.domain.NoteDto;
import com.planyourtrip.core.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api/note")
@Tag(name = "Notes", description = "API для заметок в поездке")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @Operation(summary = "Получить все заметки для поездки")
    @GetMapping("/trip/{tripId}")
    public ResponseEntity<Collection<NoteDto>> getNotesByTrip(@PathVariable Long tripId) {
        return ResponseEntity.ok(noteService.getNotesByTripId(tripId));
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(noteService.createNote(noteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable Long id, @RequestBody NoteDto noteDto) {
        return ResponseEntity.ok(noteService.updateNote(noteDto.setId(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDto> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(noteService.getNoteById(id));
    }
}