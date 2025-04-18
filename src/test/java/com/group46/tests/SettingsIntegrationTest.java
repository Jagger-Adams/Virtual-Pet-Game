package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.group46.components.JSON;
import com.group46.components.settingsManager;

public class SettingsIntegrationTest {
  private settingsManager settingsMgr;
  private ObjectMapper mapper;

  @BeforeEach
  public void setup() {
    // Retrieve the settings manager instance.
    settingsMgr = settingsManager.getInstance();
    mapper = JSON.getObjectMapper();
    // (Optionally, backup and restore existing settings)
  }

  @Test
  public void testSaveAndReloadSettings() throws IOException {
    // Create a new settings node with custom values.
    ObjectNode newSettings = mapper.createObjectNode();
    newSettings.put("volume", 75);
    newSettings.put("auto_save", false);

    // Save these settings using the JSON utility.
    JSON.toJson("settings", newSettings);

    // Reload settings in the settings manager.
    settingsMgr.updateParsedSettings();

    // Verify that the updated settings are loaded.
    int volume = settingsMgr.getVolumeValue();
    boolean autoSave = settingsMgr.getAutoSaveValue();
    assertEquals(75, volume, "Volume should update to 75");
    assertFalse(autoSave, "auto_save should update to false");
  }


}