package com.group46.components;


// JSON STUFF from jackson

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JSON {

  // object mapper instance we can use
  private static ObjectMapper objectMapper = objectMapperInstance();
  //  location of data(fake database not an actual one, adding an actual database to this project is really easy since
  //  we have this class, using nosql is highly recommended like scylladb or mongodb)
  final private static String databaseFolder = "src/main/resources/com/group46/database/";


  // create an object mapper we can add settings here if we need

  /**
   * This return's an instance of the object mapper with a bunch of settings
   *
   * @return ObjectMapper
   */
  private static ObjectMapper objectMapperInstance() {
    return new ObjectMapper().configure(JsonParser.Feature.ALLOW_COMMENTS, true)
        .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true)
        .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
        .configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  /**
   * Get the object mapper
   *
   * @return ObjectMapper
   */
  public static ObjectMapper getObjectMapper() {
    return objectMapper;
  }

  /**
   * This function will parse(read) data from the provided file name
   *
   * @param fileName
   * @return JsonNode
   * @throws IOException
   */
  public static JsonNode parse(String fileName) throws IOException {
//    create that path url based on params
    Path filePath = Paths.get(databaseFolder, fileName + ".json");

    //   check if the file does exist
    if (!Files.exists(filePath)) {
      throw new FileNotFoundException("File not found: " + fileName);
    }

//    return the actual parsed data
    return objectMapper.readTree(filePath.toFile());
  }


  /**
   * Convert an ObjectNode Tree into a json file
   *
   * @param fileName
   * @param node
   * @throws IOException
   */
  public static void toJson(String fileName, ObjectNode node) throws IOException {
//    create file path
    Path filePath = Paths.get(databaseFolder, fileName + ".json");

    objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath.toString()), node);
  }

  /**
   * Get the database folder path
   *
   * @return String
   */
  public static String getDatabaseFolder() {
    return databaseFolder;
  }


}
