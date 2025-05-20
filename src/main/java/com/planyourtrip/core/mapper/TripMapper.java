package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.TripDto;
import com.planyourtrip.core.entity.BaseEntity;
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
public interface TripMapper {

    @Mapping(target = "ticketIds", expression = "java(toIds(trip.getTickets()))")
    @Mapping(target = "hotelIds", expression = "java(toIds(trip.getHotels()))")
    @Mapping(target = "noteIds", expression = "java(toIds(trip.getNotes()))")
    @Mapping(target = "userIds", expression = "java(toIds(trip.getUsers()))")
    TripDto toDto(Trip trip);

    @Mapping(target = "users", expression = "java(toUsers(dto.getUserIds()))")
    Trip toEntity(TripDto dto);

    @Mapping(target = "ticketIds", expression = "java(toIds(trip.getTickets()))")
    @Mapping(target = "hotelIds", expression = "java(toIds(trip.getHotels()))")
    @Mapping(target = "noteIds", expression = "java(toIds(trip.getNotes()))")
    @Mapping(target = "userIds", expression = "java(toIds(trip.getUsers()))")
    Set<TripDto> toDtos(Set<Trip> trips);

    default Set<Long> toIds(Set<? extends BaseEntity> entities) {
        if (isNull(entities)) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

    default Set<User> toUsers(Set<Long> ids) {
        if (isNull(ids)) {
            return Collections.emptySet();
        }
        return ids.stream()
                .map(id -> {
                    var user = new User();
                    user.setId(id);
                    return user;
                })
                .collect(Collectors.toSet());
    }
}
