package utils;

import com.codeborne.pdftest.PDF;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Files {
    private static final String PATH_TO_FILES = "./src/test/resources/files/";

    // DOC, DOCX FILES
    public static String readTextFromDocFile(String filePath) {
        File file = getFile(filePath);
        try (FileInputStream fileInputStream = new FileInputStream(file);
             HWPFDocument document = new HWPFDocument(fileInputStream)) {
            return document.getDocumentText();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found" + file.getPath(), e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file " + file.getPath(), e);
        }
    }

    public static String readTextFromDocxFile(String filePath) {
        File file = getFile(filePath);
        try (FileInputStream fis = new FileInputStream(file);
             XWPFDocument document = new XWPFDocument(fis);
             XWPFWordExtractor extractor = new XWPFWordExtractor(document)) {
            return extractor.getText();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found" + file.getPath(), e);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file " + file.getPath(), e);
        }
    }

    public static String readTextFromFile(File file) {
        try {
            return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read file " + file.getPath(), e);
        }
    }

    public static String readTextFromFile(String filePath) {
        return readTextFromFile(getFile(filePath));
    }

    // PDF FILES
    public static File getFile(String filePath) {
        return new File(PATH_TO_FILES + filePath);
    }

    public static PDF getPdf(String filePath) {
        File file = getFile(filePath);
        try {
            return new PDF(file);
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read pdf-file " + file.getPath(), e);
        }
    }

    // XLS, XLSX FILES
    public static String getFilePath(File file) {
        return String.valueOf(file);
    }

    public static String readExcelFile(File path) throws IOException {
        if (getFilePath(path).endsWith("xlsx")) {
            String result = "";
            XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(path));
            XSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            for (Row row : myExcelSheet) {
                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            result += cell.getStringCellValue() + "=";
                            break;
                        case NUMERIC:
                            result += "[" + cell.getNumericCellValue() + "]";
                            break;
                        case FORMULA:
                            result += "[" + cell.getCellFormula() + "]";
                            break;
                        default:
                            result += cell.toString();
                            break;
                    }
                }
            }
            myExcelBook.close();
            return result;

        } else if (getFilePath(path).endsWith("xls")) {
            String result = "";
            HSSFWorkbook myExcelBook = new HSSFWorkbook(new FileInputStream(path));
            HSSFSheet myExcelSheet = myExcelBook.getSheetAt(0);
            for (Row row : myExcelSheet) {
                for (Cell cell : row) {
                    CellType cellType = cell.getCellType();
                    switch (cellType) {
                        case STRING:
                            result += cell.getStringCellValue() + "=";
                            break;
                        case NUMERIC:
                            result += "[" + cell.getNumericCellValue() + "]";
                            break;
                        case FORMULA:
                            result += "[" + cell.getCellFormula() + "]";
                            break;
                        case BOOLEAN:
                            result += "[" + cell.getBooleanCellValue() + "]";
                            break;
                        default:
                            result += cell.toString();
                            break;
                    }
                }
            }
            myExcelBook.close();
            return result;

        } else {
            throw new IllegalArgumentException("The specified file is not an Excel file");
        }
    }
}
