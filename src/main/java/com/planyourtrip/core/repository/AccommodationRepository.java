package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    Set<Accommodation> findByTripId(Long tripId);
}
