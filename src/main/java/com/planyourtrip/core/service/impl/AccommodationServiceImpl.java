package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.domain.AccommodationDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.AccommodationMapper;
import com.planyourtrip.core.repository.AccommodationRepository;
import com.planyourtrip.core.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccommodationServiceImpl implements AccommodationService {
    private static final String TABLE_NAME = "accommodations";

    private final AccommodationRepository accommodationRepository;
    private final AccommodationMapper accommodationMapper;

    @Override
    public AccommodationDto createAccommodation(AccommodationDto dto) {
        var accommodation = accommodationMapper.toEntity(dto);
        var savedAccommodation = accommodationRepository.save(accommodation);

        return accommodationMapper.toDto(savedAccommodation);
    }

    @Override
    public AccommodationDto updateAccommodation(AccommodationDto dto) {
        var accommodation = accommodationMapper.toEntity(dto);
        var savedAccommodation = accommodationRepository.save(accommodation);

        return accommodationMapper.toDto(savedAccommodation);
    }

    @Override
    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public AccommodationDto getAccommodationById(Long id) {
        var accommodation = accommodationRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());

        return accommodationMapper.toDto(accommodation);
    }

    @Override
    public Collection<AccommodationDto> getAccommodationsByTripId(Long tripId) {
        var trips = accommodationRepository.findByTripId(tripId);

        return accommodationMapper.toDtos(trips);
    }
}