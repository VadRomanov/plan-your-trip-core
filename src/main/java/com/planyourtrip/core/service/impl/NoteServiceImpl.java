package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.domain.NoteDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.NoteMapper;
import com.planyourtrip.core.repository.NoteRepository;
import com.planyourtrip.core.service.NoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private static final String TABLE_NAME = "notes";

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Override
    public NoteDto createNote(NoteDto dto) {
        var note = noteMapper.toEntity(dto);
        var savedNote = noteRepository.save(note);

        return noteMapper.toDto(savedNote);
    }

    @Override
    public NoteDto updateNote(NoteDto dto) {
        var note = noteMapper.toEntity(dto);
        var savedNote = noteRepository.save(note);

        return noteMapper.toDto(savedNote);
    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public NoteDto getNoteById(Long id) {
        var note = noteRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());

        return noteMapper.toDto(note);
    }

    @Override
    public Collection<NoteDto> getNotesByTripId(Long tripId) {
        var notes = noteRepository.findByTripId(tripId);

        return noteMapper.toDtos(notes);
    }
}