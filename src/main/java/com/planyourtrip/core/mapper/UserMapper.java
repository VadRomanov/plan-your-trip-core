package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.UserDto;
import com.planyourtrip.core.entity.Trip;
import com.planyourtrip.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    @Mapping(target = "tripIds", expression = "java(toIds(user.getTrips()))")
    UserDto toDto(User user);

    @Mapping(target = "trips", expression = "java(toTrips(dto.getTripIds()))")
    User toEntity(UserDto dto);

    default Set<Long> toIds(Set<Trip> trips) {
        if (isNull(trips)) {
            return Collections.emptySet();
        }
        return trips.stream()
                .map(Trip::getId)
                .collect(Collectors.toSet());
    }

    default Set<Trip> toTrips(Set<Long> tripIds) {
        if (isNull(tripIds)) {
            return Collections.emptySet();
        }
        return tripIds.stream()
                .map(id -> {
                    var trip = new Trip();
                    trip.setId(id);
                    return trip;
                })
                .collect(Collectors.toSet());
    }
}
