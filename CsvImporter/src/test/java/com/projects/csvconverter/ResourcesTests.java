package com.projects.csvconverter;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ResourcesTests {
    @Test
    public void resourceReadsFine() throws IOException {
        try (InputStream is = TestUtils.readResource("example.csv")) {
            String contents = new String(is.readAllBytes());
            assertNotNull(contents);
        }
    }
}
