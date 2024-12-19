import com.projects.csvconverter.InsureeCsvFileManager;
import com.projects.csvconverter.InsureeCsvBean;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CsvFileManagerTests {
    private static InputStream readExample() {
        String fileName = "example.csv";
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(fileName);
    }

    @Test
    public void resourceReadsFine() throws IOException {
        try (InputStream is = readExample()) {
            String contents = new String(is.readAllBytes());
            assertNotNull(contents);
        }
    }

    @Test
    public void testDefaultFileImport() {
        InsureeCsvFileManager csvFileManager = new InsureeCsvFileManager();
        try (var reader = new BufferedReader(new InputStreamReader(readExample()))
        ) {
            List<InsureeCsvBean> results = csvFileManager.readCsvToBeanList(reader);

            assertEquals(11, results.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMergingOfKeys() {
        InsureeCsvFileManager csvFileManager = new InsureeCsvFileManager();
        try (var reader = new BufferedReader(new InputStreamReader(readExample()))
        ) {
            List<InsureeCsvBean> results = csvFileManager.readCsvToBeanList(reader);
            var resultMap = csvFileManager.organizeBeansByKey(results);

            assertEquals(9, resultMap.keySet().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testWriteToFile() {
        InsureeCsvFileManager csvFileManager = new InsureeCsvFileManager();
        try (var reader = new BufferedReader(new InputStreamReader(readExample()))
        ) {
            List<InsureeCsvBean> results = csvFileManager.readCsvToBeanList(reader);
            var resultMap = csvFileManager.organizeBeansByKey(results);
            csvFileManager.writeFiles(resultMap);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
