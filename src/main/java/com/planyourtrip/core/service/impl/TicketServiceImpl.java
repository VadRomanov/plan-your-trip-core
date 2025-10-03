package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.domain.TicketDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.TicketMapper;
import com.planyourtrip.core.repository.TicketRepository;
import com.planyourtrip.core.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {
    private static final String TABLE_NAME = "tickets";

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
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());

        return ticketMapper.toDto(ticket);
    }

    @Override
    public Collection<TicketDto> getTicketsByTripId(Long tripId) {
        var trips = ticketRepository.findByTripId(tripId);

        return ticketMapper.toDtos(trips);
    }
}