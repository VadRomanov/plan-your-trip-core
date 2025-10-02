package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TripDto;

import java.util.Collection;

public interface TripService {
    TripDto createTrip(TripDto tripDto);

    TripDto updateTrip(TripDto tripDto);

    void deleteTrip(Long id);

    TripDto getTripById(Long id);

    Collection<TripDto> getTripsByUser(Long telegramUserId);

    byte[] getTripSummary(Long id);
}
