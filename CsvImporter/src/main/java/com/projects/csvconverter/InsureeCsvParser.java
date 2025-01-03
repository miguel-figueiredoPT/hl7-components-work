package com.projects.csvconverter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class InsureeCsvParser {
    public Map<String, List<Insuree>> parseWithStream(Reader in) throws IOException {
        return CSVFormat.RFC4180.builder()
                .setHeader()
                .setSkipHeaderRecord(true)
                .build()
                .parse(in)
                .stream()
                .map(InsureeCsvParser::parseFromRecord)
                .collect(Collectors.groupingBy(Insuree::insuranceCompany,
                        collectingAndThen(
                            Collectors.toMap(Insuree::userId, Function.identity(), (existing, replacement) ->
                                    existing.version() >= replacement.version() ? existing : replacement
                            ),
                            insureeByIdMap -> new ArrayList<>(insureeByIdMap.values())
                                    .stream()
                                    .sorted(new InsureeNameComparator())
                                    .toList()
                        )
                    )
                );
    }

    private static Insuree parseFromRecord(CSVRecord record) {
        return new Insuree(
                record.get("User ID"),
                record.get("First Name"),
                record.get("Last Name"),
                Integer.parseInt(record.get("Version")),
                record.get("Insurance Company")
        );
    }

    private static class InsureeNameComparator implements Comparator<Insuree> {
        public int compare(Insuree a, Insuree b) {
            var result = a.lastName().compareTo(b.lastName());
            return result != 0 ? result : a.firstName().compareTo(b.firstName());
        }
    }
}

