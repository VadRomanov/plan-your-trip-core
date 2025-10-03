package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TripSummaryFileDto;

public interface TripSummaryService {
    TripSummaryFileDto getTripSummaryPdfById(long id);

    String getTripSummaryTextById(long id);
}
