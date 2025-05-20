package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.HotelDto;

import java.util.Collection;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotel(HotelDto hotelDto);

    void deleteHotel(Long id);

    HotelDto getHotelById(Long id);

    Collection<HotelDto> getHotelsByTripId(Long tripId);
}
