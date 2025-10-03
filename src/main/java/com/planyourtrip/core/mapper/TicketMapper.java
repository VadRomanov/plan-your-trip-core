package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.domain.TicketDto;
import com.planyourtrip.core.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface TicketMapper extends BaseMapper {

    @Mapping(target = "tripId", source = "ticket.trip.id")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "trip", expression = "java(toTrip(dto.getTripId()))")
    Ticket toEntity(TicketDto dto);

    Set<TicketDto> toDtos(Set<Ticket> tickets);
}
