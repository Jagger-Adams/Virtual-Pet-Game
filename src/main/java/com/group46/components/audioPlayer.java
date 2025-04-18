package com.group46.components;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class audioPlayer {

  //  audio player instance
//  use like "audioPlayer x = audioPlayer.getInstance()" don't use "audioPlayer x = new audioPlayer()"
  public static audioPlayer instance;

  //  media player
  private MediaPlayer mediaPlayer;


  /**
   * This function lets you set the song you want to play just pass in the file name no extension(must be mp3)
   *
   * @param fileName
   */
  public void setMedia(String fileName) {
    try {
//      create media from song
      Media media = new Media(new File(getClass().getResource("/com/group46/assets/" + fileName + ".mp3").toURI()).toURL().toString());
      mediaPlayer = new MediaPlayer(media);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * This function returns the media player, allows you to control the player(play, pause, volume, etc.)
   *
   * @return MediaPlayer
   */
  public MediaPlayer getMediaPlayer() {
    return mediaPlayer;
  }

  /**
   * returns a single instance of this class audioPlayer
   *
   * @return audioPlayer
   */
  public static audioPlayer getInstance() {
//    check if instance has been created, if not create one
    if (instance == null) {
      instance = new audioPlayer();
    }
// return the current instance (singleton method)
    return instance;
  }

}


