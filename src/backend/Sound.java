package src.backend;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class Sound { //shit
    public Clip clip;
    public boolean paused;
    public long startTime;

    public void play(String filePath) {
        try {
            File audioFile = new File(filePath);
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(audioFile));
            clip.start();
            startTime = System.currentTimeMillis();
            paused = false;
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && (clip.isRunning() || paused)) {
            clip.stop();
            clip.close();
            paused = false;
        }
    }

    public void pause() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            paused = true;
            long elapsedTime = System.currentTimeMillis() - startTime;
            startTime += elapsedTime;
        }
    }

    public long getTime() {
        if (clip != null && (clip.isRunning() || paused)) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            return elapsedTime;
        }
        return 0;
    }
}