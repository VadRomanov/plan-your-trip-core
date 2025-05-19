package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.HotelDto;

import java.util.List;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);

    HotelDto updateHotel(HotelDto hotelDto);

    void deleteHotel(Long id);

    HotelDto getHotelById(Long id);

    List<HotelDto> getHotelsByTripId(Long tripId);
}
