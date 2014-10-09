package view;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AudioManager {
	Clip backgroundMusik;
	Clip foodSound;
	Clip lossSound;
	Clip winSound;

	public AudioManager() {
		foodSound = getClip("Food.wav");
		backgroundMusik = getClip("BackGroundMusik.wav");
		winSound = getClip("Win.wav");
		lossSound = getClip("Loss.wav");
	}

	private Clip getClip(String fileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource(fileName);
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Clip c = AudioSystem.getClip();
			c.open(audioIn);
			return c;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("We ran into some issues loading the soundfiles");
		}
	}

	public void playBackgroundMusik() {
		backgroundMusik.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stopBackgroundMusik() {
		backgroundMusik.stop();
	}

	public void playFoodSound() {
		foodSound.setFramePosition(0);
		foodSound.start();
	}

	public void playLossSound() {
		lossSound.start();
	}

	public void playWinSound() {
		winSound.start();
	}
}
