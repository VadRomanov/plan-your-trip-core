package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.domain.TripDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.TripMapper;
import com.planyourtrip.core.mapper.UserMapper;
import com.planyourtrip.core.repository.TripRepository;
import com.planyourtrip.core.service.TripService;
import com.planyourtrip.core.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {
    private static final String TABLE_NAME = "trips";

    private final TripRepository tripRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final TripMapper tripMapper;

    @Override
    public TripDto createTrip(TripDto dto) {
        var trip = tripMapper.toEntity(setExpiredIfNeeded(dto));
        var savedTrip = tripRepository.save(trip);

        return tripMapper.toDto(savedTrip);
    }

    @Override
    public TripDto updateTrip(TripDto dto) {
        var trip = tripMapper.toEntity(setExpiredIfNeeded(dto));
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
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());

        return setExpiredIfNeeded(tripMapper.toDto(trip));
    }

    @Override
    public Collection<TripDto> getTripsByUser(Long telegramUserId) {
        var userDto = userService.getByTelegramId(telegramUserId);
        var trips = tripRepository.findByUsers(userMapper.toEntity(userDto));

        return tripMapper.toDtos(trips)
                .stream()
                .map(this::setExpiredIfNeeded)
                .toList();
    }

    private TripDto setExpiredIfNeeded(TripDto tripDto) {
        if (nonNull(tripDto.getEndDate())) {
            tripDto.setExpired(tripDto.getEndDate().isBefore(LocalDate.now()));
        }
        return tripDto;
    }
}
