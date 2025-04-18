package com.group46.components;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class settingsManager {

  //  json settings data
  private JsonNode parsedSettings;

  //  instance of the settings manager
  private static settingsManager instance;

  /**
   * Constructor calls loadSettings
   */
  private settingsManager() {
    loadSettings();
  }

  /**
   * This function return an instance of this class
   * Never actually use "settingsManager x = new settingsManger()" as this creates another instance and we only ever
   * want one
   * This method is called the singleton
   *
   * @return settingsManager
   */
  public static settingsManager getInstance() {
//    check if instance has been created if not create one
    if (instance == null) {
      instance = new settingsManager();
    }

//    return the current instance
    return instance;
  }

  /**
   * Used to get the volume from the json file
   *
   * @return int
   */
  public int getVolumeValue() {
    return this.parsedSettings.get("volume").intValue();
  }

  /**
   * Get the value of the autosave from the json (true or false)
   *
   * @return boolean
   */
  public boolean getAutoSaveValue() {
    return this.parsedSettings.get("auto_save").booleanValue();
  }

  public void updateParsedSettings() {
    try {
      this.parsedSettings = JSON.parse("settings");
    } catch (IOException e) {
      System.out.println("Error in parsing settings in updateParsedSettings.");
      throw new RuntimeException(e);
    }
  }


  /**
   * This function loads the settings from the json file and sets the parsedSettings variable
   */
  @FXML
  private void loadSettings() {
//    get file path of the settings file
    Path filePath = Paths.get(JSON.getDatabaseFolder(), "settings.json");
    boolean createNewSettings = false;

    // Check if file exists
    if (!Files.exists(filePath)) {
      createNewSettings = true;
    } else {
      // File exists, but check if it's valid JSON
      try {
        this.parsedSettings = JSON.parse("settings");
      } catch (IOException e) {
        System.out.println("Error reading settings file: " + e.getMessage());
        // If parsing fails, consider the file corrupted and recreate it
        createNewSettings = true;
      }
    }

    // Create default settings if needed
    if (createNewSettings) {
      ObjectMapper mapper = JSON.getObjectMapper();
      ObjectNode settings = mapper.createObjectNode();

      settings.put("volume", 50);
      settings.put("auto_save", true);

      try {
        // Ensure directory exists
        Files.createDirectories(filePath.getParent());
        // Write default settings
        JSON.toJson("settings", settings);
        // Now parse the newly created settings
        this.parsedSettings = JSON.parse("settings");
      } catch (IOException e) {
        System.err.println("Failed to create default settings: " + e.getMessage());
        throw new RuntimeException("Could not initialize application settings", e);
      }
    }
  }
}
