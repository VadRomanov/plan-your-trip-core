package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TicketDto;

import java.util.Collection;

public interface TicketService {
    TicketDto createTicket(TicketDto dto);

    TicketDto updateTicket(TicketDto dto);

    void deleteTicket(Long id);

    TicketDto getTicketById(Long id);

    Collection<TicketDto> getTicketsByTripId(Long tripId);
}
