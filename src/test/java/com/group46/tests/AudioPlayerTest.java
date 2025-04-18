package com.group46.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.group46.components.audioPlayer;
import javafx.application.Platform;
import javafx.scene.media.MediaPlayer;

public class AudioPlayerTest {

    // Ensure JavaFX is initialized before any tests run.
    @BeforeAll
    public static void initJFX() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // JavaFX already started.
        }
    }

    @Test
    public void testSingletonInstance() {
        audioPlayer instance1 = audioPlayer.getInstance();
        audioPlayer instance2 = audioPlayer.getInstance();
        assertNotNull(instance1, "Instance should not be null");
        assertSame(instance1, instance2, "Both instances should be the same (singleton)");
    }

    @Test
    public void testSetMedia() {
        audioPlayer player = audioPlayer.getInstance();
        // This test assumes the media file "music/ariaMath.mp3" exists under 
        // src/main/resources/com/group46/assets/music/ariaMath.mp3
        player.setMedia("music/ariaMath");
        MediaPlayer mediaPlayer = player.getMediaPlayer();
        assertNotNull(mediaPlayer, "MediaPlayer should not be null after setting media");
    }
    
    @Test
    public void testMuteToggle() {
        audioPlayer player = audioPlayer.getInstance();
        player.setMedia("music/ariaMath");
        MediaPlayer mediaPlayer = player.getMediaPlayer();
        assertNotNull(mediaPlayer, "MediaPlayer should not be null after setting media");
        
        mediaPlayer.setMute(true);
        assertTrue(mediaPlayer.isMute(), "MediaPlayer should be muted after setMute(true)");
        
        mediaPlayer.setMute(false);
        assertFalse(mediaPlayer.isMute(), "MediaPlayer should not be muted after setMute(false)");
    }
}
