package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.TripSummaryFileDto;
import com.planyourtrip.core.dto.domain.TripSummaryDto;

public interface TripSummaryService {
    TripSummaryFileDto getTripSummaryPdfById(long id);

    TripSummaryDto getTripSummaryById(long id);

    String getTripSummaryTextById(long id);
}
