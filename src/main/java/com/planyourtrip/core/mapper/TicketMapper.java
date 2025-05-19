package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.TicketDto;
import com.planyourtrip.core.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface TicketMapper {
    TicketMapper INSTANCE = Mappers.getMapper(TicketMapper.class);

 //   @Mapping(target = "trip", ignore = true)
    TicketDto toDto(Ticket ticket);

//    @Mapping(target = "trip", ignore = true)
    Ticket toEntity(TicketDto dto);

//    @Mapping(target = "trip", ignore = true)
    List<TicketDto> toDtoList(List<Ticket> tickets);
}
