package com.mycompany.a2;

import java.io.InputStream;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable {
	private Media m;
	
	public BGSound(String fileName) {
		try {
			InputStream is = Display.getInstance().getResourceAsStream(getClass(), "/"+ fileName);
			m = MediaManager.createMedia(is, "audio/wav", this);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Lowers volume and plays the audio file
	 */
	public void playMusic() {
		m.setVolume(20);
		m.play();
	}
	
	/**
	 * Pauses the audio file
	 */
	public void pause() {
		m.pause();
	}
	
	/**
	 * Resets and plays the audio file
	 */
	public void run() {
		m.setTime(0);
		m.play();
	}
}
