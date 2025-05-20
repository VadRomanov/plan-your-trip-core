package com.planyourtrip.core.repository;

import com.planyourtrip.core.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Set<Note> findByTripId(Long tripId);
}
