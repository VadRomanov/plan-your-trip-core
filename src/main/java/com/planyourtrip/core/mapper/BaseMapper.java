package com.planyourtrip.core.mapper;

import com.planyourtrip.core.entity.BaseEntity;
import com.planyourtrip.core.entity.Trip;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public interface BaseMapper {
    @Named("toTrip")
    default Trip toTrip(Long tripId) {
        var trip = new Trip();
        trip.setId(tripId);

        return trip;
    }

    @Named("toIds")
    default Set<Long> toIds(Set<? extends BaseEntity> entities) {
        if (isNull(entities)) {
            return Collections.emptySet();
        }
        return entities.stream()
                .map(BaseEntity::getId)
                .collect(Collectors.toSet());
    }

}
