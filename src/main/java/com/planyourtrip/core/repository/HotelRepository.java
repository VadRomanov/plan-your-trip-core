package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Set<Hotel> findByTripId(Long tripId);
}
