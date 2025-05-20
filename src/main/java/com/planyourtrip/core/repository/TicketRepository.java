package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Set<Ticket> findByTripId(Long tripId);
}
