package com.projects.csvconverter;

public class WriteCsvException extends Exception {

    public WriteCsvException(Exception innerException) {
        super("Issue writing to file: " + innerException.getMessage(), innerException);
    }
}
