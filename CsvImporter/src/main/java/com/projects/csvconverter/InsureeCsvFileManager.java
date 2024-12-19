package com.projects.csvconverter;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import com.projects.csvconverter.exceptions.CsvReadException;
import com.projects.csvconverter.exceptions.CsvWriteException;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class InsureeCsvFileManager implements CsvFileManager<InsureeCsvBean> {
    public List<InsureeCsvBean> readCsvToBeanList(Reader reader) throws CsvReadException {
        try (reader) {
            return new CsvToBeanBuilder<InsureeCsvBean>(reader)
                    .withType(InsureeCsvBean.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            throw new CsvReadException(e);
        }
    }

    public void writeToFile(String fileName, List<InsureeCsvBean> beanList) throws CsvWriteException {
        try (Writer writer = new FileWriter(fileName)){
            StatefulBeanToCsv<InsureeCsvBean> beanToCsv = new StatefulBeanToCsvBuilder<InsureeCsvBean>(writer)
                    .build();
            beanToCsv.write(beanList);
        }  catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException | IOException e) {
            throw new CsvWriteException(e);
        }
    }

    public Map<String, List<InsureeCsvBean>> organizeBeansByKey(List<InsureeCsvBean> list) {
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

    public void writeFiles(Map<String,List<InsureeCsvBean>> organizedBeansByKeyMap) throws CsvWriteException {
        for (var insurer : organizedBeansByKeyMap.keySet()) {
            var fileName = insurer + ".csv";
            var values = organizedBeansByKeyMap.get(insurer);

            writeToFile(fileName, values);
        }
    }
}

