package com.projects.csvconverter;

import com.projects.csvconverter.exceptions.CsvReadException;
import com.projects.csvconverter.exceptions.CsvWriteException;

import java.io.Reader;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class InsureeCsvConverter {
    private final CsvFileManager<InsureeCsvBean> csvFileManager;

    public InsureeCsvConverter(CsvFileManager<InsureeCsvBean> csvFileManager) {
        this.csvFileManager = csvFileManager;
    }

    public void run(Reader reader) throws CsvReadException, CsvWriteException {
        var results = csvFileManager.readCsvToBeanList(reader);
        writeFiles(organizeBeans(results));
    }

    public Map<String, List<InsureeCsvBean>> organizeBeans(List<InsureeCsvBean> list) {
        // First group insurees under company and replace same IDs with highest of version
        // After collection use grouping by to set the key entry to insuranceCompanyt
        // and perform additional sorting using collectThenJoin
        return list.stream().collect(
                Collectors.toMap(
                        user -> new AbstractMap.SimpleEntry<>(user.getUserId(), user.getInsuranceCompany()),
                        user -> user,
                        (existing, replacement) -> {
                            return existing.getVersion() > replacement.getVersion()
                                    ? existing : replacement;
                        })
        ).values().stream().collect(
                Collectors.groupingBy(InsureeCsvBean::getInsuranceCompany,
                        collectingAndThen(toList(), enrolees -> {
                            return enrolees.stream().sorted(
                                    new InsureeCsvBeanNameComparator()
                            ).toList();
                        }))
        );
    }

    private void writeFiles(Map<String,List<InsureeCsvBean>> insureesByInsuranceCompanyMap) throws CsvWriteException {
        for (var insurer : insureesByInsuranceCompanyMap.keySet()) {
            var fileName = insurer + ".csv";
            var values = insureesByInsuranceCompanyMap.get(insurer);

            csvFileManager.writeToFile(fileName, values);
        }
    }
}
