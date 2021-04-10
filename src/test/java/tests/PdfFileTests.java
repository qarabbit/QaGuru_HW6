package tests;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static utils.Files.getPdf;

public class PdfFileTests {

    @Test
    void pdfFileContentTest() {
        String fileName = "pdf.pdf";
        String expectedData = "I am a pdf file to test";

        assertThat(getPdf(fileName), PDF.containsText(expectedData));
    }
}