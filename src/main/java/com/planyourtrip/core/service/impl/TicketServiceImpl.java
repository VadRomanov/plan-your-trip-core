package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.TicketDto;
import com.planyourtrip.core.mapper.TicketMapper;
import com.planyourtrip.core.repository.TicketRepository;
import com.planyourtrip.core.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketDto createTicket(TicketDto dto) {
        var ticket = ticketMapper.toEntity(dto);
        var savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toDto(savedTicket);
    }

    @Override
    public TicketDto updateTicket(TicketDto dto) {
        var ticket = ticketMapper.toEntity(dto);
        var savedTicket = ticketRepository.save(ticket);

        return ticketMapper.toDto(savedTicket);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public TicketDto getTicketById(Long id) {
        var ticket = ticketRepository.findById(id)
                .orElseThrow();

        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDto> getTicketsByTripId(Long tripId) {
        var trips = ticketRepository.findByTripId(tripId);

        return ticketMapper.toDtoList(trips);
    }
}