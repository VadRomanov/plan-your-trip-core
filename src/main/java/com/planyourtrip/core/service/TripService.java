package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TripDto;

import java.util.List;

public interface TripService {
    TripDto createTrip(TripDto tripDto);

    TripDto updateTrip(TripDto tripDto);

    void deleteTrip(Long id);

    TripDto getTripById(Long id);

    List<TripDto> getTripsByUser(Long telegramUserId);
}
