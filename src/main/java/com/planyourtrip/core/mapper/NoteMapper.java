package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.domain.NoteDto;
import com.planyourtrip.core.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface NoteMapper extends BaseMapper {

    @Mapping(target = "tripId", source = "note.trip.id")
    NoteDto toDto(Note note);

    @Mapping(target = "trip", expression = "java(toTrip(dto.getTripId()))")
    Note toEntity(NoteDto dto);

    Set<NoteDto> toDtos(Set<Note> notes);
}
