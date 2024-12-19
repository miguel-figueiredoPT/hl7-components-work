package com.projects.csvconverter;

import java.nio.file.Files;
import java.nio.file.Path;


public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java -jar myexe CSV_PATH");
            return;
        }

        Path path = Path.of(args[0]);
        var reader = Files.newBufferedReader(path);

        InsureeCsvFileManager csvFileManager = new InsureeCsvFileManager();

        var results = csvFileManager.readCsvToBeanList(reader);
        var organizedResults = csvFileManager.organizeBeansByKey(results);
        csvFileManager.writeFiles(organizedResults);
    }

}