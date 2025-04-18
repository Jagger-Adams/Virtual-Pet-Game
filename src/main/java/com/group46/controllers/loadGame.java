package com.group46.controllers;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import com.fasterxml.jackson.databind.JsonNode;
import com.group46.components.JSON;
import javafx.fxml.FXML;
import com.group46.App;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.util.stream.Stream;

/**
 * Load game controller
 */
public class loadGame implements Initializable {

  // variables
  static JsonNode selectedSave;

  private List<Path> listOfSaves;

  @FXML
  private VBox loadSavesContainer;

  @FXML
  private Button nextButton;
  
  @FXML
  private VBox deleteButtonContainer;

  private final Button deleteButton = new Button();


  /**
   * Returns each of the file paths of the provided directory path
   *
   * @param directory
   * @return List<Path>
   * @throws IOException
   */
  private List<Path> listFiles(String directory) throws IOException {
    List<Path> fileList;

//    get stuff from folder and filter for regular files (no folders)
    try (Stream<Path> paths = Files.list(Paths.get(directory))) {
      fileList = paths
          .filter(Files::isRegularFile)
          .toList();
    }
    return fileList;
  }


  /**
   * The exit button on the fxml page invokes this function
   *
   * @throws IOException
   */
  @FXML
  private void goBack() throws IOException {
//    return to main menu and reset all buttons and variables
    App.setRoot("mainMenu");
    selectedSave = null;
    nextButton.setDisable(true);
    deleteButton.setDisable(true);
  }


  /**
   * Convert playtime in seconds and display time in HH:MM
   *
   * @param playtime
   * @return String
   */
  private String calculateFormattedTime(int playtime) {
    int hours = playtime / 3600;
    int minutes = playtime % 3600 / 60;

    if (hours < 1) {
      return String.format("%02d minute", minutes);
    }

    if (minutes == 0) {
      return String.format("%02d hour" + (hours != 1 ? "s" : ""), hours);
    }

    return String.format("%02d hours %02d minutes", hours, minutes);
  }


