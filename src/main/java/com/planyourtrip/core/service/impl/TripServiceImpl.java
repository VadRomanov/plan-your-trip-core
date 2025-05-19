package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.TripDto;
import com.planyourtrip.core.mapper.TripMapper;
import com.planyourtrip.core.repository.TripRepository;
import com.planyourtrip.core.repository.UserRepository;
import com.planyourtrip.core.service.TripService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private final TripRepository tripRepository;
    private final UserRepository userRepository;
    private final TripMapper tripMapper;

    @Override
    public TripDto createTrip(TripDto dto) {
        var trip = tripMapper.toEntity(dto);
        var savedTrip = tripRepository.save(trip);

        return tripMapper.toDto(savedTrip);
    }

    @Override
    public TripDto updateTrip(TripDto dto) {
        var trip = tripMapper.toEntity(dto);
        var savedTrip = tripRepository.save(trip);

        return tripMapper.toDto(savedTrip);
    }

    @Override
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }

    @Override
    public TripDto getTripById(Long id) {
        var trip = tripRepository.findById(id)
                .orElseThrow();

        return tripMapper.toDto(trip);
    }

    @Override
    public List<TripDto> getTripsByUser(Long telegramUserId) {
        var user = userRepository.findByTelegramId(telegramUserId)
                .orElseThrow();
        var trips = tripRepository.findByUsers(user);

        return tripMapper.toDtoList(trips);
    }
}
