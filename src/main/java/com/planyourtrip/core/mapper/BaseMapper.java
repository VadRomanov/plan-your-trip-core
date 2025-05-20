package com.planyourtrip.core.mapper;

import com.planyourtrip.core.entity.Trip;

public interface BaseMapper {
    default Trip toTrip(Long tripId) {
        var trip = new Trip();
        trip.setId(tripId);

        return trip;
    }
}
