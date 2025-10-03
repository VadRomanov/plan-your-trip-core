package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.domain.AccommodationDto;

import java.util.Collection;

public interface AccommodationService {
    AccommodationDto createAccommodation(AccommodationDto accommodationDto);

    AccommodationDto updateAccommodation(AccommodationDto accommodationDto);

    void deleteAccommodation(Long id);

    AccommodationDto getAccommodationById(Long id);

    Collection<AccommodationDto> getAccommodationsByTripId(Long tripId);
}