  /**
   * This function is used to build each individual save container and fills in data based on the json(purely UI)
   * !important: to understand the construction of the loadsave ui I left the fully constructed and designed one commented in the load game fxml
   *
   * @param parsedSaveFile
   * @return HBox
   */
  @FXML
  private HBox gameSaveUI(JsonNode parsedSaveFile) {
//    data formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // data from json turned into usable variables to simplify construction
    String pet = parsedSaveFile.get("pet").get("type").asText();
    String saveName = parsedSaveFile.get("save").get("name").asText();
    int daysAlive = parsedSaveFile.get("day").asInt();
    boolean isAlive = parsedSaveFile.get("status_is_alive").asBoolean();
    LocalDateTime lastPlayed = LocalDateTime.parse(parsedSaveFile.get("save").get("last_played").asText(), formatter);
    int year = lastPlayed.getYear();
    int month = lastPlayed.getMonthValue();
    int day = lastPlayed.getDayOfMonth();
    int playtime = parsedSaveFile.get("save").get("playtime").asInt();
    int score = parsedSaveFile.get("score").asInt();

    // css styling variables because I use them a lot here
    String subheaderStyles = "-fx-font-size: 1.5em; -fx-font-weight: 700";
    String headerStyles = "-fx-font-size: 3em; -fx-font-weight: 900";

    // create main container and add styles
    HBox container = new HBox();
    container.getStyleClass().add("gameSave");
    container.setPadding(new Insets(20, 20, 20, 20));

//  determine which style to add based on if the pet is alive
    if (!isAlive) {
      container.getStyleClass().add("dead");
    } else {
      container.getStyleClass().add("default");
    }

// label hell( step 1. create label, step 2. add data,  step 3. add styles and other properties)
    Label saveNameLabel = new Label(saveName);
    saveNameLabel.setStyle(headerStyles);
    VBox.setVgrow(saveNameLabel, Priority.ALWAYS);

    Label scoreLabel = new Label("Score: " + score);
    scoreLabel.setStyle(subheaderStyles);

    Label lastPlayedLabel = new Label("Last Played: " + year + "-" + month + "-" + day);
    lastPlayedLabel.setStyle(subheaderStyles);

    Label playtimeLabel = new Label("Playtime: " + calculateFormattedTime(playtime));
    playtimeLabel.setStyle(subheaderStyles);

    Label statusLabel = new Label("Status: " + (isAlive ? "Alive" : "Dead"));
    statusLabel.setStyle(subheaderStyles);

    Label dayLabel = new Label("Day: " + daysAlive);
    dayLabel.setStyle(subheaderStyles);

//    create another HBox container for left side details
    HBox leftSideDetailsContainer = new HBox();
    leftSideDetailsContainer.setAlignment(Pos.CENTER);
    leftSideDetailsContainer.setPadding(new Insets(10, 10, 10, 10));

//    this vbox is used for positioning the elements
    VBox leftSideDetails = new VBox();
    leftSideDetails.setAlignment(Pos.CENTER_LEFT);
    leftSideDetails.setStyle("-fx-padding: 0 0 0 20");

//    create another Hbox and add data to it along with styles and other properties and add to parent tags
    HBox leftSideSmallDetails = new HBox();
    leftSideSmallDetails.setAlignment(Pos.CENTER);
    leftSideSmallDetails.setSpacing(40);
    leftSideSmallDetails.setStyle("-fx-padding: 50 0 0 0");
    leftSideSmallDetails.getChildren().add(statusLabel);
    leftSideSmallDetails.getChildren().add(dayLabel);

    leftSideDetails.getChildren().add(saveNameLabel);
    leftSideDetails.getChildren().add(leftSideSmallDetails);


//    get the pet icon for the pet, if something went wrong no image png will display
    Image petIcon;
    try {
      petIcon = new Image(getClass().getResourceAsStream("/com/group46/assets/pets/pixelart " + pet + ".gif"));
    } catch (Exception e) {
      petIcon = new Image(getClass().getResourceAsStream("/com/group46/assets/images/noImageAvailable.png"));
    }

//    create the image viewer and set dimensions
    ImageView imageView = new ImageView(petIcon);
    imageView.setFitHeight(230);
    imageView.setFitWidth(230);
    imageView.setPreserveRatio(true);

//    children containers and tags to parent tags
    leftSideDetailsContainer.getChildren().add(imageView);
    leftSideDetailsContainer.getChildren().add(leftSideDetails);

//  create a spacer(this is used to create space between the left and right ui)
    Region spacer = new Region();
    HBox.setHgrow(spacer, Priority.ALWAYS);

//  create Vbox container and add styling and children
    VBox rightSideDetailsContainer = new VBox();
    rightSideDetailsContainer.setAlignment(Pos.CENTER_LEFT);
    rightSideDetailsContainer.setSpacing(20);
    rightSideDetailsContainer.setStyle("-fx-padding: 0 20 0 0");

    rightSideDetailsContainer.getChildren().add(scoreLabel);
    rightSideDetailsContainer.getChildren().add(playtimeLabel);
    rightSideDetailsContainer.getChildren().add(lastPlayedLabel);

//  now add everything to the parent container
    container.getChildren().add(leftSideDetailsContainer);
    container.getChildren().add(spacer);
    container.getChildren().add(rightSideDetailsContainer);

//    unique key prop based on save name(always unique)
    container.setId(saveName);

    // added an event handler to each save to handle selecting saves
    container.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
      selectedSave(container, parsedSaveFile);
    });

    // return the fully built container
    return container;
  }


  //  claude the goat

  /**
   * This function adds and removes the styles when the save is clicked on.
   * It also will set the selectedSave variable to the currently selected save on the UI
   *
   * @param container
   * @param parsedSaveFile
   */
  private void selectedSave(HBox container, JsonNode parsedSaveFile) {
    // Iterate through all children in the loadSavesContainer
    for (Node node : loadSavesContainer.getChildren()) {
      if (node instanceof HBox saveContainer && node.getStyleClass().contains("gameSave")) {
        // Remove 'selected' class from all containers
        saveContainer.getStyleClass().remove("selected");

        // Restore original styling
        if (saveContainer.getStyleClass().contains("dead")) {
          saveContainer.getStyleClass().remove("selected");
        } else {
          if (!saveContainer.getStyleClass().contains("default")) {
            saveContainer.getStyleClass().add("default");
          }
        }
      }
    }

    // Remove default from the selected container and add selected
    container.getStyleClass().remove("default");
    container.getStyleClass().add("selected");

    // store the save file
    selectedSave = parsedSaveFile;

    //  disable certain buttons based on if the pet is alive
    if (!selectedSave.get("status_is_alive").asBoolean()) {
      nextButton.setDisable(true);
      deleteButton.setDisable(true);
    } else {
      nextButton.setDisable(false);
      deleteButton.setDisable(false);
    }
  }


  /**
   * This function is used to get the selected save (used in the play class/controller)
   *
   * @return
   */
  public static JsonNode getSelectedSave() {
    return selectedSave;
  }


  /**
   * This function deletes the currently selected save
   */
  @FXML
  public void deleteSave() {


    // delete the json file
    if (!nextButton.isDisabled()) {
      try {
        String dir = "src/main/resources/com/group46/database/saves";
        Path saveFileToDelete = Paths.get(dir, selectedSave.get("save").get("name").asText() + ".json");
        Files.delete(saveFileToDelete);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      // delete the ui
      loadSavesContainer.getChildren().removeIf(save ->
          save.getId().equals(selectedSave.get("save").get("name").asText())
      );


//      disable buttons if the saves list is empty
      if (listOfSaves.isEmpty()) {
        selectedSave = null;
        deleteButton.setDisable(true);
        nextButton.setDisable(true);
      }

//      display/ update ui
      loadGameSaves();
    }
  }

  // my brain + claude might be the goat

  /**
   * This function sorts the saves based on alive status and last played
   *
   * @param saves
   * @return List<Path>
   */
  private List<Path> sort(List<Path> saves) {
//    date formatter
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //    turn the saves into and stream and sort
    return saves.stream()
        .sorted((path1, path2) -> {
          try {
            // Parse JSON for both saves (n, n + 1)
            JsonNode json1 = JSON.parse("saves/" + path1.getFileName().toString().replace(".json", ""));
            JsonNode json2 = JSON.parse("saves/" + path2.getFileName().toString().replace(".json", ""));

            // get alive status from each json file
            boolean isAlive1 = json1.get("status_is_alive").asBoolean();
            boolean isAlive2 = json2.get("status_is_alive").asBoolean();

            // If alive status differs, alive pets come first
            if (isAlive1 != isAlive2) {
              return isAlive1 ? -1 : 1;
            }

            // If both have same alive status, sort by last_played (most recent first)
//            get last played data from json
            LocalDateTime save1 = LocalDateTime.parse(
                json1.get("save").get("last_played").asText(),
                formatter
            );
//            get last played data from json
            LocalDateTime save2 = LocalDateTime.parse(
                json2.get("save").get("last_played").asText(),
                formatter
            );

//            compare each save and return
            return save2.compareTo(save1);
          } catch (Exception e) {
            // Handle any reading or parsing errors
            e.printStackTrace();
            return 0;
          }
//          convert the data into a list
        }).toList();
  }


  //  display the saves in the UI

  /**
   * Displays the saves in the ui
   *
   * @param saves
   */
  private void displaySaves(List<Path> saves) {
//    sort the saves first
    List<Path> sortedSaves = sort(saves);
//    for each save create and add to container loadSaveContainer, which holds all the saves
    sortedSaves.forEach(save -> {
      try {
//        get json file data
        JsonNode parsedSaveFile = JSON.parse("saves/" + save.getFileName().toString().replace(".json", ""));
        System.out.println(parsedSaveFile.get("save").get("name").asText());
//        create load save and add to the loadSaveContainer
        loadSavesContainer.getChildren().add(gameSaveUI(parsedSaveFile));
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    });
  }

  /**
   * This function is invoked from the load game fxml
   * it's used to go the play stage
   *
   * @throws IOException
   */
  @FXML
  private void next() throws IOException {
//    go to play
    App.setRoot("play");
//    reset buttons and variables
    selectedSave = null;
    nextButton.setDisable(true);
    deleteButton.setDisable(true);

  }

  /**
   * This function will get all the saves from the database/saves folder in resources
   *
   * @return List<Path>
   */
  private List<Path> getListOfSaves() {
    try {
      return listFiles(Paths.get(JSON.getDatabaseFolder() + "saves/").toString());
    } catch (IOException e) {
      System.out.println(e);
    }
    return null;
  }

  /**
   * This function is used for passing data and calling functions for the loading saves
   */
  private void loadGameSaves() {
//    clear all saves off the ui first
    loadSavesContainer.getChildren().clear();
//    get all the saves
    listOfSaves = getListOfSaves();
//    check if not null then display the saves
    if (listOfSaves != null) {
      displaySaves(listOfSaves);
    }
  }

  /**
   * This function was created just so I can use an icon instead of displaying the word "delete" in the button
   */
  @FXML
  private void deleteButtonUI() {
    //    create delete button
//    get icon
    Image trashImage = new Image(getClass().getResourceAsStream("/com/group46/assets/images/trash.png"));
//    set icon
    ImageView trashIcon = new ImageView(trashImage);
//    set dimensions and preserve ratio
    trashIcon.setFitWidth(30);
    trashIcon.setFitHeight(30);
    trashIcon.setPreserveRatio(true);

//    add icon, styles and handlers to the deleteButton
    deleteButton.setGraphic(trashIcon);
    deleteButton.getStyleClass().add("deleteButton");
    deleteButton.setOnAction(event -> {
      deleteSave();
    });

//    disable trash button by default
    deleteButton.setDisable(true);
//    add to the fxml page
    deleteButtonContainer.getChildren().add(deleteButton);
  }


  // THIS COMES WITH EVERY CONTROLLER

  /**
   * This function is invoked automatically when the page is loaded calling the necessary functions and setting
   * disable status to true for the delete and next button
   *
   * @param url
   * @param resourceBundle
   */
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    loadGameSaves();
    deleteButtonUI();

    nextButton.setDisable(true);
    deleteButton.setDisable(true);
  }
}
