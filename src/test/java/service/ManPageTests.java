package service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ManPageTests {
    @Test
    public void testManPageReading() {
        try {
            String manPageContent = ManPage.readManPage();
            assertNotNull(manPageContent, "Man page content should not be null");
            assertFalse(manPageContent.isEmpty(), "Man page content should not be empty");
            assertTrue(manPageContent.contains("NAME"), "Man page should contain 'NAME' section");
            assertTrue(manPageContent.contains("SYNOPSIS"), "Man page should contain 'SYNOPSIS' section");
            assertTrue(manPageContent.contains("DESCRIPTION"), "Man page should contain 'DESCRIPTION' section");
            assertTrue(manPageContent.contains("EXIT STATUS"), "Man page should contain 'EXIT STATUS' section");
        } catch (Exception e) {
            fail("Reading man page should not throw an exception: " + e.getMessage());
        }
    }
}
