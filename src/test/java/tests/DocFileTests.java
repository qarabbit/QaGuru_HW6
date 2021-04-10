package tests;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.readTextFromDocFile;
import static utils.Files.readTextFromDocxFile;

public class DocFileTests {

    @Test
    void docFileContentTest() {
        String fileName = "doc.doc";
        String expectedData = "I am a doc file to test";

        assertThat(readTextFromDocFile(fileName), containsString(expectedData));
    }

    @Test
    void docxFileContentTest() {
        String fileName = "docx.docx";
        String expectedData = "I am a docx file to test";

        assertThat(readTextFromDocxFile(fileName), containsString(expectedData));
    }
}
