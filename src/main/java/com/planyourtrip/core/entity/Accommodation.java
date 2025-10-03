package com.planyourtrip.core.entity;

import com.planyourtrip.core.dto.domain.AccommodationType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "accommodations")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Accommodation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Enumerated(EnumType.STRING)
    private AccommodationType type;
    private String name;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String address;
    private String fileUrl;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
