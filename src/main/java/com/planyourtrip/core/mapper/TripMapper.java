package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.TripDto;
import com.planyourtrip.core.entity.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

 //   @Mapping(target = "tickets", ignore = true)
 //   @Mapping(target = "hotels", ignore = true)
 //   @Mapping(target = "notes", ignore = true)
    TripDto toDto(Trip trip);

  //  @Mapping(target = "tickets", ignore = true)
  //  @Mapping(target = "hotels", ignore = true)
  //  @Mapping(target = "notes", ignore = true)
    Trip toEntity(TripDto dto);

 //   @Mapping(target = "tickets", ignore = true)
 //   @Mapping(target = "hotels", ignore = true)
 //   @Mapping(target = "notes", ignore = true)
    List<TripDto> toDtoList(List<Trip> trips);
}
