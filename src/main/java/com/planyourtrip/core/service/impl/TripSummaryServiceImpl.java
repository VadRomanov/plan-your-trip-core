package com.planyourtrip.core.service.impl;

import com.planyourtrip.core.dto.TripSummaryDto;
import com.planyourtrip.core.exception.BusinessException;
import com.planyourtrip.core.exception.ResponseCode;
import com.planyourtrip.core.mapper.TripMapper;
import com.planyourtrip.core.repository.TripRepository;
import com.planyourtrip.core.service.TripSummaryService;
import com.planyourtrip.core.util.PdfUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TripSummaryServiceImpl implements TripSummaryService {
    private static final String TABLE_NAME = "trips";
    private static final String TEMPLATE_NAME = "trip-summary";
    private static final String TRIP_PLACEHOLDER = "trip";
    private static final String HOTELS_PLACEHOLDER = "hotels";
    private static final String TICKETS_PLACEHOLDER = "tickets";
    private static final String NOTES_PLACEHOLDER = "notes";

    private final TemplateEngine templateEngine;
    private final TripRepository tripRepository;
    private final TripMapper tripMapper;

    @Override
    public byte[] getTripSummaryPdfById(long id) {
        var tripSummary = getTripSummaryDto(id);
        return createTripSummaryPdf(tripSummary);
    }

    private TripSummaryDto getTripSummaryDto(long id) {
        var trip = tripRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder(ResponseCode.ENTITY_NOT_FOUND)
                        .params(List.of(TABLE_NAME, id))
                        .build());
        return tripMapper.toSummaryDto(trip);
    }

    private byte[] createTripSummaryPdf(TripSummaryDto tripSummary) {
        var context = new Context();
        context.setVariable(TRIP_PLACEHOLDER, tripSummary);
        context.setVariable(HOTELS_PLACEHOLDER, tripSummary.getHotels());
        context.setVariable(TICKETS_PLACEHOLDER, tripSummary.getTickets());
        context.setVariable(NOTES_PLACEHOLDER, tripSummary.getNotes());

        var html = templateEngine.process(TEMPLATE_NAME, context);

        return PdfUtil.generatePdfFromHtml(html);
    }
}
