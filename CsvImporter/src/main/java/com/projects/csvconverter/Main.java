package com.projects.csvconverter;

import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: java -jar myexe CSV_PATH EXPORT_PATH_BASE");
            return;
        }

        Path csvPath = Path.of(args[0]);
        if (!csvPath.toFile().exists())
            throw new IllegalArgumentException("First argument must be an existing CSV file - current: " + csvPath.toAbsolutePath());

        Path exportBasePath = Path.of(args[1]);
        if (!exportBasePath.toFile().isDirectory())
            throw new IllegalArgumentException("Second argument must be an existing directory - current: " + exportBasePath.toAbsolutePath());

        InsureeCsvParser insureeCsvParser = new InsureeCsvParser();
        InsureeCsvWriter insureeCsvWriter = new InsureeCsvWriter();

        try (var reader = Files.newBufferedReader(csvPath)) {
            var results = insureeCsvParser.parseWithStream(reader);
            insureeCsvWriter.writeInsureeMap(results, exportBasePath);
        }
    }

}