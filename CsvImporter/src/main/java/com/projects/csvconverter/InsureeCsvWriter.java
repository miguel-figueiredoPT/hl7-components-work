package com.projects.csvconverter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class InsureeCsvWriter {
    private final static String[] HEADERS = {"User ID", "First Name", "Last Name", "Version", "Insurance Company"};

    public void writeInsureeMap(Map<String, List<Insuree>> insureesByCompanyMap, Path basePath) throws IOException {
        for (var insuranceCompany : insureesByCompanyMap.keySet()) {
            var filePath = basePath.resolve(insuranceCompany + ".csv").toString();
            System.out.println("Writing CSV file to " + filePath);

            try (var writer = new FileWriter(filePath)) {
                writeInsureeList(insureesByCompanyMap.get(insuranceCompany), writer);
            } catch (WriteCsvException e) {
                System.err.println(insuranceCompany + ": " + e.getMessage());
            }
        }
    }

    private void writeInsureeList(List<Insuree> insureeList, Appendable out) throws WriteCsvException {
        try (CSVPrinter printer = CSVFormat.DEFAULT.builder()
                .setHeader(HEADERS) // Set headers
                .build()
                .print(out)
        ) {
            for (Insuree insuree : insureeList) {
                printer.printRecord(
                        insuree.userId(),
                        insuree.firstName(),
                        insuree.lastName(),
                        insuree.version(),
                        insuree.insuranceCompany()
                );
            }

            printer.flush();
            System.out.println("CSV file written successfully");

        } catch (IOException e) {
            throw new WriteCsvException(e);
        }
    }
}
