package com.projects.csvconverter;

import java.io.InputStream;

public class TestUtils {

    public static InputStream readResource(String resourcePath) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        return classloader.getResourceAsStream(resourcePath);
    }
}
