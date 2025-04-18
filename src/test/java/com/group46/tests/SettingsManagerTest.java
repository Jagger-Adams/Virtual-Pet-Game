package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.group46.components.settingsManager;

public class SettingsManagerTest {
  private settingsManager settingsMgr;

  @BeforeEach
  public void setup() {
    // Get the singleton instance. This will load settings from the file.
    settingsMgr = settingsManager.getInstance();
  }

  @Test
  public void testDefaultVolume() {
    // Default volume is expected to be 75 (as per your settings file)
    int volume = settingsMgr.getVolumeValue();
    assertEquals(75, volume, "Default volume should be 75");
  }

  @Test
  public void testDefaultAutoSave() {
    // Default auto_save is expected to be false.
    boolean autoSave = settingsMgr.getAutoSaveValue();
    assertTrue(!autoSave, "Default auto_save should be false");
  }

  @Test
  public void testUpdateParsedSettings() {
    // Ensure that updateParsedSettings() doesn't throw an exception.
    assertDoesNotThrow(() -> settingsMgr.updateParsedSettings(), "updateParsedSettings should not throw an exception");
  }
}
