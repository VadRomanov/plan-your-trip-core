package com.planyourtrip.core.util;

import lombok.experimental.UtilityClass;
import org.openpdf.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@UtilityClass
public class PdfUtil {

    public byte[] generatePdfFromHtml(String html) {
        try (var baos = new ByteArrayOutputStream()) {
            var renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(baos);

            return baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("PDF generation error", e);
        }
    }

}
