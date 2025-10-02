package com.planyourtrip.core.service;

import com.planyourtrip.core.dto.HotelDto;
import com.planyourtrip.core.dto.NoteDto;
import com.planyourtrip.core.dto.TicketDto;
import com.planyourtrip.core.dto.TripDto;
import lombok.RequiredArgsConstructor;
import org.openpdf.pdf.ITextRenderer;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class PdfService {
    private static final String TEMPLATE_NAME = "trip-summary";
    private static final String TRIP_PLACEHOLDER = "trip";
    private static final String HOTELS_PLACEHOLDER = "hotels";
    private static final String TICKETS_PLACEHOLDER = "tickets";
    private static final String NOTES_PLACEHOLDER = "notes";

    private final TemplateEngine templateEngine;

    public byte[] generatePdf(TripDto trip,
                              Collection<HotelDto> hotels,
                              Collection<TicketDto> tickets,
                              Collection<NoteDto> notes) {
        try (var baos = new ByteArrayOutputStream()) {
            var context = new Context();
            context.setVariable(TRIP_PLACEHOLDER, trip);
            context.setVariable(HOTELS_PLACEHOLDER, hotels);
            context.setVariable(TICKETS_PLACEHOLDER, tickets);
            context.setVariable(NOTES_PLACEHOLDER, notes);

            var html = templateEngine.process(TEMPLATE_NAME, context);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(baos);

            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("PDF generation error", e);
        }
    }
}
