package com.projects.csvconverter;

import org.junit.jupiter.api.Test;

public class MainTests {

    @Test
    public void testExample() throws Exception {
        Main.main(new String[]{"src/test/resources/example.csv", "target"});
    }
}
