package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.HotelDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.HotelMapper;
import com.planyourtrip.core.repository.HotelRepository;
import com.planyourtrip.core.service.HotelService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {
    private static final String TABLE_NAME = "hotels";

    private final HotelRepository hotelRepository;
    private final HotelMapper hotelMapper;

    @Override
    public HotelDto createHotel(HotelDto dto) {
        var hotel = hotelMapper.toEntity(dto);
        var savedHotel = hotelRepository.save(hotel);

        return hotelMapper.toDto(savedHotel);
    }

    @Override
    public HotelDto updateHotel(HotelDto dto) {
        var hotel = hotelMapper.toEntity(dto);
        var savedHotel = hotelRepository.save(hotel);

        return hotelMapper.toDto(savedHotel);
    }

    @Override
    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        var hotel = hotelRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());

        return hotelMapper.toDto(hotel);
    }

    @Override
    public Collection<HotelDto> getHotelsByTripId(Long tripId) {
        var trips = hotelRepository.findByTripId(tripId);

        return hotelMapper.toDtos(trips);
    }
}