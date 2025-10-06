package com.planyourtrip.core.mapper;


import com.planyourtrip.core.dto.domain.TripDto;
import com.planyourtrip.core.dto.domain.TripSummaryDto;
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
public interface TripMapper extends BaseMapper {

    @Mapping(target = "ticketIds", source = "tickets", qualifiedByName = "toIds")
    @Mapping(target = "accommodationIds", source = "accommodations", qualifiedByName = "toIds")
    @Mapping(target = "noteIds", source = "notes", qualifiedByName = "toIds")
    @Mapping(target = "userIds", source = "users", qualifiedByName = "toIds")
    TripDto toDto(Trip trip);

    @Mapping(target = "users", source = "userIds", qualifiedByName = "toUsers")
    Trip toEntity(TripDto dto);

    @Mapping(target = "ticketIds", source = "tickets", qualifiedByName = "toIds")
    @Mapping(target = "accommodationIds", source = "accommodations", qualifiedByName = "toIds")
    @Mapping(target = "noteIds", source = "notes", qualifiedByName = "toIds")
    @Mapping(target = "userIds", source = "users", qualifiedByName = "toIds")
    Set<TripDto> toDtos(Set<Trip> trips);

   // @Mapping(target = "tickets.tripId", source = "trip.id")
   // @Mapping(target = "accommodations.tripId", source = "trip.id")
   // @Mapping(target = "notes.tripId", source = "trip.id")
   // @Mapping(target = "users.tripId", source = "trip.id")
    //todo: tripId in subentities is always 0l
    TripSummaryDto toSummaryDto(Trip trip);

    @Named("toUsers")
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
