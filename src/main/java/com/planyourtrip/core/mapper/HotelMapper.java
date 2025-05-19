package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.HotelDto;
import com.planyourtrip.core.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface HotelMapper {
    NoteMapper INSTANCE = Mappers.getMapper(NoteMapper.class);

  //  @Mapping(target = "trip", ignore = true)
    HotelDto toDto(Hotel hotel);

   // @Mapping(target = "trip", ignore = true)
    Hotel toEntity(HotelDto dto);

  //  @Mapping(target = "trip", ignore = true)
    List<HotelDto> toDtoList(List<Hotel> hotels);
}
