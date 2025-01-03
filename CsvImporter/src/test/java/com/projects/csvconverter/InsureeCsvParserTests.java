package com.projects.csvconverter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class InsureeCsvParserTests {

    @Test
    public void readsExample() {
        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();

        try (InputStream is = TestUtils.readResource("example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(is)
        ) {
            Map<String, List<Insuree>> results = insureeCsvParser.parseWithStream(inputStreamReader);

            Assertions.assertNotNull(results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void returnsCorrectNumberOfInsuranceCompanies() {
        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();

        try (InputStream is = TestUtils.readResource("example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(is)
        ) {
            Map<String, List<Insuree>> results = insureeCsvParser.parseWithStream(inputStreamReader);

            Assertions.assertEquals(9, results.keySet().size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void returnsBiggestVersionWhenSameUserIdAndInsuranceCompany() {
        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();

        try (InputStream is = TestUtils.readResource("same-id-example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(is)
        ) {
            Map<String, List<Insuree>> results = insureeCsvParser.parseWithStream(inputStreamReader);

            Assertions.assertEquals(1,results.keySet().size());
            Assertions.assertEquals(1, results.values().size());

            var insureeList = results.values().stream().toList().get(0);

            Assertions.assertEquals(1, insureeList.size());
            Assertions.assertEquals(2, insureeList.get(0).version());
            Assertions.assertEquals("Patricia", insureeList.get(0).firstName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void returnsSortedByLastWhenUnsortedInCsv() {
        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();

        try (InputStream is = TestUtils.readResource("unsorted-diff-lastname-example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(is)
        ) {
            Map<String, List<Insuree>> results = insureeCsvParser.parseWithStream(inputStreamReader);

            Assertions.assertEquals(1,results.keySet().size());
            Assertions.assertEquals(1, results.values().size());

            var insureeList = results.values().stream().toList().get(0);

            Assertions.assertEquals(2, insureeList.size());
            Assertions.assertEquals("Anderson", insureeList.get(0).lastName());
            Assertions.assertEquals("Thomas", insureeList.get(1).lastName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void returnsSortedByFirstWhenUnsortedInCsv() {
        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();

        try (InputStream is = TestUtils.readResource("unsorted-same-lastname-example.csv");
             InputStreamReader inputStreamReader = new InputStreamReader(is)
        ) {
            Map<String, List<Insuree>> results = insureeCsvParser.parseWithStream(inputStreamReader);

            Assertions.assertEquals(1,results.keySet().size());
            Assertions.assertEquals(1, results.values().size());

            var insureeList = results.values().stream().toList().get(0);

            Assertions.assertEquals(2, insureeList.size());
            Assertions.assertEquals("James", insureeList.get(0).firstName());
            Assertions.assertEquals("Patricia", insureeList.get(1).firstName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
