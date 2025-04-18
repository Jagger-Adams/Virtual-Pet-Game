package com.group46.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javafx.fxml.FXML;
import com.group46.App;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.control.CheckBox;
import com.group46.components.settingsManager;
import com.group46.components.JSON;

import com.group46.components.audioPlayer;

public class settings implements Initializable {


  // FXML page variables
  @FXML
  private CheckBox autoSave;

  @FXML
  private Slider volumeSlider;

  //  variables
  private final settingsManager parsedSettings = settingsManager.getInstance();
  private final audioPlayer player = audioPlayer.getInstance();


  /**
   * Return to main menu function, used in the settings fxml
   *
   * @throws IOException
   */
  @FXML
  private void goBack() throws IOException {
    App.setRoot("mainMenu");
  }


  /**
   * This function changes the volume of the media player
   *
   * @param volume
   */
  private void handleVolumeChange(double volume) {
    System.out.println(volume);
// set media player volume
    player.getMediaPlayer().setVolume(volume);
  }


  /**
   * This function saves the settings
   */
  @FXML
  private void saveSettings() {
//    get mapper (used to create the nodes)
    ObjectMapper mapper = JSON.getObjectMapper();

//    create settings node
    ObjectNode settings = mapper.createObjectNode();

//    add data to node
    settings.put("volume", Math.round(volumeSlider.getValue()));
    settings.put("auto_save", autoSave.isSelected());

    // set data to volume, auto save and player
    volumeSlider.setValue(Math.round(volumeSlider.getValue()));
    autoSave.setSelected(autoSave.isSelected());
    player.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);

//    save to json
    try {
      System.out.println("SAVING SETTINGS");
      JSON.toJson("settings", settings);
      parsedSettings.updateParsedSettings();
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * This function is invoked on page load
   * setting the volume and autosave values
   * and adds the listener to the volume slider
   *
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    // Set the volume slider to the current volume setting
    volumeSlider.setValue(parsedSettings.getVolumeValue());
    // Set the auto-save checkbox to the current auto-save setting
    autoSave.setSelected(parsedSettings.getAutoSaveValue());


    //    handle real time audio change(when you change the volume, you can actually hear it change loudness)
    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      handleVolumeChange(newValue.doubleValue() / 100);
    });
  }
}
