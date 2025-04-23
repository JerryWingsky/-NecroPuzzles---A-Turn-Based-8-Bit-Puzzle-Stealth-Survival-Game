package gameSounds;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    private Clip clip;
    private FloatControl volumeControl;

    public SoundPlayer(String filePath) {
        try {
            // Use class loader to load the music file
            URL soundURL = getClass().getResource(filePath);
            if (soundURL == null) {
                System.out.println("Music file not found: " + filePath);
                return;
}
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Try to get the volume control from the clip
            if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                System.out.println("Volume control not supported");
}
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
}
}

    public void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start(); // Start playing
}
}

    public void stop() {
        if (clip != null) {
            clip.stop(); // Stop playing
}
}

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Loop indefinitely
}
}

    public void setVolume(float volume) {
        if (volumeControl != null) {
            // volume should be a value between 0.0f (silent) and 1.0f (full volume)
            float min = volumeControl.getMinimum();
            float max = volumeControl.getMaximum();
            float newVolume = min + (max - min) * volume;
            volumeControl.setValue(newVolume);
        } else {
            System.out.println("Volume control is not available");
}
}
}
