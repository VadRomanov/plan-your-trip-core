package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.NoteDto;

import java.util.List;

public interface NoteService {
    NoteDto createNote(NoteDto dto);

    NoteDto updateNote(NoteDto dto);

    void deleteNote(Long id);

    NoteDto getNoteById(Long id);

    List<NoteDto> getNotesByTripId(Long tripId);
}
