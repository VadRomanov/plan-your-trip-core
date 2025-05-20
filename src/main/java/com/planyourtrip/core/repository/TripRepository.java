package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Trip;
import com.planyourtrip.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    @Query("SELECT t FROM Trip t JOIN t.users u WHERE u.id = :userId and t.name = :name")
    Set<Trip> findByUserIdAndName(Long userId, String name);


    Set<Trip> findByUsers(User user);
}
