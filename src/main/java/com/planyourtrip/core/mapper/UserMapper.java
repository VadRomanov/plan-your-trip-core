package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.domain.UserDto;
import com.planyourtrip.core.entity.Trip;
import com.planyourtrip.core.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Mapper(componentModel = "spring")
@Component
public interface UserMapper extends BaseMapper {

    @Mapping(target = "tripIds", source = "trips", qualifiedByName = "toIds")
    UserDto toDto(User user);

    @Mapping(target = "trips", source = "tripIds", qualifiedByName = "toTrips")
    User toEntity(UserDto dto);

    @Named("toTrips")
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
