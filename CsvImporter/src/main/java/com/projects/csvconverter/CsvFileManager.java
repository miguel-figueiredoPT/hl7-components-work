package com.projects.csvconverter;

import com.projects.csvconverter.exceptions.CsvReadException;
import com.projects.csvconverter.exceptions.CsvWriteException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public interface CsvFileManager<T> {
    List<T> readCsvToBeanList(Reader reader) throws CsvReadException;

    void writeToFile(String fileName, List<T> beanList) throws CsvWriteException;

    Map<String, List<T>> organizeBeansByKey(List<T> list);

    void writeFiles(Map<String,List<T>> organizedBeansByKeyMap) throws CsvWriteException;
}
