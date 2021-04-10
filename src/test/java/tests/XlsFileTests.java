package tests;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.readExcelFile;

public class XlsFileTests {
    @Test
    void xlsFileContentTest() throws IOException {
        File xlsFile = new File("src/test/resources/files/xls.xls");
        String expectedData = "I am a xls file text";
        String actualData = readExcelFile(xlsFile);

        assertThat(actualData, containsString(expectedData));
    }

    @Test
    void xlsxFileContentTest() throws IOException {
        File xlsxFile = new File("src/test/resources/files/xlsx.xlsx");
        String expectedData = "I am a xlsx file text";
        String actualData = readExcelFile(xlsxFile);

        assertThat(actualData, containsString(expectedData));
    }
}
