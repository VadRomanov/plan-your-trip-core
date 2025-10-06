package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.domain.AccommodationDto;
import com.planyourtrip.core.entity.Accommodation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface AccommodationMapper extends BaseMapper {

    @Mapping(target = "tripId", source = "accommodation.trip.id")
    AccommodationDto toDto(Accommodation accommodation);

    @Mapping(target = "trip", source = "tripId", qualifiedByName = "toTrip")
    Accommodation toEntity(AccommodationDto dto);

    Set<AccommodationDto> toDtos(Set<Accommodation> accommodations);
}
