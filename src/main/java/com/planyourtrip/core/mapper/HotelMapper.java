package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.HotelDto;
import com.planyourtrip.core.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface HotelMapper extends BaseMapper {

    @Mapping(target = "tripId", source = "hotel.trip.id")
    HotelDto toDto(Hotel hotel);

    @Mapping(target = "trip", expression = "java(toTrip(dto.getTripId()))")
    Hotel toEntity(HotelDto dto);

    Set<HotelDto> toDtos(Set<Hotel> hotels);
}
