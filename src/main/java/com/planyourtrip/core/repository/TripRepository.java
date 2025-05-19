package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Trip;
import com.planyourtrip.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByUsers(User user);
}
