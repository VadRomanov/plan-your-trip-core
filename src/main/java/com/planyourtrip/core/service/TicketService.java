package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TicketDto;

import java.util.List;

public interface TicketService {
    TicketDto createTicket(TicketDto dto);

    TicketDto updateTicket(TicketDto dto);

    void deleteTicket(Long id);

    TicketDto getTicketById(Long id);

    List<TicketDto> getTicketsByTripId(Long tripId);
}
