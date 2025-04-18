package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.group46.components.JSON;
import com.group46.components.parentalController;

public class ParentalControllerTest {

    private parentalController pc;
    private final String parentalFileName = "parental.json";
    private final Path parentalFilePath = Paths.get(JSON.getDatabaseFolder(), parentalFileName);

    @BeforeEach
    public void setup() throws Exception {
        // Optionally delete the file to force a default creation.
        if (Files.exists(parentalFilePath)) {
            Files.delete(parentalFilePath);
        }
        // Get the instance (which will recreate the file with defaults).
        pc = parentalController.getInstance();
    }

    @Test
    public void testDefaultEnableTimeVal() {
        // The default behavior sets "Enable" to true.
        assertTrue(pc.getEnableTimeVal(), "Default Enable should be true");
    }

    @Test
    public void testSaveAndRetrieveTotalTime() {
        // Initially, total time should be 0.
        assertEquals(0, pc.getTotalTime(), "Initial total time should be 0");
        // Save a total time and verify.
        pc.saveTotal(1000);
        assertEquals(1000, pc.getTotalTime(), "Total time should be 1000 after saving");
    }

    @Test
    public void testResetAllData() {
        // Set some total time first.
        pc.saveTotal(5000);
        // Now reset data.
        pc.resetAllData();
        assertEquals(0, pc.getTotalTime(), "After reset, total time should be 0");
        // Default "Enable" should remain true.
        assertTrue(pc.getEnableTimeVal(), "Enable should remain true after reset");
    }
}