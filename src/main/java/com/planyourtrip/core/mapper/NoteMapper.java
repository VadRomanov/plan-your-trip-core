package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.NoteDto;
import com.planyourtrip.core.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface NoteMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

    //   @Mapping(target = "trip", ignore = true)
    NoteDto toDto(Note note);

    //   @Mapping(target = "trip", ignore = true)
    Note toEntity(NoteDto dto);

    // @Mapping(target = "trip", ignore = true)
    List<NoteDto> toDtoList(List<Note> notes);
}
