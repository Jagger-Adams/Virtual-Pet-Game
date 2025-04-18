package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.group46.components.JSON;

public class JSONTest {

    @Test
    public void testToJsonAndParse() throws IOException {
        // Create an ObjectNode with sample data.
        ObjectNode node = JSON.getObjectMapper().createObjectNode();
        node.put("testKey", "testValue");

        // Write to a temporary file (using a unique file name).
        String testFileName = "testFile";
        JSON.toJson(testFileName, node);

        // Read it back.
        JsonNode parsedNode = JSON.parse(testFileName);
        assertNotNull(parsedNode, "Parsed node should not be null");
        assertEquals("testValue", parsedNode.get("testKey").asText(), "The value for testKey should match");
    }

    @Test
    public void testGetDatabaseFolder() {
        String folder = JSON.getDatabaseFolder();
        assertNotNull(folder, "Database folder should not be null");
        // Ensure the folder string contains the expected path fragment.
        assertTrue(folder.contains("com/group46/database"), "Database folder should include 'com/group46/database'");
    }
}