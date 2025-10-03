package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.domain.NoteDto;

import java.util.Collection;

public interface NoteService {
    NoteDto createNote(NoteDto dto);

    NoteDto updateNote(NoteDto dto);

    void deleteNote(Long id);

    NoteDto getNoteById(Long id);

    Collection<NoteDto> getNotesByTripId(Long tripId);
}
