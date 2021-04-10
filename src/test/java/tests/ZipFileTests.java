package tests;

import net.lingala.zip4j.exception.ZipException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static utils.Files.readTextFromFile;
import static utils.Zip.unzip;

public class ZipFileTests {
    @Test
    void zipWithPasswordTest() throws ZipException {
        String zipFilePath = "./src/test/resources/files/zip.zip";
        String unzipFolderPath = "./src/test/resources/files/unzip";
        String unzipTxtFile = "/unzip/zip.txt";
        String expectedData = "I am a zip txt file to test";

        unzip(zipFilePath, unzipFolderPath, "123");

        assertThat(readTextFromFile(unzipTxtFile), containsString(expectedData));
    }
}
